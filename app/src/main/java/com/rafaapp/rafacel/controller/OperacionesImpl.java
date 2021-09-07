package com.rafaapp.rafacel.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Persona;

import org.json.JSONArray;

import java.util.ArrayList;

public class OperacionesImpl implements Operaciones {

    @Override
    public boolean registro(Context context, Persona persona) {
        if (persona != null) {
            SharedPreferences sharedPreferences = Utils.getSharePreference(context);
            JSONArray personas = Utils.getPersonasJsonArray(context);
            personas.put(Utils.getJsonObjectFromPojo(persona));
            return sharedPreferences.edit().putString("arrayPersona", personas.toString()).commit();
        } else {
            return false;
        }
    }

    @Override
    public boolean registroCelular(Context context, Persona persona, EquipoCelular equipoCelular) {
        if (equipoCelular != null && persona != null && equipoCelular.getLinea().getLinumerolinea() != 0L) {
            SharedPreferences sharedPreferences = Utils.getSharePreference(context);
            ArrayList<Persona> listPersonas = Utils.getPersonasFromDatabase(Utils.getPersonasJsonArray(context));
            listPersonas = removePersona(listPersonas, persona);
            ArrayList<EquipoCelular> equipoCelulars = persona.getEquipoCelular();
            equipoCelulars.add(equipoCelular);
            persona.setEquipoCelular(equipoCelulars);
            listPersonas.add(persona);
            JsonArray personasResult = new GsonBuilder().create().toJsonTree(listPersonas).getAsJsonArray();
            return sharedPreferences.edit().putString("arrayPersona", personasResult.toString()).commit();
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(Context context, Persona persona) {
        if (persona != null) {
            SharedPreferences sharedPreferences = Utils.getSharePreference(context);
            ArrayList<Persona> listPersonas = Utils.getPersonasFromDatabase(Utils.getPersonasJsonArray(context));
            listPersonas = removePersona(listPersonas, persona);
            listPersonas.remove(persona);
            JsonArray personasResult = new GsonBuilder().create().toJsonTree(listPersonas).getAsJsonArray();
            return sharedPreferences.edit().putString("arrayPersona", personasResult.toString()).commit();
        } else {
            return false;
        }
    }

    private ArrayList<Persona> removePersona(ArrayList<Persona> personas, Persona persona) {
        for (Persona persona1 : personas) {
            if (persona1.getPerid() == persona.getPerid()) {
                personas.remove(persona1);
                break;
            }
        }
        return personas;
    }
}
