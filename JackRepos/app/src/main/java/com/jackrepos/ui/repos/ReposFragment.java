package com.jackrepos.ui.repos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jackrepos.R;
import com.jackrepos.adapter.ReposAdapter;
import com.jackrepos.data.RepoRepositories;
import com.jackrepos.model.Repo;
import com.jackrepos.networking.services.RepoServiceAPIImp;
import com.jackrepos.ui.BaseFragment;
import com.jackrepos.ui.custom.EndlessRecyclerOnScrollListener;
import com.jackrepos.ui.repodetail.RepoDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */
public class ReposFragment extends BaseFragment<ReposContractor.ReposPresenter> implements ReposContractor.ReposView, ReposAdapter.RepoItemListener {

    private RecyclerView rvRepos;
    private SwipeRefreshLayout swlMain;
    private ReposAdapter mRepoAdapter;
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;

    public static ReposFragment newInstance() {
        return new ReposFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repo_list, container, false);
        rvRepos = (RecyclerView)rootView.findViewById(R.id.recyclerView);

        swlMain = (SwipeRefreshLayout)rootView.findViewById(R.id.srl_main);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvRepos.setHasFixedSize(true);
        rvRepos.setItemAnimator(new DefaultItemAnimator());
        rvRepos.setLayoutManager(linearLayoutManager);
        mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!mRepoAdapter.isLoading()) {
                    mPresenter.loadRepos(true, false);
                }
            }
        };
        rvRepos.addOnScrollListener(mEndlessRecyclerOnScrollListener);
        swlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mEndlessRecyclerOnScrollListener.reset();
                mPresenter.loadRepos(false, true);
            }
        });

        setProgressIndicator(true);

        return rootView;
    }

    @Override
    public void showReposLoading(boolean loading) {
       if (mRepoAdapter != null) mRepoAdapter.setLoading(loading);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadRepos(false, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPresenter = new ReposPresenter(RepoRepositories.getRepoManager(new RepoServiceAPIImp()));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showRepos(List<Repo> repos) {



        setProgressIndicator(false);
        swlMain.setRefreshing(false);
        if (mRepoAdapter == null) {
            mRepoAdapter = new ReposAdapter(new ArrayList<>(repos), this);
            rvRepos.setAdapter(mRepoAdapter);
        }

        mRepoAdapter.replaceData(new ArrayList<Repo>(repos));
    }

    @Override
    public void showRepoDetailUi(Repo repo) {

        /*
        Replacing RepoFragment with RepoDetailFragment
        */

        RepoDetailFragment repoDetailFragment = new RepoDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("repo",repo);
        repoDetailFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.fragment_container,  repoDetailFragment, "RepoDetailFragment").addToBackStack("RepoDetailFragment").commit();
    }

    @Override
    public void showError(String errorMsg) {
        setProgressIndicator(false);
        Toast.makeText(getActivity(),errorMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRepoClick(Repo clickedRepo) {
        mPresenter.openRepoDetails(clickedRepo);
    }

}
