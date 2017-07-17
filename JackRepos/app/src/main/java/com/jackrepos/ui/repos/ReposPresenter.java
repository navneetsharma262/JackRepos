package com.jackrepos.ui.repos;

import android.support.annotation.NonNull;

import com.jackrepos.data.RepoRepository;
import com.jackrepos.model.Repo;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public class ReposPresenter implements ReposContractor.ReposPresenter<ReposContractor.ReposView> {

    private ReposContractor.ReposView mReposView;
    private RepoRepository mReposRepository;

    public ReposPresenter(@NonNull RepoRepository repoRepository){
        mReposRepository = repoRepository;
    }

    @Override
    public void loadRepos(boolean loadMore, boolean forceUpdate) {
        mReposView.showReposLoading(true);
        mReposRepository.getRepos(new RepoRepository.LoadReposCallback() {
            @Override
            public void onReposLoaded(List<Repo> repos) {
                mReposView.showReposLoading(false);
                mReposView.showRepos(repos);
            }

            @Override
            public void onError(String errorMsg) {
                mReposView.showError(errorMsg);
            }
        }, loadMore, forceUpdate);
    }

    @Override
    public void openRepoDetails(@NonNull Repo repoClicked) {
        //Some validations
        mReposView.showRepoDetailUi(repoClicked);
    }

    @Override
    public void attachView(ReposContractor.ReposView mReposView) {
        this.mReposView = mReposView;
    }

    @Override
    public void detachView() {
        mReposView = null;
    }

}
