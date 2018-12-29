package com.example.portatilcda.cobroscda.ConexionBd;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaDatosUsu extends  StringRequest{
    // SE PONE LA URL LOCALHOST/REGISTER.PHP
    private static final String DATOS_REQUEST_URL="http://192.168.1.41:8080/conAndroid/ConsultaDatos.php";
    //private static final String DATOS_REQUEST_URL = "http://auditores.mipropia.com/conAndroid/ConsultaDatos.php";

    private Map<String,String> params;

    //CONSTRUCTOR DE LA CLASE y se ponen los parametros a usar
    public ConsultaDatosUsu(String idSocios, Response.Listener<String> listener){

        //METODO DE ENVIO POR EL METODO POST Y SE DA LA URL DONDE SE ENCUENTRA EL ARCHIVO + ESCUCHADOR
        super(Method.POST, DATOS_REQUEST_URL,listener,null);

        //PARAMETOS A SER ENVIADOS MISMO NOMBRE EL ARCHIVO DE LOCALHOST
        params=new HashMap<>();
        params.put("idSocios",idSocios);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
