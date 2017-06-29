package me.bitfrom.githubsearch.injection.components;

import dagger.Subcomponent;
import me.bitfrom.githubsearch.injection.ActivityScope;
import me.bitfrom.githubsearch.injection.modules.ActivityModule;
import me.bitfrom.githubsearch.ui.main.MainActivity;

/**
 * <p>Component that is responsible for providing activity-related objects.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}