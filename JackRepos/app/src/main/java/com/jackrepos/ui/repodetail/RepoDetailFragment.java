package com.jackrepos.ui.repodetail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jackrepos.R;
import com.jackrepos.data.RepoDetailRepositories;
import com.jackrepos.model.Repo;
import com.jackrepos.networking.services.RepoServiceAPIImp;
import com.jackrepos.ui.BaseFragment;

/**
 * Created by Navneet on 13-07-2017.
 */
public class RepoDetailFragment extends BaseFragment<RepoDetailContractor.RepoDetailPresenter> implements RepoDetailContractor.RepoDetailView {


    public static RepoDetailFragment newInstance() {
        return new RepoDetailFragment();
    }


    private TextView repoIdTextView;
    private TextView repoNameTextView;
    private TextView descriptionTextView;
    private TextView createdAtTextView;
    private TextView watchersCountTextView;
    private TextView stargazersCountTextView;
    private TextView forksCountTextView;
    private TextView openIssuesCountTextView;
    private TextView branchCountTextView;
    private TextView commitCountTextView;
    private TextView contributorCountTextView;

    private Repo repo;
    private View rootView;

    public static final int BRANCH_COUNT_REQUEST=1;
    public static final int COMMIT_COUNT_REQUEST=2;
    public static final int CONTRIBUTER_COUNT_REQUEST=3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_repo_detail, container, false);

        repo= (Repo) getArguments().getSerializable("repo");
        initView();
        setRepoDetails();
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is Repo detail screen.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPresenter = new RepoDetailPresenter(RepoDetailRepositories.getRepoManager(new RepoServiceAPIImp()));
        setProgressIndicator(true);


        mPresenter.loadRepoDetails(BRANCH_COUNT_REQUEST,repo.getBranches_url().split("\\{")[0]);//first api call for branch count
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void showRepoContributors(int count) {
        Log.v("count",count+"");
        contributorCountTextView.setText("Contributor Count :"+count);
        setProgressIndicator(false);
    }

    @Override
    public void showRepoCommits(int count) {
        commitCountTextView.setText("Commit Count :"+count);
        mPresenter.loadRepoDetails(CONTRIBUTER_COUNT_REQUEST,repo.getContributors_url());//Api call for Contributors count
    }

    @Override
    public void showRepoBranches(int count) {
        branchCountTextView.setText("Branch Count :"+count);
        mPresenter.loadRepoDetails(COMMIT_COUNT_REQUEST,repo.getCommits_url().split("\\{")[0]);//Api call for commits count
    }

    @Override
    public void showError(String errorMsg) {
        setProgressIndicator(false);
        Toast.makeText(getActivity(),errorMsg,Toast.LENGTH_LONG).show();
    }

    /*
         finding views
        */

    private void initView()
    {
        repoIdTextView = (TextView) rootView.findViewById(R.id.repoIdTextView);
        repoNameTextView = (TextView) rootView.findViewById(R.id.repoNameTextView);
        descriptionTextView = (TextView) rootView.findViewById(R.id.descriptionTextView);
        createdAtTextView = (TextView) rootView.findViewById(R.id.createdAtTextView);

        watchersCountTextView = (TextView) rootView.findViewById(R.id.watchersCountTextView);
        stargazersCountTextView = (TextView) rootView.findViewById(R.id.stargazersCountTextView);
        forksCountTextView = (TextView) rootView.findViewById(R.id.forksCountTextView);
        openIssuesCountTextView = (TextView) rootView.findViewById(R.id.openIssuesCountTextView);
        branchCountTextView = (TextView) rootView.findViewById(R.id.branchCountTextView);
        commitCountTextView = (TextView) rootView.findViewById(R.id.commitCountTextView);
        contributorCountTextView = (TextView) rootView.findViewById(R.id.contributorCountTextView);
    }

    /*
         setting repo details data
        */

    private void setRepoDetails()
    {
        repoIdTextView.setText(getResources().getString(R.string.repoId)+repo.getId());
        repoNameTextView.setText(getResources().getString(R.string.repoName)+repo.getName());
        createdAtTextView.setText(getResources().getString(R.string.createdAt)+repo.getCreated_at());
        descriptionTextView.setText(getResources().getString(R.string.description)+repo.getDescription());
        watchersCountTextView.setText(getResources().getString(R.string.watchersCount)+repo.getWatchers_count());
        stargazersCountTextView.setText(getResources().getString(R.string.stargazersCount)+repo.getStargazers_count());
        forksCountTextView.setText(getResources().getString(R.string.forksCount)+repo.getForks_count());
        openIssuesCountTextView.setText(getResources().getString(R.string.openIssuesCount)+repo.getOpen_issues_count());
    }

}
