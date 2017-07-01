package me.bitfrom.githubsearch.ui.main;

import android.support.annotation.NonNull;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.bitfrom.githubsearch.core.MainRepository;
import me.bitfrom.githubsearch.ui.base.BasePresenter;
import timber.log.Timber;

/**
 * <p>Presenter for the {@link MainActivity}.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
public class MainPresenter extends BasePresenter<MainView> {

    private final MainRepository mainRepository;
    //To separate disposables - for canceling request correctly
    private Disposable networkDisposable;
    private Disposable diskDisposable;

    @Inject
    MainPresenter(@NonNull final MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void attachView(@NonNull MainView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        stopSearch();
        stopLoadingCachedRepositories();
    }

    void searchForRepositories(@NonNull final String query) {
        networkDisposable = mainRepository.searchRepositories(query)
                .delay(2, TimeUnit.SECONDS, Schedulers.newThread()) //For those, who likes quickly change their minds
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2))) //2 threads for data loading
                .replay().autoConnect()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> {
                    //Show loading pb
                    if (isViewAttached()) {
                        getMvpView().showLoading(true);
                    }
                })
                .doAfterTerminate(() -> {
                    //After complete or error - hide loading pb
                    if (isViewAttached()) {
                        getMvpView().showLoading(false);
                    }
                })
                .subscribe(requestResult -> {
                    final Boolean result = requestResult.getData();
                    //If result != null - there is nothing to worry about
                    if (result != null) {
                        //If result == false - data not persisted
                        if (!result && isViewAttached()) {
                            getMvpView().showDataNotSaved();
                        }
                    } else {
                        //Something bad has happened - let's try to extract error message
                        final String errorMessage = requestResult.getErrorMessage();
                        if (errorMessage != null && isViewAttached()) {
                            getMvpView().showError(errorMessage);
                        }
                    }
                }, throwable -> {
                    //Something awful has happened - let's tell about this our users
                    if (isViewAttached()) {
                        getMvpView().showError();
                    }
                    Timber.e(throwable, "Exception occurred while searching for repositories!");
                });
    }

    void getCachedRepositories() {
        diskDisposable = mainRepository.getCachedRepositories()
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))//Reading from disk with 2 threads
                .replay().autoConnect()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryEntityList -> {
                    //If user hasn't ever use search
                    if (repositoryEntityList.isEmpty()) {
                        if (isViewAttached()) {
                            getMvpView().showCachedRepositoriesIsEmpty();
                        }
                    } else {
                        //If we loaded results, we, probably should show them
                        if (isViewAttached()) {
                            getMvpView().showSearchResults(repositoryEntityList);
                        }
                    }
                });
    }

    /**
     * <p>For canceling request we need just unsubscribe (dispose) from the Observable (Flowable).</p>
     * <p>See: https://github.com/square/retrofit/issues/1216</p>
     **/
    void stopSearch() {
        if (networkDisposable != null && !networkDisposable.isDisposed()) {
            networkDisposable.dispose();
        }
    }

    private void stopLoadingCachedRepositories() {
        if (diskDisposable != null && !diskDisposable.isDisposed()) {
            diskDisposable.dispose();
        }
    }
}