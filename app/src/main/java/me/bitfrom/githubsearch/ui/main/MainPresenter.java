package me.bitfrom.githubsearch.ui.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.bitfrom.githubsearch.core.MainRepository;
import me.bitfrom.githubsearch.ui.base.BasePresenter;

/**
 * <p>Presenter for the {@link MainActivity}.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
public class MainPresenter extends BasePresenter<MainView> {

    private final MainRepository mainRepository;
    private final CompositeDisposable compositeDisposable;

    @Inject
    MainPresenter(@NonNull final MainRepository mainRepository) {
        this.mainRepository = mainRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(@NonNull MainView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}