package me.bitfrom.githubsearch.injection.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.githubsearch.core.MainRepository;
import me.bitfrom.githubsearch.core.MainRepositoryImpl;
import me.bitfrom.githubsearch.injection.ApplicationContext;

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
     * Provides application-related context.
     *
     * @return the context
     **/
    @Provides
    @NonNull
    @ApplicationContext
    Context providesApplicationContext() {
        return application.getApplicationContext();
    }

    /**
     * Provides repository object - business logic access point
     *
     * @return {@link MainRepository} implementation.
     **/
    @Provides
    @NonNull
    MainRepository providesMainRepository() {
        return new MainRepositoryImpl();
    }
}