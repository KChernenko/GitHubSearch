package me.bitfrom.githubsearch.ui.base;

import android.support.v7.app.AppCompatActivity;

import me.bitfrom.githubsearch.GitHubSearchApp;
import me.bitfrom.githubsearch.injection.components.ActivityComponent;
import me.bitfrom.githubsearch.injection.modules.ActivityModule;

/**
 * <p>Base activity class for DI and leak detecting purposes.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityComponent = null;
    }

    /**
     * Initialize activity-related objects.
     *
     * @return the {@link ActivityComponent}
     */
    protected ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = GitHubSearchApp.get()
                    .getApplicationComponent()
                    .addActivityComponent(new ActivityModule(this));
        }

        return activityComponent;
    }
}