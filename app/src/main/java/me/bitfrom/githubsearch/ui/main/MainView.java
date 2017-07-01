package me.bitfrom.githubsearch.ui.main;

import android.support.annotation.NonNull;

import java.util.List;

import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;
import me.bitfrom.githubsearch.ui.base.BaseView;

/**
 * <p>View for the {@link MainActivity}.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
interface MainView extends BaseView {

    void showSearchResults(@NonNull List<RepositoryEntity> repositoryEntityList);

    void showCachedRepositoriesIsEmpty();

    void showLoading(boolean isLoading);

    void showDataNotSaved();

    void showError();

    void showError(@NonNull String errorMessage);
}