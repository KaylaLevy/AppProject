package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class c_language extends AppCompatActivity {

    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/councellor_lang.php";
    private String un = LoginUser.getCurrentUsername();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_language);

        final Spinner spnLang1;
        spnLang1 = (Spinner)findViewById(R.id.spnLang1);
        ArrayList<String> list = new ArrayList<> ();
        list.add("English"); list.add("Zulu"); list.add("Xhosa"); list.add("Afrikaans");
        list.add("Sesotho sa Laboa"); list.add("Setswana"); list.add("Sesotho"); list.add("Xitsonga");
        list.add("siSwati"); list.add("Tshivenda"); list.add("isiNdebele");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        spnLang1.setAdapter(adapter);

        /*final Spinner spnLang2;
        spnLang2 = (Spinner)findViewById(R.id.spnLang2);
        ArrayList<String> list2 = new ArrayList<> ();
        list2.add("English"); list2.add("Zulu"); list2.add("Xhosa"); list2.add("Afrikaans");
        list2.add("Sesotho sa Laboa"); list2.add("Setswana"); list2.add("Sesotho"); list2.add("Xitsonga");
        list2.add("siSwati"); list2.add("Tshivenda"); list2.add("isiNdebele"); list2.add("N/A");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list2);
        spnLang2.setAdapter(adapter2);*/

        Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang1 = spnLang1.getSelectedItem().toString();
                //String lang2 = spnLang2.getSelectedItem().toString();
                new insert().execute(lang1,un);    //,  lang2
            }
        });
    }


    public class insert extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String lang1 = strings[0];
            String uName = strings[1];
            //String lang2 = strings[2];
            String finalURL = url_Register + "?LANG1=" + lang1
                    //+ "&LANG2=" + lang2
                    + "&USERNAME=" + uName;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalURL)
                    .get()
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String result = response.body().string();
                    showToast(result);

                    if (result.equalsIgnoreCase("LANG1 HAS BEEN entered successfully")) {
                        showToast("LANG1 HAS BEEN entered successfully");

                        Intent i = new Intent(c_language.this, c_color.class);
                        c_language.this.startActivity(i);
                    } else {
                        showToast("oops! please try again!");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final String text)
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(c_language.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }


}
