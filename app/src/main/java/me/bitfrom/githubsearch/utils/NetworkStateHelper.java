package me.bitfrom.githubsearch.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.bitfrom.githubsearch.injection.ActivityScope;
import me.bitfrom.githubsearch.injection.ApplicationContext;

/**
 * <p>Network checker utility class.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@ActivityScope
public class NetworkStateHelper {

    private Context context;

    @Inject
    public NetworkStateHelper(@NonNull @ApplicationContext Context context) {
        this.context = context;
    }

    /**
     * Returns true if the network is available or about become available.
     *
     * @return boolean statement
     **/
    public boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}