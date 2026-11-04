package com.example.restaurante;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private String cliente;
    private String telefono;
    private LocalDate fecha;
    private LocalTime hora;
    private int personas;
    private int numeroMesa;
    private String estado;  // "PENDIENTE", "CONFIRMADA" o "CANCELADA"

    public Reserva(int id, String cliente, String telefono, LocalDate fecha,
                   LocalTime hora, int personas, int numeroMesa, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.telefono = telefono;
        this.fecha = fecha;
        this.hora = hora;
        this.personas = personas;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public int getPersonas() { return personas; }
    public void setPersonas(int personas) { this.personas = personas; }
    public int getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(int numeroMesa) { this.numeroMesa = numeroMesa; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /** id|cliente|telefono|fecha|hora|personas|mesa|estado */
    public String aLinea() {
        return id + "|" + cliente.replace("|", " ") + "|" + telefono.replace("|", " ")
                + "|" + fecha + "|" + hora + "|" + personas + "|" + numeroMesa + "|" + estado;
    }

    public static Reserva desdeLinea(String linea) {
        String[] p = linea.split("\\|", -1);
        if (p.length != 8) {
            throw new IllegalArgumentException("Se esperaban 8 campos y hay " + p.length);
        }
        return new Reserva(
                Integer.parseInt(p[0].trim()),
                p[1].trim(),
                p[2].trim(),
                LocalDate.parse(p[3].trim()),
                LocalTime.parse(p[4].trim()),
                Integer.parseInt(p[5].trim()),
                Integer.parseInt(p[6].trim()),
                p[7].trim());
    }
}