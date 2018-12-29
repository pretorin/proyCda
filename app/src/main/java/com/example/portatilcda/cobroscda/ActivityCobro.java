package com.example.portatilcda.cobroscda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityCobro extends AppCompatActivity {
    TextView tvNombre, tvCargo;
    //TextView t1,t2,t3;
    Button cerrarCesionAdm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cobro);

        //boolean logueadoUsu;
        tvNombre = findViewById(R.id.LabelNombre);
      //  tvCargo = findViewById(R.id.LabelCargo);
       // t1 = findViewById(R.id.editText)
        //t1.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        //enlaza y sale de la aplicacion
        cerrarCesionAdm = findViewById(R.id.salirCobrador);
        cerrarCesionAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean logueado = false;
               // boolean logueadoAdm = false;
              //  PreferenciasUsuario.savePreferenceBolean(ActivityCobro.this,false, PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                PreferenciasUsuario.savePreferenceBolean(ActivityCobro.this,logueado,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                PreferenciasUsuario.savePreferenceString(ActivityCobro.this,null,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUEADO);
                //PreferenciasUsuario.savePreferenceBolean(ActivityCobro.this,logueadoAdm,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);

                finish();

            }
        });


        //carga el nobmre del cobrador
        //Intent intent = getIntent();
        tvNombre.setText(PreferenciasUsuario.obtenerPreferenceString(this,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUIN));

    }
}
