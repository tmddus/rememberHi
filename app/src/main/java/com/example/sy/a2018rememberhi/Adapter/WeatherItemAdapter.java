package com.example.sy.a2018rememberhi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sy.a2018rememberhi.R;
import com.example.sy.a2018rememberhi.TodayListItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WeatherItemAdapter extends BaseAdapter {

    private ArrayList<TodayListItem> array = new ArrayList<TodayListItem>();

    @Override
    public int getCount() { return array.size(); }

    @Override
    public Object getItem(int position) { return array.get(position);  }

    @Override
    public long getItemId(int position) { return position;   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.checklist_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textTextView = (TextView) convertView.findViewById(R.id.checkListText) ;

        return null;
    }
}
