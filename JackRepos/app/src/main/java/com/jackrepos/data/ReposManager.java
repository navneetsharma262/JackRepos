package com.jackrepos.data;

import android.support.annotation.NonNull;

import com.jackrepos.model.Repo;
import com.jackrepos.networking.services.RepoServiceAPI;
import com.jackrepos.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public class ReposManager implements RepoRepository {

    private RepoServiceAPI mRepoServiceApi;
    private List<Repo> mRepos = new ArrayList<>();

    public ReposManager(@NonNull RepoServiceAPI repoServiceAPI) {
        mRepoServiceApi = repoServiceAPI;
    }

    @Override
    public void getRepos(@NonNull final LoadReposCallback callback, boolean loadMore, boolean forceUpdate) {
        if (forceUpdate) {
            mRepos.clear();
        }
        if (!loadMore && !mRepos.isEmpty()) {
            callback.onReposLoaded(mRepos);
        } else {
            mRepoServiceApi.getRepos(new RepoServiceAPI.RepoServiceCallback<List<Repo>>() {
                @Override
                public void onLoaded(List<Repo> repos) {
                    if(repos==null)
                    {
                        callback.onError("Server Error");
                    }
                    else {
                        mRepos.addAll(new ArrayList<>(repos));
                        callback.onReposLoaded(mRepos);
                    }
                }

                @Override
                public void onLoaded(int count) {

                }

                @Override
                public void onError(String errorMsg) {
                    callback.onError(errorMsg);
                }
            }, Constants.GET_USER_REPO_URL, mRepos.size());
        }
    }

    public void switchAPILayer(@NonNull RepoServiceAPI repoServiceAPI) {
        this.mRepoServiceApi = repoServiceAPI;
    }
}
