package com.bnk.test.assetinspection;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;
import com.bnk.test.assetinspection.Util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends BaseAdapter implements Filterable {
    private Context ctx;
    private List<InfoAndItmqAndFaxmCgp> data;
    private List<InfoAndItmqAndFaxmCgp> filteredData;
    private Filter listFilter;

    public CardAdapter(Context ctx, List<InfoAndItmqAndFaxmCgp> data) {
        this.ctx = ctx;
        this.data = data;
        filteredData = data;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public InfoAndItmqAndFaxmCgp getItem(int i) {
        return filteredData.get(i);
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

        TextView ast_cd = (TextView) view.findViewById(R.id.ast_cd);
        TextView ast_nm = (TextView) view.findViewById(R.id.ast_nm);
        TextView empe_nm = (TextView) view.findViewById(R.id.empe_nm);
        TextView check_date = (TextView) view.findViewById(R.id.check_date);
        TextView checked = (TextView) view.findViewById(R.id.checked);

        ast_cd.setText(filteredData.get(i).astCd + "-" + filteredData.get(i).astDtlCd);
        ast_nm.setText(filteredData.get(i).astNm);
        empe_nm.setText(filteredData.get(i).empNm);

        if (filteredData.get(i).vdDt == null || filteredData.get(i).vdDt.isEmpty()) {
            check_date.setText("-");
            checked.setTextColor(Color.RED);
            checked.setText("미확인");
        } else {
            check_date.setText(DateUtil.dateFormat(filteredData.get(i).vdDt));
            checked.setTextColor(Color.BLUE);
            checked.setText("확인");
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {
        @Override
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            Filter.FilterResults results = new FilterResults();
            constraint = constraint.toString().replace(" ", "");
            String[] search = constraint.toString().split("##");
            String searchFormat = search[0].trim();
            String searchText = null;
            if (search.length == 2) {
                searchText = search[1].trim();
            }

            if (search.length == 1 || searchText == null) { // 검색어가 빈값인 경우
                results.values = data;
                results.count = data.size();
            } else {    // 검색을 한 경우
                ArrayList<InfoAndItmqAndFaxmCgp> itemList = new ArrayList<>();
                for (InfoAndItmqAndFaxmCgp item : data) {
                    if (searchFormat.equals("확인여부")) {
                        if (searchText.equals("확인") && item.vdDt != null) {
                            itemList.add(item);
                        } else if (searchText.equals("미확인") && item.vdDt == null) {
                            itemList.add(item);
                        }
                    } else if (searchFormat.equals("담당부서") && item.empDeptNm.contains(searchText)) {
                        itemList.add(item);
                    } else if (searchFormat.equals("담당자") && item.empNm.contains(searchText)) {
                        itemList.add(item);
                    } else if (searchFormat.equals("자산명") && item.astNm.contains(searchText)) {
                        itemList.add(item);
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listView by filtered data list.
            filteredData = (ArrayList<InfoAndItmqAndFaxmCgp>) results.values;
            // notify
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
