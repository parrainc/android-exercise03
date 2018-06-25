package com.carlosparra.githubjobs.githubjobsapi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlosparra.githubjobs.githubjobsapi.R;
import com.carlosparra.githubjobs.githubjobsapi.models.Job;

import java.util.List;

public class JobCustomAdapter extends
        RecyclerView.Adapter<JobCustomAdapter.JobCustomAdapterViewHolder>{

    private List<Job> jobList;
    private Context context;
    private OnItemClickListener clickListener;

    public JobCustomAdapter(List<Job> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
    }

    @Override
    public JobCustomAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.job_list_item, parent, false);

        return new JobCustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobCustomAdapterViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.bind(job);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void updateDataSet(List<Job> jobs) {
        this.jobList = jobs;
        notifyDataSetChanged();
    }

    class JobCustomAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView textViewJobTitle;
        TextView textViewJobLocation;
        TextView textViewJobPublishedDate;
        ImageView imageViewCompanyLogo;

        JobCustomAdapterViewHolder(View itemView) {
            super(itemView);

            textViewJobTitle = itemView.findViewById(R.id.tv_job_title);
            textViewJobLocation = itemView.findViewById(R.id.tv_job_location);
            textViewJobPublishedDate = itemView.findViewById(R.id.tv_published_date);
            imageViewCompanyLogo = itemView.findViewById(R.id.iv_company_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(view, position);
                        }
                    }
                }
            });
        }

        void bind(Job job) {
            textViewJobTitle.setText(job.getTitle());
            textViewJobLocation.setText(job.getLocation());
            textViewJobPublishedDate.setText(job.getCreatedDate());

//            if (job.getX_company().getLogo() == null) {
//                //imageViewCompanyLogo.setImageResource(R.);
//            }
        }
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
