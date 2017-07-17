package com.jackrepos.ui.repodetail;

import android.support.annotation.NonNull;

import com.jackrepos.data.RepoDetailRepository;
import com.jackrepos.data.RepoRepository;
import com.jackrepos.model.Repo;
import com.jackrepos.ui.repos.ReposContractor;

import java.util.List;

/**
 * Created by Navneet on 14-07-2017.
 */

public class RepoDetailPresenter  implements RepoDetailContractor.RepoDetailPresenter<RepoDetailContractor.RepoDetailView> {

    private RepoDetailContractor.RepoDetailView mReposView;
    private RepoDetailRepository mReposRepository;

    public RepoDetailPresenter(@NonNull RepoDetailRepository repoRepository){
        mReposRepository = repoRepository;
    }

    @Override
    public void loadRepoDetails(int type,String url) {

        mReposRepository.getRepoDetails(new RepoDetailRepository.LoadRepoDetailCallback() {
            @Override
            public void onRepoBranchesCount(int count) {
                mReposView.showRepoBranches(count);
            }

            @Override
            public void onRepoCommitsCount(int count) {
                mReposView.showRepoCommits(count);
            }

            @Override
            public void onRepoContributorsCount(int count) {
                mReposView.showRepoContributors(count);
            }

            @Override
            public void onError(String errorMsg) {
                mReposView.showError(errorMsg);
            }


        },type,url);
    }

    @Override
    public void attachView(RepoDetailContractor.RepoDetailView mReposView) {
        this.mReposView = mReposView;
    }
    @Override
    public void detachView() {
        mReposView = null;
    }

}
