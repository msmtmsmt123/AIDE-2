package com.example.nianxin.gongjuxiang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.db.Danci;

import java.util.List;

/**
 * Created by nianxin on 2017/4/25.
 */

public class DanCiAdapter extends RecyclerView.Adapter<DanCiAdapter.ViewHolder> {
    private List<DanCi>danCiList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView danciname,dancitext;
        public ViewHolder(View view) {
            super(view);
            danciname=(TextView)view.findViewById(R.id.danci_name);
            dancitext=(TextView)view.findViewById(R.id.danci_text);
        }
    }
    public DanCiAdapter (List<DanCi>dancis){
        danCiList=dancis;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dancisousuo,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DanCi danci=danCiList.get(position);
        holder.danciname.setText(danci.getName());
        holder.dancitext.setText(danci.getText());
    }

    @Override
    public int getItemCount() {
        return danCiList.size();
    }



}
