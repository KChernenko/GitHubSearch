package me.bitfrom.githubsearch.core.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;

/**
 * <p>.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
@Database(entities = {RepositoryEntity.class}, version = 1)
public abstract class DbHelper extends RoomDatabase {


}
