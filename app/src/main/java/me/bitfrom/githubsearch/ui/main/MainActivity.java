package me.bitfrom.githubsearch.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import me.bitfrom.githubsearch.R;
import me.bitfrom.githubsearch.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    protected MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getActivityComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }
}