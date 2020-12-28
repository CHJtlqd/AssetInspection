package com.bnk.test.assetinspection;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.List;

public class InspectionAdapter extends BaseAdapter {
    private Context ctx;
    private List<InfoAndItmqAndFaxmCgp> data;

    public InspectionAdapter(Context ctx, List<InfoAndItmqAndFaxmCgp> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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

        ast_cd.setText(data.get(i).astCd+"-"+data.get(i).astDtlCd);
        ast_nm.setText(data.get(i).astNm);
        empe_nm.setText(data.get(i).empNm);

        if (data.get(i).vdDt == null || data.get(i).vdDt.isEmpty()) {
            check_date.setText("-");
            checked.setText("미확인");
            checked.setTextColor(Color.parseColor("#CB2B11"));
        } else {
            check_date.setText(data.get(i).vdDt);
            checked.setText("확인");
            checked.setTextColor(Color.parseColor("#111111"));
        }

        return view;
    }
}
