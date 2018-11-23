package com.example.sy.a2018rememberhi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.TodayListItem;

import java.util.ArrayList;

public class TodayListAdapter extends BaseAdapter {
    private ArrayList<TodayListItem> array = new ArrayList<TodayListItem>();


    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todaylist_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView listNumber = convertView.findViewById(R.id.today_item_num);
        TextView listTile = convertView.findViewById(R.id.today_title);

        TodayListItem listViewItem = array.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        listNumber.setText(listViewItem.getListNum());
        listTile.setText(listViewItem.getListTitle());


        return convertView;

    }

    public void setArray(ArrayList<TodayListItem> arr){ array = arr; }
    public void addItem(String str, int i){
        TodayListItem item = new TodayListItem();
        item.setListTitle(str);
        item.setListNum(String.valueOf(i));

        array.add(item);
    }
    public void clear(){array.clear();}
}
