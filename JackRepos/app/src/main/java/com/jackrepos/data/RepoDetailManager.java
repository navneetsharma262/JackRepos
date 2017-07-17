package com.jackrepos.data;

import android.support.annotation.NonNull;

import com.jackrepos.model.Branch;
import com.jackrepos.model.Commit;
import com.jackrepos.model.Contributor;
import com.jackrepos.networking.services.RepoServiceAPI;
import com.jackrepos.ui.repodetail.RepoDetailFragment;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public class RepoDetailManager implements RepoDetailRepository {

    private RepoServiceAPI mRepoServiceApi;


    public RepoDetailManager(@NonNull RepoServiceAPI repoServiceAPI) {
        mRepoServiceApi = repoServiceAPI;
    }
    @Override
    public void getRepoDetails(@NonNull final LoadRepoDetailCallback callback,final int type,String url) {

        if(type== RepoDetailFragment.BRANCH_COUNT_REQUEST) {
            mRepoServiceApi.getRepoBranchesCount(new RepoServiceAPI.RepoServiceCallback<List<Branch>>() {
                @Override
                public void onLoaded(List<Branch> repos) {

                }

                @Override
                public void onLoaded(int count) {
                    callback.onRepoBranchesCount(count);
                }

                @Override
                public void onError(String errorMsg) {
                    callback.onError(errorMsg);
                }
            }, url);
        }
        else if(type== RepoDetailFragment.COMMIT_COUNT_REQUEST)
        {
            mRepoServiceApi.getRepoCommitsCount(new RepoServiceAPI.RepoServiceCallback<List<Commit>>() {
                @Override
                public void onLoaded(List<Commit> repos) {

                }

                @Override
                public void onLoaded(int count) {
                    callback.onRepoCommitsCount(count);
                }

                @Override
                public void onError(String errorMsg) {
                    callback.onError(errorMsg);
                }
            }, url);
        }
        else
        {
            mRepoServiceApi.getRepoContributorsCount(new RepoServiceAPI.RepoServiceCallback<List<Contributor>>() {
                @Override
                public void onLoaded(List<Contributor> repos) {

                }

                @Override
                public void onLoaded(int count) {
                    callback.onRepoContributorsCount(count);
                }

                @Override
                public void onError(String errorMsg) {

                }
            }, url);
        }
    }
}
