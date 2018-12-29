package com.example.portatilcda.cobroscda;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Informacion extends AppCompatActivity  {

    private ImageView linkCDA;
    private String direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        LinkFb();
        LinkCDA();

    }

    public void LinkFb() {

        ImageView entry = (ImageView) findViewById(R.id.Fotofb);

        entry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/cdauditores");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
//        String facebookId = "fb://page/638146522919692";
//        String urlPage = "https://www.facebook.com/cdauditores";
//        try {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
//        } catch (Exception e) {
//            Log.e("Error","Aplicacion no instada");
//            //Abre url de pagina.
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
//        }
    }
    public void LinkCDA() {

        ImageView entry = (ImageView) findViewById(R.id.webCda);

        entry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.auditores.org.bo/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}