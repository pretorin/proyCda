package com.example.portatilcda.cobroscda;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasUsuario {
    public static final  String STRING_PREFERENCES = "CobrosCDA.datos.sesion";

    public static final String PREFERENCE_ESTADO_SESION = "estado.sesion";

    public static  final String PREFERENCE_USUARIO_LOGUIN = "usuario.login";

    public  static  final String PREFERENCE_USUARIO_ID = "usuario.id";

    public  static  final String PREFERENCE_USUARIO_LOGUEADO= "usuario.logueado";

    // para guardar boolena y string
    public static void savePreferenceBolean(Context c, boolean b, String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        preferences.edit().putBoolean(key, b).apply();
    }

    public static void savePreferenceString(Context c, String b, String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        preferences.edit().putString(key, b).apply();
    }

    ///// verifica si el usuario ya fue logueado
    public static boolean obtenerPreferenceBolean(Context c, String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES,c.MODE_PRIVATE);
        return  preferences.getBoolean(key,false);
    }


    public static String obtenerPreferenceString(Context c, String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES,c.MODE_PRIVATE);
        return  preferences.getString(key, "");
    }


}
