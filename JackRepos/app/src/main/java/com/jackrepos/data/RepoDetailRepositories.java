package com.jackrepos.data;

import android.support.annotation.NonNull;

import com.jackrepos.networking.services.RepoServiceAPI;
import com.jackrepos.networking.services.RepoServiceAPIImp;

/**
 * Created by Navneet on 13-07-2017.
 */
public class RepoDetailRepositories {

    private static RepoDetailRepository repository = null;

    private RepoDetailRepositories() {
    }

    public synchronized static RepoDetailRepository getRepoManager(@NonNull RepoServiceAPI repoServiceAPI) {
        if (repository == null) {
            repository = new RepoDetailManager(repoServiceAPI);
        }
        return repository;
    }
}

