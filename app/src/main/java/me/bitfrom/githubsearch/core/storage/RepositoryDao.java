package me.bitfrom.githubsearch.core.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;

/**
 * <p>Data Access Object for the repositories table.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
@Dao
public interface RepositoryDao {

    /**
     * Selects list of {@link RepositoryEntity} and makes {@link Flowable} from that list.
     **/
    @Query("SELECT * From repositories")
    Flowable<List<RepositoryEntity>> getRepositories();

    /**
     * Inserts array of {@link RepositoryEntity} into the database.
     **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepositories(List<RepositoryEntity> repositoryEntities);

    /**
     * Deletes all repositories from the database.
     **/
    @Query("DELETE FROM repositories")
    void deleteAllRepositories();
}