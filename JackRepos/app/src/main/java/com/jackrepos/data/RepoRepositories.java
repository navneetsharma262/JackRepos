package com.jackrepos.data;

import android.support.annotation.NonNull;

import com.jackrepos.networking.services.RepoServiceAPIImp;
import com.jackrepos.networking.services.RepoServiceAPI;

/**
 * Created by Navneet on 13-07-2017.
 */
public class RepoRepositories {

    private static RepoRepository repository = null;

    private RepoRepositories() {
    }

    public synchronized static RepoRepository getRepoManager(@NonNull RepoServiceAPI repoServiceAPI) {
        if (repository == null) {
            repository = new ReposManager(repoServiceAPI);
        }
        return repository;
    }

    public synchronized static void changeOffLineRepository(boolean online) {
        ((ReposManager) repository).switchAPILayer(online ? new RepoServiceAPIImp() : new RepoServiceAPIImp());
    }

}

