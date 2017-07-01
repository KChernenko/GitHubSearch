package me.bitfrom.githubsearch.core;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;
import me.bitfrom.githubsearch.core.network.ServerResponse;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;

/**
 * <p>Entry point for main business logic.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
public interface MainRepository {

    /**
     * Makes search request to GitHub server and caches response.
     *
     * @param repositoryName name of the repository that user is looking for.
     *
     * @return a {@link Flowable} from the {@link ServerResponse} of a {@link Boolean}.
     **/
    Flowable<ServerResponse<Boolean>> searchRepositories(@NonNull String repositoryName);

    /**
     * Loads and subscribes for repository table.
     *
     * @return a {@link Flowable} from {@link List} of the {@link RepositoryEntity}.
     **/
    Flowable<List<RepositoryEntity>> getCachedRepositories();
}