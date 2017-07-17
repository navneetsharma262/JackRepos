/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Carlos Piñan
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jackrepos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jackrepos.R;
import com.jackrepos.model.Repo;

import java.util.List;

/**
 * Created by Navneet on 13-07-2017.
 */

public class ReposAdapter extends LoadMoreBaseAdapter<Repo, ReposAdapter.RepoViewHolder> {

    private RepoItemListener mItemListener;
    private LayoutInflater inflater;
    private Context context;
    public ReposAdapter(List<Repo> repos, RepoItemListener itemListener) {
        setList(repos);
        mItemListener = itemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
         context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View repoView = inflater.inflate(R.layout.item_list_repos, parent, false);
        return new RepoViewHolder(repoView, mItemListener);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_DATA) {
            bindRepoViewHolder((RepoViewHolder)holder, position);
        }
    }

    private void bindRepoViewHolder(RepoViewHolder repoViewHolder, int position) {
        Repo repo = data.get(position);
        repoViewHolder.repoIdTextView.setText(context.getResources().getString(R.string.repoId)+repo.getId());
        repoViewHolder.repoNameTextView.setText(context.getResources().getString(R.string.repoName)+repo.getName());
        repoViewHolder.createdAtTextView.setText(context.getResources().getString(R.string.createdAt)+repo.getCreated_at());
        repoViewHolder.descriptionTextView.setText(context.getResources().getString(R.string.description)+repo.getDescription());


    }

       /*
         Replace list data and notify list
        */

    public void replaceData(List<Repo> repos) {
        data.clear();
        setList(repos);
        notifyDataSetChanged();
    }

    private void setList(List<Repo> repos) {
        this.data = repos;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView repoIdTextView;
        public TextView repoNameTextView;
        public TextView descriptionTextView;
        public TextView createdAtTextView;


        private RepoItemListener mItemListener;

        public RepoViewHolder(View itemView, RepoItemListener listener) {
            super(itemView);
            mItemListener = listener;
            repoIdTextView = (TextView) itemView.findViewById(R.id.repoIdTextView);
            repoNameTextView = (TextView) itemView.findViewById(R.id.repoNameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            createdAtTextView = (TextView) itemView.findViewById(R.id.createdAtTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Repo repo = getItem(position);
            mItemListener.onRepoClick(repo);
        }
    }

    /*
         Interface for click event on list item
        */


    public interface RepoItemListener {

        void onRepoClick(Repo clickedRepo);

    }

}
