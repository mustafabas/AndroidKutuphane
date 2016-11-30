package com.example.mustafa_bas.kutuphaneandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mustafa-Bas on 5/1/2015.
 */
public class KutuphaneAdapter extends ArrayAdapter<KutuphanelerHelper> {
Context context;
    int layoutid;
    KutuphanelerHelper kutuphaneler[];
   int[] resim;
     public KutuphaneAdapter(Context context, int resource, KutuphanelerHelper[] objects) {
        super(context, resource, objects);
        this.context=context;
         this.kutuphaneler=objects;
        layoutid=resource;
    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent)
    {
        View row=convertView;
        kutuphaneViewHolder Holder=null;



        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.kutuphanelerliste, parent,false );
            Holder=new kutuphaneViewHolder();
            Holder.img=(ImageView)row.findViewById(R.id.imgKutuphane);
            Holder.adi=(TextView)row.findViewById(R.id.txtKutuphaneAdi);
            Holder.adres=(TextView)row.findViewById(R.id.txtAdres);
            Holder.ok=(ImageView)row.findViewById(R.id.imgOk);
            row.setTag(Holder);


        }
        else
        {
            Holder=(kutuphaneViewHolder)row.getTag();
        }


        KutuphanelerHelper helper=kutuphaneler[position];
        Holder.img.setImageResource(helper.getResim());
        Holder.adi.setText(helper.getAdi());
        Holder.adres.setText(helper.getAdres());
        Holder.ok.setImageResource(helper.getOk());
        return row;

    }

    static class kutuphaneViewHolder{
        ImageView img;
        TextView adres;
        TextView adi;
        ImageView ok;
    }
}
