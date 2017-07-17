package com.jackrepos.networking.interfaces;

import com.jackrepos.model.Branch;
import com.jackrepos.model.Commit;
import com.jackrepos.model.Contributor;
import com.jackrepos.model.Repo;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Navneet on 13-07-2017.
 */


public interface APIServiceCall {

    @GET("repos?per_page=30")
    Call<List<Repo>> getRepos(@Query("page") int start);
    @GET("?per_page=1")
    Call<List<Contributor>> getRepoContributors();
    @GET("?per_page=1")
    Call<List<Commit>> getRepoCommits();
    @GET("?per_page=1")
    Call<List<Branch>> getRepoBranches();

}
