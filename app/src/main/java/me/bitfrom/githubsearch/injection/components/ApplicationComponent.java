package me.bitfrom.githubsearch.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import me.bitfrom.githubsearch.injection.modules.ActivityModule;
import me.bitfrom.githubsearch.injection.modules.ApplicationModule;
import me.bitfrom.githubsearch.injection.modules.NetworkModule;

/**
 * <p>Component that is responsible for providing application-related objects.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    /**
     * Helper method for initialization {@link ActivityComponent} subcomponent.
     *
     * @param activityModule the activity module
     * @return the activity component
     */
    ActivityComponent addActivityComponent(ActivityModule activityModule);

}
