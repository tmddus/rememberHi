package com.example.sy.a2018rememberhi;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.example.sy.a2018rememberhi.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    CheckBox cb = (CheckBox)findViewById(R.id.checkListBox);

    public CheckableLinearLayout(Context context) {
        super(context);
    }

    @Override
    public void setChecked(boolean checked) {
        if (cb.isChecked() != checked) {
            cb.setChecked(checked) ;
        }
    }


    @Override
    public boolean isChecked() {
        return cb.isChecked();
    }

    @Override
    public void toggle() {
        setChecked(cb.isChecked() ? false : true);
    }
}
