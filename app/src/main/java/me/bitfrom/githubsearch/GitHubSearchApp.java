package me.bitfrom.githubsearch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import me.bitfrom.githubsearch.injection.components.ApplicationComponent;
import me.bitfrom.githubsearch.injection.components.DaggerApplicationComponent;
import me.bitfrom.githubsearch.injection.modules.ApplicationModule;
import timber.log.Timber;

/**
 * <p>For initialization useful stuff.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
public class GitHubSearchApp extends Application {

    private ApplicationComponent applicationComponent;
    private static GitHubSearchApp application;

    @Override
    public void onCreate() {
        super.onCreate();

        initGitHubSearchApplicationInstance(this);

        initTimber();
        initStetho();
        initLeakCanary();
    }

    public static GitHubSearchApp get() {
        return application;
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return applicationComponent;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private static void initGitHubSearchApplicationInstance(GitHubSearchApp app) {
        application = app;
    }
}