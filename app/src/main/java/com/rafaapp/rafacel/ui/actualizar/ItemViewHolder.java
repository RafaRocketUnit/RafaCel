package com.rafaapp.rafacel.ui.actualizar;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.rafaapp.rafacel.R;


public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView apellido;
    private TextView cedula;
    private TextView telefonoFijo;
    private TextView fechaNacimiento;
    private TextView factura;
    private TextView equipoCelular;

    public ItemViewHolder(View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.nombre);
        apellido = itemView.findViewById(R.id.apellido);
        cedula = itemView.findViewById(R.id.cedula);
        telefonoFijo = itemView.findViewById(R.id.telefonoFijo);
        fechaNacimiento = itemView.findViewById(R.id.fechaNacimiento);
        factura = itemView.findViewById(R.id.factura);
        equipoCelular = itemView.findViewById(R.id.equipoCelular);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getApellido() {
        return apellido;
    }

    public void setApellido(TextView apellido) {
        this.apellido = apellido;
    }

    public TextView getCedula() {
        return cedula;
    }

    public void setCedula(TextView cedula) {
        this.cedula = cedula;
    }

    public TextView getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(TextView telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public TextView getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(TextView fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TextView getFactura() {
        return factura;
    }

    public void setFactura(TextView factura) {
        this.factura = factura;
    }

    public TextView getEquipoCelular() {
        return equipoCelular;
    }

    public void setEquipoCelular(TextView equipoCelular) {
        this.equipoCelular = equipoCelular;
    }
}
