package me.bitfrom.githubsearch.core.network;


import io.reactivex.Flowable;
import me.bitfrom.githubsearch.core.network.models.GitHubResultModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <p>All server requests.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
public interface GitHubApi {

    @GET("search/repositories")
    Flowable<Response<GitHubResultModel>> searchRepositories(@Query("q") String query,
                                                             @Query("sort") String sortPrincipe,
                                                             @Query("order") String order);
}