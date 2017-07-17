package com.jackrepos.data;

import android.support.annotation.NonNull;

import com.jackrepos.model.Repo;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public interface RepoRepository {

        interface LoadReposCallback {

        void onReposLoaded(List<Repo> repos);
        void onError(String errorMsg);
    }

    void getRepos(@NonNull LoadReposCallback callback, boolean loadMore, boolean forceUpdate);

}
