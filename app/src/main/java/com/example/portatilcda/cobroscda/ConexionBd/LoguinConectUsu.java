package com.example.portatilcda.cobroscda.ConexionBd;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

    //PERMITE HACER OTDO EL TRABAJO DEL LOGUIN
    public class LoguinConectUsu extends StringRequest {

        // SE PONE LA URL LOCALHOST/REGISTER.PHP
        private static final String LOGINUSU_REQUEST_URL="http://192.168.1.41:8080/conAndroid/LoginUsu.php";
        //private static final String LOGINUSU_REQUEST_URL = "http://auditores.mipropia.com/conAndroid/LoginUsu.php";

        private Map<String,String> params;

        //CONSTRUCTOR DE LA CLASE y se ponen los parametros a usar
        public LoguinConectUsu(String user,String password, Response.Listener<String> listenerUsu){

            //METODO DE ENVIO POR EL METODO POST Y SE DA LA URL DONDE SE ENCUENTRA EL ARCHIVO + ESCUCHADOR
            super(Method.POST, LOGINUSU_REQUEST_URL,listenerUsu,null);
            //PARAMETOS A SER ENVIADOS MISMO NOMBRE EL ARCHIVO DE LOCALHOST
            params=new HashMap<>();
            params.put("user",user);
            params.put("password",password);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }

