package com.example.sy.a2018rememberhi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.missionItem;

import java.util.ArrayList;

public class missionAdapter extends BaseAdapter {
    ArrayList<missionItem> missionItems = new ArrayList<missionItem>();

    @Override
    public int getCount() {
        return missionItems.size();
    }

    @Override
    public Object getItem(int position) {
        return missionItems.get(position);
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
            convertView = inflater.inflate(R.layout.mission_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView missionTxt = convertView.findViewById(R.id.missionTxt);
        ImageView ckImg = convertView.findViewById(R.id.Checkimg);

        missionItem myItem = missionItems.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        missionTxt.setText(myItem.getMissionText());
        if(myItem.getSuccess()==1){
            ckImg.setImageResource(R.mipmap.success);
        }else{
            ckImg.setImageResource(R.mipmap.oval);
        }

        return convertView;
    }

    //public void setArray(ArrayList<missionItem> arr){ missionItems = arr; }
    public void addItem(int suc, String txt){
        missionItem item = new missionItem();
        item.setSuccess(suc);
        item.setMissionText(txt);

        missionItems.add(item);

    }
    public void delItem(int position){missionItems.remove(position);}
    public  void clear(){missionItems.clear();}
}