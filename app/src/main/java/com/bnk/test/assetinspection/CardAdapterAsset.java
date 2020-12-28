package com.bnk.test.assetinspection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

public class CardAdapterAsset extends BaseAdapter {
    private Context ctx;
    private List<InfoAndItmqAndFaxmCgp> data;

    public CardAdapterAsset(Context ctx, List<InfoAndItmqAndFaxmCgp> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public InfoAndItmqAndFaxmCgp getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.info_list, viewGroup, false);
        }

        TextView ast_cd = (TextView) view.findViewById(R.id.asset_ast_dtl_cd);
        TextView ast_nm = (TextView) view.findViewById(R.id.asset_ast_nm);
        TextView empe_nm = (TextView) view.findViewById(R.id.asset_empe_nm);

        ast_cd.setText(data.get(i).astCd+"-"+data.get(i).astDtlCd);
        ast_nm.setText(data.get(i).astNm);
        empe_nm.setText(data.get(i).empNm);

        return view;
    }
}
