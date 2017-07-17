package com.jackrepos.data;

import android.support.annotation.NonNull;

import com.jackrepos.model.Repo;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public interface RepoDetailRepository {

        interface LoadRepoDetailCallback {

        void onRepoBranchesCount(int count);
        void onRepoCommitsCount(int count);
        void onRepoContributorsCount(int count);
        void onError(String errorMsg);
    }

    void getRepoDetails(@NonNull LoadRepoDetailCallback callback,int type,String url);


}
