package me.bitfrom.githubsearch.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import javax.inject.Inject;

import me.bitfrom.githubsearch.R;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;
import me.bitfrom.githubsearch.databinding.MainActivityBinding;
import me.bitfrom.githubsearch.ui.base.BaseActivity;
import me.bitfrom.githubsearch.utils.NetworkStateHelper;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    protected MainPresenter presenter;
    @Inject
    protected NetworkStateHelper networkStateHelper;
    @Inject
    protected RepositoriesAdapter adapter;

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        getActivityComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        initRecyclerView();
        initSearchListener();
        initStopSearchListener();
        presenter.getCachedRepositories();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showSearchResults(@NonNull List<RepositoryEntity> repositoryEntityList) {
        binding.emptyRepositoryList.setVisibility(View.GONE);
        binding.repositoriesList.setVisibility(View.VISIBLE);
        adapter.setItemsList(repositoryEntityList);
    }

    @Override
    public void showCachedRepositoriesIsEmpty() {
        binding.repositoriesList.setVisibility(View.GONE);
        binding.emptyRepositoryList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            binding.searchingPb.setVisibility(View.VISIBLE);
        } else {
            binding.searchingPb.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDataNotSaved() {
        Snackbar.make(binding.mainActivityRootLayout,
                getString(R.string.data_not_saved_msg),
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Snackbar.make(binding.mainActivityRootLayout,
                getString(R.string.unknown_error_msg),
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Snackbar.make(binding.mainActivityRootLayout, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    private void initRecyclerView() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        binding.repositoriesList.setLayoutManager(new LinearLayoutManager(this));
        binding.repositoriesList.addItemDecoration(dividerItemDecoration);
        binding.repositoriesList.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            if (networkStateHelper.isNetworkAvailable()) {
                final RepositoryEntity clickedItem = adapter.getItemByPosition(position);
                showDialogBrowser(clickedItem.getName(), clickedItem.getRepositoryUrl());
            } else {
                showNoInternet();
            }
        });
    }

    private void initSearchListener() {
        binding.searchRepositoriesBtn.setOnClickListener(v -> {
            if (networkStateHelper.isNetworkAvailable()) {
                final String query = binding.searchEt.getText().toString();
                presenter.searchForRepositories(query);
            } else {
                showNoInternet();
            }
        });
    }

    private void initStopSearchListener() {
        binding.stopSearchBtn.setOnClickListener(v -> {
            binding.searchingPb.setVisibility(View.GONE);
            presenter.stopSearch();
        });
    }

    private void showDialogBrowser(@NonNull final String repositoryName,
                                   @NonNull final String url) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(repositoryName);

        WebView wv = new WebView(this);
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,  WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton(getString(R.string.close_dialog_btn), (dialog, id) -> dialog.dismiss());
        alert.show();
    }

    private void showNoInternet() {
        Snackbar.make(binding.mainActivityRootLayout, getString(R.string.network_error_msg), Snackbar.LENGTH_LONG).show();
    }
}