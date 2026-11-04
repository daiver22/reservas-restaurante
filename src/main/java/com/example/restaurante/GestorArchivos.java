package com.example.restaurante;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {

    private static final Path CARPETA = Paths.get("datos");
    private static final Path ARCHIVO_MESAS = CARPETA.resolve("mesas.txt");
    private static final Path ARCHIVO_RESERVAS = CARPETA.resolve("reservas.txt");

    // ---------- MESAS ----------

    public static List<Mesa> cargarMesas() {
        if (!Files.exists(ARCHIVO_MESAS)) {
            guardarMesas(mesasDeEjemplo());
        }
        List<Mesa> mesas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ARCHIVO_MESAS, StandardCharsets.UTF_8);
            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea.isBlank()) continue;
                try {
                    mesas.add(Mesa.desdeLinea(linea));
                } catch (Exception e) {
                    System.out.println("mesas.txt línea " + (i + 1) + " ignorada: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer mesas.txt: " + e.getMessage());
        }
        return mesas;
    }

    public static void guardarMesas(List<Mesa> mesas) {
        List<String> lineas = new ArrayList<>();
        for (Mesa m : mesas) {
            lineas.add(m.aLinea());
        }
        escribir(ARCHIVO_MESAS, lineas);
    }

    private static List<Mesa> mesasDeEjemplo() {
        List<Mesa> m = new ArrayList<>();
        m.add(new Mesa(1, 2, "Salón", 0, 0));
        m.add(new Mesa(2, 2, "Salón", 0, 1));
        m.add(new Mesa(3, 4, "Salón", 0, 2));
        m.add(new Mesa(4, 4, "Salón", 1, 0));
        m.add(new Mesa(5, 6, "Salón", 1, 1));
        m.add(new Mesa(6, 8, "Salón", 1, 2));
        m.add(new Mesa(7, 2, "Terraza", 2, 0));
        m.add(new Mesa(8, 4, "Terraza", 2, 1));
        m.add(new Mesa(9, 4, "Terraza", 2, 2));
        return m;
    }

    // ---------- RESERVAS ----------

    public static List<Reserva> cargarReservas() {
        if (!Files.exists(ARCHIVO_RESERVAS)) {
            guardarReservas(reservasDeEjemplo());
        }
        List<Reserva> reservas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ARCHIVO_RESERVAS, StandardCharsets.UTF_8);
            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea.isBlank()) continue;
                try {
                    reservas.add(Reserva.desdeLinea(linea));
                } catch (Exception e) {
                    System.out.println("reservas.txt línea " + (i + 1) + " ignorada: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer reservas.txt: " + e.getMessage());
        }
        return reservas;
    }

    public static void guardarReservas(List<Reserva> reservas) {
        List<String> lineas = new ArrayList<>();
        for (Reserva r : reservas) {
            lineas.add(r.aLinea());
        }
        escribir(ARCHIVO_RESERVAS, lineas);
    }

    private static List<Reserva> reservasDeEjemplo() {
        LocalDate hoy = LocalDate.now();
        List<Reserva> r = new ArrayList<>();
        r.add(new Reserva(1, "Ana Pérez", "3001112233", hoy, LocalTime.of(19, 0), 2, 1, "CONFIRMADA"));
        r.add(new Reserva(2, "Carlos Ruiz", "3004445566", hoy, LocalTime.of(20, 0), 4, 3, "PENDIENTE"));
        r.add(new Reserva(3, "Ana Pérez", "3001112233", hoy.plusDays(1), LocalTime.of(13, 0), 2, 2, "PENDIENTE"));
        r.add(new Reserva(4, "Ana Pérez", "3001112233", hoy.plusDays(2), LocalTime.of(20, 0), 3, 4, "PENDIENTE"));
        return r;
    }

    // ---------- UTILIDAD ----------

    private static void escribir(Path archivo, List<String> lineas) {
        try {
            Files.createDirectories(CARPETA);
            Files.write(archivo, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("No se pudo escribir " + archivo + ": " + e.getMessage());
        }
    }
}