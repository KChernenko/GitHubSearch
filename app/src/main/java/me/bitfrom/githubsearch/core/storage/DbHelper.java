package me.bitfrom.githubsearch.core.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

import me.bitfrom.githubsearch.core.storage.daos.RepositoryDao;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;
import me.bitfrom.githubsearch.utils.ConstantsHolder;

/**
 * <p>Room database creation.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
@Database(entities = {RepositoryEntity.class}, version = ConstantsHolder.DATABASE_VERSION)
@Singleton
public abstract class DbHelper extends RoomDatabase {

    public abstract RepositoryDao repositoryDao();

}