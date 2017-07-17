package com.jackrepos.networking.services;

import com.jackrepos.model.Branch;
import com.jackrepos.model.Commit;
import com.jackrepos.model.Contributor;
import com.jackrepos.model.Repo;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public interface RepoServiceAPI {

    interface RepoServiceCallback<T> {

        void onLoaded(T repos);
        void onLoaded(int count);
        void onError(String errorMsg);
    }

    void getRepos(RepoServiceCallback<List<Repo>> callback,String url, int offset);
    void getRepoBranchesCount(RepoServiceCallback<List<Branch>> callback,String url);
    void getRepoCommitsCount(RepoServiceCallback<List<Commit>> callback,String url);
    void getRepoContributorsCount(RepoServiceCallback<List<Contributor>> callback,String url);

}
