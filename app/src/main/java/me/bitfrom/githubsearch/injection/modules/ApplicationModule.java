package me.bitfrom.githubsearch.injection.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.githubsearch.core.MainRepository;
import me.bitfrom.githubsearch.core.MainRepositoryImpl;
import me.bitfrom.githubsearch.core.mappers.RepositoryMapper;
import me.bitfrom.githubsearch.core.network.GitHubApi;
import me.bitfrom.githubsearch.core.storage.DbHelper;
import me.bitfrom.githubsearch.injection.ApplicationContext;
import me.bitfrom.githubsearch.utils.ConstantsHolder;

/**
 * <p>Module for instantiation application-related objects.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Module
public class ApplicationModule {

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    /**
     * Provides application instance for internal needs.
     *
     * @return the {@link Application} instance.
     **/
    @Provides @NonNull
    Application providesApplication() {
        return application;
    }

    /**
     * Provides application-related context.
     *
     * @return the {@link Context} instance.
     **/
    @Provides
    @NonNull
    @ApplicationContext
    Context providesApplicationContext() {
        return application.getApplicationContext();
    }

    /**
     * Provides database-layer.
     *
     * @return the {@link DbHelper} instance.
     **/
    @Provides
    @NonNull
    DbHelper providesDbHelper(@NonNull @ApplicationContext Context context) {
        return Room.databaseBuilder(context, DbHelper.class, ConstantsHolder.DATABASE_NAME).build();
    }

    /**
     * Provides mapper object, that could map one type of the repositories data to another.
     *
     * @return the {@link RepositoryMapper} instance.
     **/
    @Provides
    @NonNull
    RepositoryMapper providesRepositoryMapper() {
        return new RepositoryMapper();
    }

    /**
     * Provides repository object - business logic access point
     *
     * @param dbHelper database access layer object.
     * @param gitHubApi server access layer object.
     *
     * @return {@link MainRepository} implementation.
     **/
    @Provides
    @NonNull
    MainRepository providesMainRepository(@NonNull Gson gson,
                                          @NonNull DbHelper dbHelper,
                                          @NonNull GitHubApi gitHubApi,
                                          @NonNull RepositoryMapper repositoryMapper) {
        return new MainRepositoryImpl(gson, dbHelper, gitHubApi, repositoryMapper);
    }
}