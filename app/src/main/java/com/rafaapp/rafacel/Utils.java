package com.rafaapp.rafacel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;


import com.google.gson.Gson;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Factura;
import com.rafaapp.rafacel.model.Linea;
import com.rafaapp.rafacel.model.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {


    public static Persona createPersona(Context context,
                                        String nombre,
                                        String apellido,
                                        String cedula,
                                        String telefonoFijo,
                                        Date fechaNacimiento,
                                        String marca,
                                        String descripcion) {
        Persona persona = new Persona();
        int id = getId(context);
        persona.setPerid(id+1);
        persona.setPerNombre(nombre);
        persona.setPerApellido(apellido);
        persona.setPerTelefonoFijo(telefonoFijo);
        persona.setPerCedula(cedula);
        persona.setPerFechaNacimiento(Utils.getDateStringFromDate(fechaNacimiento));
        persona.setEquipoCelular(new ArrayList<>());


        if (persona.isFullfilled()) {
            return persona;
        } else {
            return null;
        }
    }

    private static int getId(Context context) {
        return getPersonasJsonArray(context).length();
    }

    public static EquipoCelular createEquipoCelular(int perId,
                                                    Linea linea,
                                                    long serial,
                                                    String marca,
                                                    String descripcion,
                                                    EquipoCelular.EquEstado equEstado) {

        EquipoCelular equipoCelular = new EquipoCelular();
        equipoCelular.setPerId(perId);
        equipoCelular.setLinea(linea);
        equipoCelular.setEquSerial(serial);
        equipoCelular.setEquMarca(marca);
        equipoCelular.setEquDescripcion(descripcion);
        equipoCelular.setEquEstado(equEstado);
        equipoCelular.setFacturas(primeraFactura(perId, linea));


        if (equipoCelular.isFullFilled()) {
            return equipoCelular;
        } else {
            return null;
        }
    }

    private static ArrayList<Factura> primeraFactura(int perId, Linea linea) {
        Factura factura = new Factura();
        factura.setFacValor(80000.0);
        factura.setFacFechaEmision(Calendar.getInstance().getTime());
        factura.setFacNumero(1);
        factura.setPerId(perId);
        factura.setLinea(linea);
        ArrayList<Factura> facturas = new ArrayList<>();
        facturas.add(factura);
        return facturas;
    }

    private static long nuevoNumeroCelular(Context context) {
        final long min = 3062100000L;
        final long max = 3062199999L;
        final long random = ThreadLocalRandom.current().nextLong((max - min) + 1) + min;

        if (existNumeroCelularInDataBase(context, random)) {
            return nuevoNumeroCelular(context);
        } else {
            return random;
        }
    }

    public static boolean existNumeroCelularInDataBase(Context context, long nuevoNumeroCelular) {
        boolean result = false;
        if (nuevoNumeroCelular != 0L) {
            return result;
        }

        for (Persona persona : getPersonasFromDatabase(getPersonasJsonArray(context))) {
            for (EquipoCelular equipoCelular : persona.getEquipoCelular()) {
                result = equipoCelular.getLinea().getLinumerolinea() == nuevoNumeroCelular;
                if (result) return result;
            }
        }
        return result;
    }

    public static boolean existSerialInDataBase(Context context, long serial) {
        boolean result = false;
        if (serial == 0L) {
            return result;
        }
        for (Persona persona : getPersonasFromDatabase(getPersonasJsonArray(context))) {
            for (EquipoCelular equipoCelular : persona.getEquipoCelular()) {
                result = equipoCelular.getEquSerial() == serial;
                if (result) {
                    return result;
                }
            }
        }
        return result;
    }

    public static Linea createLinea(Context context, Persona persona) {
        Linea linea = new Linea();
        linea.setPerid(persona.getPerid());
        linea.setLinEstado(Linea.LineaEstado.Activa);
        linea.setLinumerolinea(nuevoNumeroCelular(context));
        return linea;
    }

    public static long nuevoSerial(Context context) {
        final long random = ThreadLocalRandom.current().nextLong();

        if (existSerialInDataBase(context, random)) {
            return nuevoSerial(context);
        } else {
            return random;
        }
    }

    public static Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static String getDateStringFromDate(Date date) {
        String dayOfTheWeek = (String) DateFormat.format("dd", date);
        String monthNumber = (String) DateFormat.format("MM", date);
        String year = (String) DateFormat.format("yyyy", date); // 2013

        return dayOfTheWeek + "-"+ monthNumber +"-"+ year;
    }

    public static Date getDateFromString(String dateString) {
        String[] fields = dateString.split("-");
        Calendar calendar = Calendar.getInstance();
        int day = Integer.parseInt(fields[0]);
        int month = Integer.parseInt(fields[1]);
        int year =  Integer.parseInt(fields[2]);
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static Factura getFacturaFromString(String factura) {
        return new Gson().fromJson(factura, Factura.class);
    }

    private static Linea getLineaFromString(String linea) {
        return new Gson().fromJson(linea, Linea.class);
    }

    private static Linea.LineaEstado getLineaEstadoFromString(String estado) {
        if (Linea.LineaEstado.Activa.name().equals(estado)) {
            return Linea.LineaEstado.Activa;
        } else {
            return Linea.LineaEstado.Suspendida;
        }
    }

    public static EquipoCelular getEquipoCelularFromString(String equipoCelularString) {
        return new Gson().fromJson(equipoCelularString, EquipoCelular.class);
    }

    private static EquipoCelular.EquEstado getEquipoEstadoFromString(String estado) {
        if (EquipoCelular.EquEstado.No_Reportado.name().equals(estado)) {
            return EquipoCelular.EquEstado.No_Reportado;
        } else {
            return EquipoCelular.EquEstado.Reportado;
        }
    }

    public static String getPerIDFromString(String personaString) {
        return personaString.split(",")[0];
    }

    public static JSONObject getJsonObjectFromPojo(Object object) {
        org.json.JSONObject jsonObject = new JSONObject();
        try {
            Gson gson = new Gson();
           jsonObject  = new JSONObject(gson.toJson(object));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray getPersonasJsonArray(Context context) {
        JSONArray jsonArray = new JSONArray();
        String info = getSharePreference(context).getString("arrayPersona", "");
        try {
            jsonArray = new JSONArray(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    public static ArrayList<Persona> getPersonasFromDatabase(JSONArray array) {
        ArrayList<Persona> personas = new ArrayList<>();
        Gson gson = new Gson();
        JSONObject jsonObject;

        for (int i = 0; i < array.length(); i++) {
            try {
               jsonObject = array.getJSONObject(i);
               Persona persona = gson.fromJson(jsonObject.toString(), Persona.class);
                personas.add(persona);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public static void showUsuarios(Context context, String titulo, Persona personaExtra, ArrayList<Persona> personas, Handler.Callback callback) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        builderSingle.setIcon(R.drawable.ic_notifications_black_24dp);
        builderSingle.setTitle(titulo);

        final ArrayAdapter<Persona> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_singlechoice);
        for (Persona persona: personas) {
            arrayAdapter.add(persona);
        }

        if (personaExtra != null) {
            arrayAdapter.add(personaExtra);
        }

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Persona persona = arrayAdapter.getItem(which);
                Message message = new Message();
                message.obj = persona;
                callback.handleMessage(message);
            }
        });

        builderSingle.show();
    }

    public static void showEstados(Context context, String titulo, ArrayList<EquipoCelular.EquEstado> estados, Handler.Callback callback) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        builderSingle.setIcon(R.drawable.ic_notifications_black_24dp);
        builderSingle.setTitle(titulo);

        final ArrayAdapter<EquipoCelular.EquEstado> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_singlechoice);
        for (EquipoCelular.EquEstado estado: estados) {
            arrayAdapter.add(estado);
        }

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EquipoCelular.EquEstado estado = arrayAdapter.getItem(which);
                Message message = new Message();
                message.obj = estado;
                callback.handleMessage(message);
            }
        });

        builderSingle.show();
    }

    public static void showDialog(Context context, String message, Handler.Callback callback) {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                context)
                .setTitle(message)
                .setCancelable(true)
                .setPositiveButton("Si", (dialog, which) -> {
                    Message message1 = new Message();
                    message1.obj = true;
                    callback.handleMessage(message1);
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create();
        alertDialog1.show();
    }

    public static ArrayList<Factura> getFacturasPerPerson(Persona persona, Date input, Date output) {
        ArrayList<Factura> result = new ArrayList<>();

        for (EquipoCelular equipoCelular: persona.getEquipoCelular()) {
            for (Factura factura: equipoCelular.getFacturas()) {
                if (factura.getFacFechaEmision().after(input) && factura.getFacFechaEmision().before(output)) {
                    result.add(factura);
                }
            }
        }
        return result;
    }

    public static ArrayList<Factura> getTodasFacturas(Context context, Date input, Date output) {
        ArrayList<Factura> result = new ArrayList<>();
        for (Persona persona: getPersonasFromDatabase(getPersonasJsonArray(context))) {
            for (EquipoCelular equipoCelular: persona.getEquipoCelular()) {
                for (Factura factura: equipoCelular.getFacturas()) {
                    if (factura.getFacFechaEmision().after(input) && factura.getFacFechaEmision().before(output)) {
                        result.add(factura);
                    }
                }
            }
        }
        return result;
    }

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("MiData", Context.MODE_PRIVATE);
    }
}
