package com.example.nianxin.gongjuxiang.my;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.adapter.DanCi;
import com.example.nianxin.gongjuxiang.adapter.DanCiAdapter;
import com.example.nianxin.gongjuxiang.adapter.DanCilineAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<DanCi>dcs;
    public MyFragment(List<DanCi>dcs){
        this.dcs=dcs;
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 19:47.
    * action:实现了加载布局，传递得到的集合到适配器上
    */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.Recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        DanCilineAdapter adapter = new DanCilineAdapter(dcs);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}