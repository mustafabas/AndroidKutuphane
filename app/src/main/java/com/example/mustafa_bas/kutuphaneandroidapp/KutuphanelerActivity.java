package com.example.mustafa_bas.kutuphaneandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

/**
 * Created by Mustafa-Bas on 4/30/2015.
 */
public class KutuphanelerActivity extends ActionBarActivity {
    String sehir="";
    String kitapAdi="";
    String result="";
    String[] kutuphaneAdi=new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.kutuphanelerview);
            sehir=getIntent().getExtras().getString("sehir");
            kitapAdi=getIntent().getExtras().getString("kitapadi");
        Toast.makeText(getApplicationContext(),kitapAdi+""+sehir,Toast.LENGTH_LONG).show();
        final ListView lstKutuphaneneler=(ListView)findViewById(R.id.lstKutuphanaler);
        final TextView txtListe=(TextView)findViewById(R.id.txtListe);




            final Gson gson=new Gson();
        final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get("http://kutuphane.kutahyademirdokum.com//kutuphanaler.aspx?kitapadi="+kitapAdi+"&sehir="+sehir,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                     result=new String(responseBody);
                ResultKutuphaneler resultKutuphaneler=gson.fromJson(result,ResultKutuphaneler.class);
                List<kutuphaneler> kutuphanelerList=resultKutuphaneler.getKutuphaneList();
              final KutuphanelerHelper[] dizi=new KutuphanelerHelper[kutuphanelerList.size()];


                int a=0;

                for (kutuphaneler kutuphane:kutuphanelerList) {
                KutuphanelerHelper kutphane=new KutuphanelerHelper();

                    kutphane.setAdi(kutuphane.getKutuphane());
                    kutphane.setAdres(kutuphane.getAdres());
                    kutphane.setResim(R.drawable.kutuphane);
                    kutphane.setOk(R.drawable.arrow);
                    dizi[a]=kutphane;
                    kutuphaneAdi[a]=kutphane.getAdi();
                    a++;
                }


                KutuphaneAdapter adapter = new KutuphaneAdapter(KutuphanelerActivity.this, R.layout.kutuphanelerliste, dizi);
                lstKutuphaneneler.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        lstKutuphaneneler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(KutuphanelerActivity.this,KutuphaneAyrintiActivity.class);
                intent.putExtra("kutuphaneAdi",kutuphaneAdi[position]);
                startActivity(intent);

            }
        });



    }


}
