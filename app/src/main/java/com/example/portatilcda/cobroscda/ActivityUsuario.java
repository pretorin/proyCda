package com.example.portatilcda.cobroscda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityUsuario extends AppCompatActivity {
    TextView nombreLic;

    Button cerrarSesionUsu;
    Button actualizaDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);
         nombreLic = findViewById(R.id.LabelNombreLic);
      /*  //////////RECIBIENDO LOS PARAMETROS DEL INTENR/////////////
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String appPaterno = intent.getStringExtra("appPaterno");
        String appMaterno = intent.getStringExtra("appMaterno");
        String cargo = intent.getStringExtra("cargo");
////////////////////////////////////////////////*/

        // RECUPERA LOS DATOS DE share preferences
        nombreLic.setText(PreferenciasUsuario.obtenerPreferenceString(this,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUIN));

        cerrarSesionUsu = findViewById(R.id.salirSocio);
        cerrarSesionUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean logueado = false;
                //boolean logueadoUsu = false;
                //PreferenciasUsuario.savePreferenceBolean(ActivityUsuario.this,false, PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                PreferenciasUsuario.savePreferenceBolean(ActivityUsuario.this,logueado,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                PreferenciasUsuario.savePreferenceString(ActivityUsuario.this,null,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUEADO);
               // PreferenciasUsuario.savePreferenceBolean(ActivityUsuario.this,logueadoUsu,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                finish();
            }
        });

        actualizaDatos = findViewById(R.id.socioDatos);
        actualizaDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarDatos = new Intent(ActivityUsuario.this, ActivityUsuario_Datos.class);
                ActivityUsuario.this.startActivity(actualizarDatos);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            //new ActivityUsuario().finish();
//            finishActivity(0);
//            System.exit(0);
            this.finish();
            Toast.makeText(getApplicationContext(), "Salir!!",
                    Toast.LENGTH_SHORT).show();
           // return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
