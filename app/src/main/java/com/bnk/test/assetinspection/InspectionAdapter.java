package com.bnk.test.assetinspection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InspectionAdapter extends BaseAdapter {
    private Context ctx;
    private MyData[] data;

    public InspectionAdapter(Context ctx, MyData[] data) {
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
            view = inflater.inflate(R.layout.asset_inspection, viewGroup, false);
        }

        TextView ast_cd = (TextView) view.findViewById(R.id.ast_cd);
        TextView ast_nm = (TextView) view.findViewById(R.id.ast_nm);
        TextView empe_nm = (TextView) view.findViewById(R.id.empe_nm);
        TextView check_date = (TextView) view.findViewById(R.id.check_date);
        TextView checked = (TextView) view.findViewById(R.id.checked);

        ast_cd.setText(data[i].ast_cd);
        ast_nm.setText(data[i].ast_nm);
        empe_nm.setText(data[i].empe_nm);

        if (data[i].check_date.equals("")) {
            check_date.setText("-");
            checked.setText("미확인");
        } else {
            check_date.setText(data[i].check_date);
            checked.setText("확인");
        }

        return view;
    }
}
