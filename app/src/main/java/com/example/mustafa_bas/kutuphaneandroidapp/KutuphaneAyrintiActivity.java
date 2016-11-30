package com.example.mustafa_bas.kutuphaneandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

/**
 * Created by Mustafa-Bas on 5/1/2015.
 */

public class KutuphaneAyrintiActivity extends ActionBarActivity {
    String result="";
    static String Lat="";
    static String Long="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kutuphaneayrinti);
        final String kutuphaneAdi=getIntent().getExtras().getString("kutuphaneAdi");
        TextView txtAdi=(TextView)findViewById(R.id.edkAdi);
        final TextView txtAdres=(TextView)findViewById(R.id.edAdres);
        final TextView txtSehir=(TextView)findViewById(R.id.edSehir);
        final TextView txtTelefon=(TextView)findViewById(R.id.edTelNo);

        txtAdi.setText(kutuphaneAdi);
        final Button btnAra=(Button)findViewById(R.id.btnAra);
        final Button btnHarita=(Button)findViewById(R.id.btnHarita);
        final Gson gson=new Gson();
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get("http://kutuphane.kutahyademirdokum.com/kutuphaneayrinti.aspx?kutuphaneadi="+kutuphaneAdi,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                result=new String(responseBody);
                KutupHaneAyrintiList kutuphaneAyrintiList=gson.fromJson(result, KutupHaneAyrintiList.class);
                List<KutuphaneAyrintiHelper> kutuphanelerList=kutuphaneAyrintiList.getKutuphaneList();

                for(KutuphaneAyrintiHelper kutuphane:kutuphanelerList)
                {
                    txtAdres.setText(kutuphane.getAdres());
                    txtSehir.setText(kutuphane.getSehir());
                    txtTelefon.setText(kutuphane.getTelNo());
                    Toast.makeText(KutuphaneAyrintiActivity.this,kutuphane.getAdres(),Toast.LENGTH_LONG).show();
                    Lat=String.valueOf(kutuphane.getLat());
                    Long=String.valueOf(kutuphane.getLong());
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(KutuphaneAyrintiActivity.this,"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();

            }
        });
        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("tel:"+txtTelefon.getText());
                Intent callIntent=new Intent(Intent.ACTION_DIAL,uri);
                startActivity(callIntent);
            }
        });
        btnHarita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("geo:"+Lat+","+Long+"?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);

            }
        });
    }
}
