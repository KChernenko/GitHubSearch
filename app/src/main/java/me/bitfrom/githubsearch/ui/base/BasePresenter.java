package me.bitfrom.githubsearch.ui.base;

import android.support.annotation.NonNull;

/**
 * <p>Hides presenter boilerplate methods.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T mvpView;

    @Override
    public void attachView(@NonNull T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public T getMvpView() {
        return mvpView;
    }
}