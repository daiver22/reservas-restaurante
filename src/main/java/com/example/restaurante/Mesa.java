package com.example.restaurante;

public class Mesa {
    private int numero;
    private int capacidad;
    private String zona;   // "Salón" o "Terraza"
    private int fila;
    private int columna;

    public Mesa(int numero, int capacidad, String zona, int fila, int columna) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.zona = zona;
        this.fila = fila;
        this.columna = columna;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }
    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    /** Convierte la mesa en una línea: numero|capacidad|zona|fila|columna */
    public String aLinea() {
        return numero + "|" + capacidad + "|" + zona.replace("|", " ") + "|" + fila + "|" + columna;
    }

    /** Crea una mesa desde una línea. Lanza excepción si la línea está mal escrita. */
    public static Mesa desdeLinea(String linea) {
        String[] p = linea.split("\\|", -1);
        if (p.length != 5) {
            throw new IllegalArgumentException("Se esperaban 5 campos y hay " + p.length);
        }
        return new Mesa(
                Integer.parseInt(p[0].trim()),
                Integer.parseInt(p[1].trim()),
                p[2].trim(),
                Integer.parseInt(p[3].trim()),
                Integer.parseInt(p[4].trim()));
    }

    @Override
    public String toString() {
        return "Mesa " + numero + " (" + capacidad + " pers., " + zona + ")";
    }
}