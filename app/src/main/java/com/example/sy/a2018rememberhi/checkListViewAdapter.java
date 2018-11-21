package com.example.sy.a2018rememberhi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.Activity.CheckActivity;

import java.util.ArrayList;
import java.util.List;

public class checkListViewAdapter extends BaseAdapter {

    private ArrayList<String> TextList = new ArrayList<String>();
    private  ArrayList<String> setString = new ArrayList<String>();

    @Override
    public int getCount() {
        return TextList.size();
    }

    @Override
    public Object getItem(int position) {
        return TextList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.checklist_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textTextView = (TextView) convertView.findViewById(R.id.checkListText) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final String ListText = TextList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textTextView.setText(ListText);
        CheckBox tv = convertView.findViewById(R.id.checkListBox);

        tv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setString.add(ListText);
                }else{
                    setString.remove(ListText);
                }
            }
        });
        return convertView;
    }

    public void setArray(ArrayList<String> array) {
        TextList = array;
    }
    public int getChecks(){return setString.size();}


}
