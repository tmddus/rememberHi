package com.example.sy.a2018rememberhi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class checkListViewAdapter extends BaseAdapter {
    CheckBox tv;
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

        TextView textTextView = (TextView) convertView.findViewById(R.id.checkListText) ;


        final String ListText = TextList.get(position);

        textTextView.setText(ListText);
        tv = convertView.findViewById(R.id.checkListBox);
        tv.setChecked(false);

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

    public void addItem(String text) {
        TextList.add(text);
    }
    public int getChecks(){return setString.size();}

}
