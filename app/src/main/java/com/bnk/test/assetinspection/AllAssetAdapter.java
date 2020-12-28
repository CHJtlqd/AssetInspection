package com.bnk.test.assetinspection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AllAssetAdapter extends BaseAdapter {
    private Context ctx;
    private MyData[] data;

    public AllAssetAdapter(Context ctx, MyData[] data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.asset_list, viewGroup, false);
        }

        TextView ast_cd = (TextView) view.findViewById(R.id.ast_cd_list);
        TextView ast_nm = (TextView) view.findViewById(R.id.ast_list_nm);
        TextView empe_nm = (TextView) view.findViewById(R.id.empe_nm_list);

        ast_cd.setText(data[i].ast_cd);
        ast_nm.setText(data[i].ast_nm);
        empe_nm.setText(data[i].empe_nm);

        return view;
    }
}
