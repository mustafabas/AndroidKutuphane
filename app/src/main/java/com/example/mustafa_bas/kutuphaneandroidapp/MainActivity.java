package com.example.mustafa_bas.kutuphaneandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    String result;
    String[] list;



    EditText txtKitapAdi;

   static String kitapAdi="";
    static String sehirA="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lstSehirler=(ListView)findViewById(R.id.lstSehirler);
        txtKitapAdi=(EditText)findViewById(R.id.txtKitapAdi);
        final Button btnAra=(Button)findViewById(R.id.btnAra);
        final Spinner spsehirler=(Spinner)findViewById(R.id.spSehirler);
        final String[] lste=new String[10];
        
        findViewById(R.id.mainLayout).requestFocus();
        spsehirler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sehirA=String.valueOf(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


final Gson gson = new Gson();

        final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://kutuphane.kutahyademirdokum.com/sehirler.aspx",new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                result = new String(responseBody);
                ResulSehirler resulObj = gson.fromJson(result, ResulSehirler.class);
                List<Sehirler> sehirList = resulObj.getSehirList();
                String[] content=new String[sehirList.size()+1];
               int a=1;
                content[0]="Şehir";

                for(Sehirler sehir:sehirList)
                {
                    content[a]=sehir.getSehir();
                        a++;
                }
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_spinner_item, content);

                adapter.setDropDownViewResource(R.layout.sehirlerlistview);
                spsehirler.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this,"İnternet Bağlantınızı Kontrol Edin.",Toast.LENGTH_LONG).show();

            }
        });






       btnAra.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

            kitapAdi=String.valueOf(txtKitapAdi.getText());

                          final Gson gson = new Gson();


               final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
               asyncHttpClient.get("http://kutuphane.kutahyademirdokum.com/sehirler.aspx?kitapadi="+txtKitapAdi.getText()+"&sehir="+sehirA, new AsyncHttpResponseHandler() {
                   @Override
                   public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                       result = new String(responseBody);
                       ResulSehirler resulObj = gson.fromJson(result, ResulSehirler.class);
                       List<Sehirler> sehirList = resulObj.getSehirList();



                       int a = 0;

                            String txs="";
                             String[] lst= new String[sehirList.size()];
                       for (Sehirler sehir : sehirList) {
                                lst[a] = sehir.getSehir();
                                a++;
                            }
                        if(lst[0].equals("yok")) {
                            Toast.makeText(MainActivity.this, "Böyle Bir Kitap Bulunamadı", Toast.LENGTH_LONG).show();
                            lstSehirler.setAdapter(null);
                        }

                       else {
                            Intent intent=new Intent(MainActivity.this,KutuphanelerActivity.class);
                            intent.putExtra("kitapadi",kitapAdi);
                            intent.putExtra("sehir",sehirA);
                            startActivity(intent);


                        }







                   }


                   @Override
                   public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                       Toast.makeText(getApplication(), "İnternet Bağlantısı yok", Toast.LENGTH_SHORT).show();

                   }
               });







           }




       });








       /* ArrayAdapter<CharSequence> adapter=new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,list);
        spnSehirler.setAdapter(adapter);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
