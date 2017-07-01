package me.bitfrom.githubsearch.core;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import me.bitfrom.githubsearch.core.mappers.RepositoryMapper;
import me.bitfrom.githubsearch.core.network.ErrorResponse;
import me.bitfrom.githubsearch.core.network.GitHubApi;
import me.bitfrom.githubsearch.core.network.ServerResponse;
import me.bitfrom.githubsearch.core.network.models.GitHubResultModel;
import me.bitfrom.githubsearch.core.storage.DbHelper;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;
import me.bitfrom.githubsearch.utils.ConstantsHolder;
import okhttp3.ResponseBody;

/**
 * <p>An implementation of the {@link MainRepository}.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Singleton
public class MainRepositoryImpl implements MainRepository {

    private final Gson gson;
    private final DbHelper dbHelper;
    private final GitHubApi gitHubApi;
    private final RepositoryMapper repositoryMapper;

    @Inject
    public MainRepositoryImpl(@NonNull Gson gson,
                              @NonNull final DbHelper dbHelper,
                              @NonNull final GitHubApi gitHubApi,
                              @NonNull final RepositoryMapper repositoryMapper) {
        this.gson = gson;
        this.dbHelper = dbHelper;
        this.gitHubApi = gitHubApi;
        this.repositoryMapper = repositoryMapper;
    }

    @Override
    public Flowable<ServerResponse<Boolean>> searchRepositories(@NonNull final String repositoryName) {
        return gitHubApi.searchRepositories(repositoryName, ConstantsHolder.SORT_PRINCIPE_QUERY_PARAM,
                ConstantsHolder.ORDER_QUERY_PARAM)
                .concatMap(gitHubResultResponse -> {
                    //Generic server answer model - contains or boolean value or error message
                    final ServerResponse<Boolean> response = new ServerResponse<>();
                    //If response is successful, we could try to extract the data
                    if (gitHubResultResponse.isSuccessful()) {
                        //Since OkHttp 3.8.0 and Retrofit 2.4.0 all responses marked as @Nullable
                        final GitHubResultModel serializedResult = gitHubResultResponse.body();
                        if (serializedResult != null) {
                            //We need to deal somehow with old and new values
                            final long timeStamp = System.currentTimeMillis();
                            //Mapping response result to database insertion values
                            final List<RepositoryEntity> valuesToInsert =
                                    repositoryMapper.mapRepositories(serializedResult.items(), timeStamp);
                            //Inserting a new values into the database
                            dbHelper.repositoryDao().insertRepositories(valuesToInsert);
                            //Deleting the old ones
                            dbHelper.repositoryDao().deleteAllRepositories(timeStamp);
                            //Marking that response is successful - values are cached into the database.
                            response.setData(Boolean.TRUE);
                        } else {
                            //Could not extract the data from the response
                            response.setData(Boolean.FALSE);
                        }
                    } else {
                        //Response has http status different from 200, so it was unsuccessful
                        final ResponseBody responseBody = gitHubResultResponse.errorBody();
                        if (responseBody != null) {
                            //Extracting error message
                            response.setErrorMessage(gson.fromJson(responseBody.string(), ErrorResponse.class).message());
                        }
                    }
                    //Just a ServerResponse value
                    return Flowable.just(response);
                });
    }

    @Override
    public Flowable<List<RepositoryEntity>> getCachedRepositories() {
        return dbHelper.repositoryDao().getRepositories();
    }
}