package com.example.lenovo.reportdesk;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class ReportAdapter extends RealmRecyclerViewAdapter<Report, ReportAdapter.ReportViewHolder> {
    OnTicketItemInteraction mListener;
    public ReportAdapter(@Nullable OrderedRealmCollection<Report> data, boolean autoUpdate, OnTicketItemInteraction listener) {
        super(data, autoUpdate);
        mListener = listener;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_report_view, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, final int position) {
        Report report = getData().get(position);
        holder.tvAssetName.setText(report.asset_name);
        holder.tvLocation.setText(report.location);

        holder.vContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.didSelectTicket(getData().get(position));
            }
        });
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder{
        View vContainer;
        TextView tvAssetName;
        TextView tvLocation;

        public ReportViewHolder(View itemView) {
            super(itemView);
            tvAssetName = itemView.findViewById(R.id.tv_Title);
            tvLocation = itemView.findViewById(R.id.tv_location);
            vContainer = itemView.findViewById(R.id.container);
        }
    }

    public interface OnTicketItemInteraction{
        void didSelectTicket(Report report);
    }
}