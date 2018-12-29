package com.example.portatilcda.cobroscda;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.portatilcda.cobroscda.ConexionBd.ConsultaActualizaDatosUsu;
import com.example.portatilcda.cobroscda.ConexionBd.ConsultaDatosUsu;
import com.example.portatilcda.cobroscda.ConexionBd.LoguinConect;
import com.example.portatilcda.cobroscda.Modelo.ModeloSpinnerZonas;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityUsuario_Datos extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    TextView nombreLic;
    Spinner cb_Zonas;  // combo box zonas
    Button guardar;

    String idSocios;


    //valores carga datos
    EditText direccion;
    EditText dirDescripcion;
    int zona;
    EditText telefono;
    EditText correo;
    String latitud;
    String longitud;
    String imagen;
    int zonaCB;
    double lat2;
    double long2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario__datos);


        // recupera el idSocio
        idSocios = (PreferenciasUsuario.obtenerPreferenceString(this,PreferenciasUsuario.PREFERENCE_USUARIO_ID));


       //enlaza y carga el nombre del lic
        nombreLic = findViewById(R.id.nombreUsuDatos);
        nombreLic.setText(PreferenciasUsuario.obtenerPreferenceString(this,PreferenciasUsuario.PREFERENCE_USUARIO_LOGUIN));


        ////// combo box carga spiner
        cb_Zonas = (Spinner) findViewById(R.id.Cb_Zona);
        cargaSpiner();


        // enlazando compenentes
         direccion = (EditText) findViewById(R.id.textDireccion);
         dirDescripcion = (EditText) findViewById(R.id.textDescripcion);
         telefono = (EditText) findViewById(R.id.textTelefono);
         correo = (EditText) findViewById(R.id.textCorreo);


         Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                        boolean succes =  jsonResponse.getBoolean("succes");
                    if (succes){
                        direccion.setText(jsonResponse.getString("direccion"));
                        dirDescripcion.setText(jsonResponse.getString("dirDescripcion"));
                        telefono.setText(jsonResponse.getString("telefono"));
                        correo.setText(jsonResponse.getString("correo"));

                        latitud = jsonResponse.getString("latitud");
                        longitud = jsonResponse.getString("longitud");
                        lat2 = Double.parseDouble(latitud);
                        long2 = Double.parseDouble(longitud);

                        zona = Integer.parseInt(jsonResponse.getString("zonas_idZonas"));
                        zonaCB = zona - 1;
                        cb_Zonas.setSelection(zonaCB);
                    }
                    else{

                        AlertDialog.Builder builder = new AlertDialog.Builder((ActivityUsuario_Datos.this));
                        builder.setMessage("Error en cargar datos!!")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ConsultaDatosUsu consultaDatosUsu = new ConsultaDatosUsu(idSocios,responseListener);
        RequestQueue queue = Volley.newRequestQueue(ActivityUsuario_Datos.this);
        queue.add(consultaDatosUsu);

        //acion boton guardar
        guardar = (Button) findViewById(R.id.guardarDatosUsu);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String direccion2 = direccion.getText().toString();
                final String dirDescripcion2 = dirDescripcion.getText().toString();
                // final String zona2 = zona.getText().toString();
                final String telefono2 = telefono.getText().toString();
                final String  correo2 = correo.getText().toString();

                ModeloSpinnerZonas modeloSpinnerZonas = (ModeloSpinnerZonas) cb_Zonas.getSelectedItem();
                final int zonas_idZonas = (modeloSpinnerZonas.getValor());
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ///////llenado de nuevso valores paraguardar


                            JSONObject jsonResponse = new JSONObject(response);
                            boolean succes =  jsonResponse.getBoolean("succes");
                            if(succes){
                                Toast.makeText(getApplicationContext(), "Datos actualizados!!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ActivityUsuario_Datos.this, ActivityUsuario.class);
                                ActivityUsuario_Datos.this.startActivity(intent);

                                //finish();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder((ActivityUsuario_Datos.this));
                                builder.setMessage("Error al guardar los datos")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ConsultaActualizaDatosUsu actualizarDatos2 = new ConsultaActualizaDatosUsu(idSocios, direccion2,dirDescripcion2,String.valueOf(zonas_idZonas),telefono2,correo2,responseListener);

                /// clase que se comunica con voley
                RequestQueue queue = Volley.newRequestQueue(ActivityUsuario_Datos.this);
                queue.add(actualizarDatos2);

            }
        });

        //enlaza fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

       private  void  cargaSpiner(){
        ArrayList<ModeloSpinnerZonas> lista = new ArrayList<ModeloSpinnerZonas>();
        lista.add(new ModeloSpinnerZonas(1,"Colegio"));
        lista.add(new ModeloSpinnerZonas(2,"Central"));
        lista.add(new ModeloSpinnerZonas(3,"Norte"));
        lista.add(new ModeloSpinnerZonas(4,"Sud"));
        lista.add(new ModeloSpinnerZonas(5,"Sacaba"));
        lista.add(new ModeloSpinnerZonas(6,"Quillacollo"));

        ArrayAdapter<ModeloSpinnerZonas> adapter = new ArrayAdapter<ModeloSpinnerZonas>(this,R.layout.cb__zonas_ops, lista);
        cb_Zonas.setAdapter(adapter);
//        ModeloSpinnerZonas modeloSpinnerZonas = (ModeloSpinnerZonas) cb_Zonas.getSelectedItem();
//        Toast.makeText(this, "valor "+ modeloSpinnerZonas.getValor()+" "+modeloSpinnerZonas.toString(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng China = new LatLng(lat2, long2);
        mMap.addMarker(new MarkerOptions().position(China).title("Dirección de cobro").icon(BitmapDescriptorFactory.fromResource(R.drawable.img_punto_v)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(China,15));
    }
}
