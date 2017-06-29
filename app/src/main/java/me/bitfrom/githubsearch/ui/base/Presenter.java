package me.bitfrom.githubsearch.ui.base;

import android.support.annotation.NonNull;

/**
 * <p>Base interface that provides helper methods for handling view state.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
public interface Presenter<V extends BaseView> {

    /**
     * Attaches view to presenter.
     *
     * @param view the view
     */
    void attachView(@NonNull V view);

    /**
     * Checks if view is attached.
     *
     * @return true if view is attached
     */
    boolean isViewAttached();

    /**
     * Detach view from presenter.
     */
    void detachView();

}