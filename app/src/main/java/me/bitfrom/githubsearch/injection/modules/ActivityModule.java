package me.bitfrom.githubsearch.injection.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.githubsearch.injection.ActivityContext;
import me.bitfrom.githubsearch.utils.NetworkStateHelper;

/**
 * <p>Module for instantiation activity-related objects.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Module
public class ActivityModule {

    @NonNull
    private final AppCompatActivity activity;

    public ActivityModule(@NonNull AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * Provides activity-related context.
     *
     * @return the context
     */
    @Provides @NonNull
    @ActivityContext
    Context providesActivityContext() {
        return activity;
    }

    @Provides @NonNull
    NetworkStateHelper networkStateHelper(@NonNull @ActivityContext Context context) {
        return new NetworkStateHelper(context);
    }
}