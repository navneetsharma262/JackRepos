package com.jackrepos.networking.services;

import com.jackrepos.model.Branch;
import com.jackrepos.model.Commit;
import com.jackrepos.model.Contributor;
import com.jackrepos.model.Repo;
import com.jackrepos.util.API;
import com.squareup.okhttp.Headers;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Navneet on 13-07-2017.
 */
public class RepoServiceAPIImp implements RepoServiceAPI {

    @Override
    public void getRepos(final RepoServiceCallback<List<Repo>> callback,String url, int offset) {
        Call<List<Repo>> callListRepos = API.get().getRetrofitService(url).getRepos(offset);
        callListRepos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Response<List<Repo>> response) {

                if(response==null)
                {
                    callback.onError("Server Error");
                }
                else {
                    callback.onLoaded(response.body());
                }



            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Implement generic alert dialog.
                callback.onError("Error :"+t.getMessage());
            }
        });
    }

    @Override
    public void getRepoBranchesCount(final RepoServiceCallback<List<Branch>> callback,String url) {
        Call<List<Branch>> callListRepos = API.get().getRetrofitService(url).getRepoBranches();
        callListRepos.enqueue(new Callback<List<Branch>>() {
            @Override
            public void onResponse(Response<List<Branch>> response) {

                if(response==null)
                {
                    callback.onError("Server Error");
                }
                else {
                    int count = getCountFromHeaders(response.headers().get("Link"));
                    callback.onLoaded(count);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Implement generic alert dialog.
                callback.onError("Error :"+t.getMessage());
            }
        });
    }

    @Override
    public void getRepoCommitsCount(final RepoServiceCallback<List<Commit>> callback,String url) {

        Call<List<Commit>> callListRepos = API.get().getRetrofitService(url).getRepoCommits();
        callListRepos.enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Response<List<Commit>> response) {

                if(response==null)
                {
                    callback.onError("Server Error");
                }
                else {
                    int count = getCountFromHeaders(response.headers().get("Link"));
                    callback.onLoaded(count);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Implement generic alert dialog.
                callback.onError("Error :"+t.getMessage());
            }
        });
    }

    @Override
    public void getRepoContributorsCount(final RepoServiceCallback<List<Contributor>> callback,String url) {
        Call<List<Contributor>> callListRepos = API.get().getRetrofitService(url).getRepoContributors();
        callListRepos.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response) {
                if(response==null)
                {
                    callback.onError("Server Error");
                }
                else {
                    int count = getCountFromHeaders(response.headers().get("Link"));
                    callback.onLoaded(count);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Implement generic alert dialog.
                callback.onError("Error :"+t.getMessage());
            }
        });
    }

    /*
    Getting total count from Link header
    */

    private int getCountFromHeaders(String header)
    {
        try
        {
            String[] tempCount=header.split("&page=");
            String[] temp=tempCount[2].split(">");
            return Integer.parseInt(temp[0]);
        }
        catch (Exception e)
        {
            return 0;
        }


    }

}
