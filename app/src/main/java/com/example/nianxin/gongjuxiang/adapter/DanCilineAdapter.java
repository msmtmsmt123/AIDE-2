package com.example.nianxin.gongjuxiang.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nianxin.gongjuxiang.R;
import java.util.List;

/**
 * Created by nianxin on 2017/4/29.
 */

public class DanCilineAdapter extends RecyclerView.Adapter<DanCilineAdapter.ViewHolder>{

    private List<DanCi> danCiList;
    private RecyclerView recyclerView;
    private boolean vis=true,vis2=true;

    public DanCilineAdapter (List<DanCi>dcs,boolean vis,boolean vis2){
        danCiList=dcs;
        this.vis=vis;
        this.vis2=vis2;
    }

    @Override
    public DanCilineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_danciline,parent,false);
        DanCilineAdapter.ViewHolder holder=new DanCilineAdapter.ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(DanCilineAdapter.ViewHolder holder, int position) {
        DanCi danci=danCiList.get(position);
        if (vis==true) {
            holder.dancilinename.setText(danci.getName());
        }
        if (vis2==true) {
            holder.dancilinetext.setText(danci.getText());
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return danCiList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public static TextView dancilinename,dancilinetext;
        public ViewHolder(View view) {
            super(view);
            dancilinename=(TextView)view.findViewById(R.id.TV1);
            dancilinetext=(TextView)view.findViewById(R.id.TV2);
        }
    }












}
