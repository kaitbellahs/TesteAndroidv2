package com.resourceit.app.Adaptera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.resourceit.app.R;
import com.resourceit.app.holders.StatmentHolder;
import com.resourceit.app.models.StatmentModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class StatmentsAdapter extends RecyclerView.Adapter<StatmentHolder> {

    private List<StatmentModel> statments;
    private final LayoutInflater mInflater;

    public StatmentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StatmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View itemView = mInflater.inflate(R.layout.statment_item, parent, false);
        return new StatmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StatmentHolder holder, int position) {
        if (statments != null) {
            StatmentModel current = statments.get(position);

            holder.title.setText(current.getTitle());
            holder.desc.setText(current.getDesc());
            holder.date.setText(current.getDate());
            holder.value.setText("R$"+current.getValue());
        }
    }

    void setStatments(List<StatmentModel> statments){
        this.statments = statments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return statments != null ? statments.size() : 0;
    }

    public void insertItem(List<StatmentModel> statments) {
        this.statments = statments;
        notifyDataSetChanged();
    }

}
