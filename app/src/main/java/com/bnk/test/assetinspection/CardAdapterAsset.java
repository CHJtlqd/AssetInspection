package com.bnk.test.assetinspection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.bnk.test.assetinspection.Entity.InfoAndItmqAndFaxmCgp;

import java.util.ArrayList;
import java.util.List;

public class CardAdapterAsset extends BaseAdapter implements Filterable {
    private Context ctx;
    private List<InfoAndItmqAndFaxmCgp> data;
    private List<InfoAndItmqAndFaxmCgp> filteredData;
    private Filter listFilter;

    public CardAdapterAsset(Context ctx, List<InfoAndItmqAndFaxmCgp> data) {
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
            view = inflater.inflate(R.layout.info_list, viewGroup, false);
        }
        TextView ast_cd = (TextView) view.findViewById(R.id.asset_ast_dtl_cd);
        TextView ast_nm = (TextView) view.findViewById(R.id.asset_ast_nm);
        TextView empe_nm = (TextView) view.findViewById(R.id.asset_empe_nm);
        Button btnRwdAplYn = (Button) view.findViewById(R.id.btn_rwd_apl_yn);
        Button btnReturnYn = (Button) view.findViewById(R.id.btn_return_yn);

        btnRwdAplYn.setTag(i);
        btnRwdAplYn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "보수신청 추후 업데이트 예정입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        btnReturnYn.setTag(i);
        btnReturnYn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "반납신청 추후 업데이트 예정입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        ast_cd.setText(new StringBuilder().append(filteredData.get(i).astCd).append("-").append(filteredData.get(i).astDtlCd).toString());
        ast_nm.setText(filteredData.get(i).astNm);
        empe_nm.setText(filteredData.get(i).empNm);

        return view;
    }


    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new CardAdapterAsset.ListFilter();
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
                    if (searchFormat.equals("담당부서") && item.empDeptNm.contains(searchText)) {
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
