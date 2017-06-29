package me.bitfrom.githubsearch.injection.modules;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.githubsearch.BuildConfig;
import me.bitfrom.githubsearch.core.network.AutoValueGsonAdapterFactory;
import me.bitfrom.githubsearch.core.network.GitHubApi;
import me.bitfrom.githubsearch.utils.ConstantsHolder;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * <p>Module for instantiating network-related objects (OkHttp, Retrofit and so on).</p>
 * <p>Statics methods used for avoiding Dagger unnecessary object creation.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    static HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides @Singleton
    static StethoInterceptor providesStethoInterceptor() {
        return new StethoInterceptor();
    }

    /**
     * Caching interceptor - for 1 minute living cache.
     */
    @Provides @Singleton
    static Interceptor providesCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());

            //Re-write response headers to force use of cache
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(1, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .header(ConstantsHolder.CACHE_CONTROL_HEADER, cacheControl.toString())
                    .build();
        };
    }

    @Provides @Singleton
    static Cache providesCache(@NonNull Application application) {
        Cache cache = null;

        try {
            cache = new Cache(new File(application.getCacheDir(), ConstantsHolder.CACHE_DIR_NAME),
                    ConstantsHolder.CACHE_SIZE);
        } catch (Exception ex) {
            Timber.e(ex, "Exception occurred while creating cache directory!");
        }

        return cache;
    }

    @Provides @Singleton
    static OkHttpClient providesOkHttpClient(@NonNull HttpLoggingInterceptor loggingInterceptor,
                                             @NonNull StethoInterceptor stethoInterceptor,
                                             @NonNull Interceptor responseCodeAndCacheInterceptor,
                                             @Nullable Cache cache) {

        OkHttpClient.Builder okHttp = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            okHttp.addInterceptor(loggingInterceptor);
            okHttp.addNetworkInterceptor(stethoInterceptor);
        }

        okHttp.addInterceptor(responseCodeAndCacheInterceptor);
        okHttp.cache(cache);
        okHttp.readTimeout(ConstantsHolder.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttp.connectTimeout(ConstantsHolder.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttp.retryOnConnectionFailure(true);

        return okHttp.build();
    }

    @Provides @Singleton
    static Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonAdapterFactory.create())
                .serializeNulls()
                .create();
    }

    @Provides @Singleton
    static Retrofit providesRetrofit(@NonNull OkHttpClient okHttpClient,
                                     @NonNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ConstantsHolder.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton
    static GitHubApi providesForceApi(@NonNull Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }
}