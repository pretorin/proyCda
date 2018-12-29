package com.example.portatilcda.cobroscda;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.portatilcda.cobroscda.ConexionBd.LoguinConect;
import com.example.portatilcda.cobroscda.ConexionBd.LoguinConectUsu;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tb_info;

    Button btn_loguin; // boton main activity 1
    EditText et_usuario, et_password;
    CheckBox checkAdm;
   boolean logueado;
    //boolean logueadoAdm;
    //boolean logueadoUsu;
    String usuarioLogueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // verificar si esta logueado

        logueado = PreferenciasUsuario.obtenerPreferenceBolean(this,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
        usuarioLogueado = PreferenciasUsuario.obtenerPreferenceString(this,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUEADO);
        //logueadoAdm = PreferenciasUsuario.obtenerPreferenceBolean(this,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
       // logueadoUsu = PreferenciasUsuario.obtenerPreferenceBolean(this,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
        if(logueado ==true){
            if (usuarioLogueado == "adm"){
                Intent intent = new Intent(MainActivity.this, ActivityCobro.class);
                finish();
                MainActivity.this.startActivity(intent);

            }
            if(usuarioLogueado == "socio") {
                Intent intent = new Intent(MainActivity.this, ActivityUsuario.class);
                finish();
                MainActivity.this.startActivity(intent);

            }
        }



        //poner iconno en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //cambiar icono de la aplicacion
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);



        //BOTON INFORMACION DESDE EL LABEL
        tb_info = findViewById(R.id.textViewInfo);
        tb_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRege = new Intent(MainActivity.this, Informacion.class);
                MainActivity.this.startActivity(intentRege);
            }
        });


        //IDENTIFICACION DE INPUTS o Instanciar
        et_usuario = (EditText) findViewById(R.id.textUsuarioMain);
        et_password = (EditText) findViewById(R.id.textPassMain);
        btn_loguin = (Button) findViewById(R.id.btnLoguin);
        checkAdm = (CheckBox)findViewById(R.id.checkBoxAdmin);
        btn_loguin.setOnClickListener(this);


    }


    // METODO ON CLICK DONDE VERIFICA SI ES ADMINISTRADOR
    @Override
    public void onClick(View v) {
        final String user =  et_usuario.getText().toString();
        final String password= et_password.getText().toString();
        // boolean admin = false;
        if(checkAdm.isChecked()==true){
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean succes =  jsonResponse.getBoolean("succes");
                        if(succes){
                            String nomb = jsonResponse.getString("nombre");
                            String apell = jsonResponse.getString("apellidos");
                            String cargo = jsonResponse.getString("cargo");
                            String nombre = nomb + " " + apell;

                           logueado= true;
                          // logueadoAdm = true;
                           //logueadoUsu = false;
                            usuarioLogueado = "adm";
                          // PreferenciasUsuario.savePreferenceBolean(MainActivity.this,logueado,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                            PreferenciasUsuario.savePreferenceBolean(MainActivity.this,logueado,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);

                            PreferenciasUsuario.savePreferenceString(MainActivity.this,usuarioLogueado,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUEADO);

                           PreferenciasUsuario.savePreferenceString(MainActivity.this,nombre,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUIN);


                            // int age = jsonResponse.getInt("age");
                            Intent intent = new Intent(MainActivity.this, ActivityCobro.class);
//                            intent.putExtra("nombre", nombre);
//                            intent.putExtra("apellidos", apellidos);
//                            intent.putExtra("cargo", cargo);
                            //  intent.putExtra("age", age);
                            MainActivity.this.startActivity(intent);
                            finish();

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                            builder.setMessage("Datos Incorrectos")
                                    .setNegativeButton("Retry",null)
                                    .create().show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Fallo en la conexion al servidor0", Toast.LENGTH_LONG).show();

                    }
                }
            };

            LoguinConect loguinConect = new LoguinConect(user,password,responseListener);

            /// clase que se comunica con voley
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loguinConect);
        }

        ////////VERIFICA SI ES SOCIO
        else{
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean succes =  jsonResponse.getBoolean("succes");
                        if(succes){
                            String nomb = jsonResponse.getString("nombre");
                            String app = jsonResponse.getString("appPaterno");
                            String apm = jsonResponse.getString("appMaterno");
                            String nombre = nomb + " " + app + " " + apm;
                            String idSocio = jsonResponse.getString("IdSocios");

                            //Toast.makeText(getApplicationContext(), idSocio, Toast.LENGTH_LONG).show();

                            logueado= true;
                            //logueadoAdm = false;
                            //logueadoUsu = true;
                            usuarioLogueado = "socio";


                            PreferenciasUsuario.savePreferenceBolean(MainActivity.this,logueado,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                            PreferenciasUsuario.savePreferenceString(MainActivity.this,nombre,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUIN);
                            PreferenciasUsuario.savePreferenceString(MainActivity.this,idSocio,PreferenciasUsuario.PREFERENCE_USUARIO_ID);
                            PreferenciasUsuario.savePreferenceString(MainActivity.this,usuarioLogueado,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUEADO);

                            //boolean xxx = PreferenciasUsuario.obtenerPreferenceBolean(MainActivity.this,PreferenciasUsuario.PREFERENCE_ESTADO_SESION);
                            int IdSocios = jsonResponse.getInt("IdSocios");

                            // int age = jsonResponse.getInt("age");
                            Intent intent = new Intent(MainActivity.this, ActivityUsuario.class);
//                            intent.putExtra("nombre", nombre);
//                            intent.putExtra("appPaterno", appPaterno);
//                            intent.putExtra("appMaterno", appMaterno);
                            //  intent.putExtra("age", age);

                            MainActivity.this.startActivity(intent);
                            finish();

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                            builder.setMessage("Datos Incorrectos")
                                    .setNegativeButton("Retry",null)
                                    .create().show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Fallo en la conexion al servidor", Toast.LENGTH_LONG).show();
                     //   Toast.makeText(this, "valor "+   modeloSpinnerZonas.getValor()+" "+modeloSpinnerZonas.toString(),Toast.LENGTH_LONG).show();
                       // throw new RuntimeException(e); cieree
                    }
                }
            };

            LoguinConectUsu loguinConect = new LoguinConectUsu( user,password,responseListener);
            /// clase que se comunica con voley
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loguinConect);


        }

    }

}