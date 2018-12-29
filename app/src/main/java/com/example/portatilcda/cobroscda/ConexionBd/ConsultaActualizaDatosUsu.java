package com.example.portatilcda.cobroscda.ConexionBd;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaActualizaDatosUsu extends StringRequest {
    // SE PONE LA URL LOCALHOST/REGISTER.PHP
   private static final String ENVIO_DATOS_REQUEST_URL = "http://192.168.1.41:8080/conAndroid/ActualizaDatos.php";
    //private static final String ENVIO_DATOS_REQUEST_URL = "http://185.27.134.127/conAndroid/Login.php";

    private Map<String, String>params;
    public  ConsultaActualizaDatosUsu(String idSocios, String direccion, String dirDescripcion, String zonas_idZonas, String telefono, String correo, Response.Listener<String> listenerActualiza){
        super(Method.POST, ENVIO_DATOS_REQUEST_URL, listenerActualiza, null);
        params = new HashMap<>();
        params.put("idSocios", idSocios);
        params.put("direccion", direccion);
        params.put("dirDescripcion", dirDescripcion);
        params.put("zonas_idZonas", zonas_idZonas);
        params.put("telefono", telefono);
        params.put("correo", correo);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}