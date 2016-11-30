package com.example.mustafa_bas.kutuphaneandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Mustafa-Bas on 4/30/2015.
 */
public class SehirAdapter extends ArrayAdapter<CharSequence> {
CharSequence[] list;


    public SehirAdapter(Context context, int resource, CharSequence[] objects) {
        super(context, resource, objects);
        this.list=objects;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sehirlerlistview, null );
        }



        ((TextView)convertView.findViewById(R.id.txtSehirler))
                .setText(list[position]);

        return convertView;

    }


}
