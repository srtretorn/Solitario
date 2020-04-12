package IU;

import Core.*;
import java.util.EmptyStackException;

public class Solitario {

    private static enum Estado_Juego {
        EN_JUEGO, VICTORIA, DERROTA
    };
    private static Estado_Juego estado = Estado_Juego.EN_JUEGO;

    public static void inicioPartida() {
        Jugador jugador = new Jugador();
        System.out.println(jugador.getMesa());
        while (estado == Estado_Juego.EN_JUEGO) {
            try {
                jugador.mover(jugador.origen(), jugador.destino());
                if (jugador.getMesa().counterZonaExterior() == 40) {
                    estado = Estado_Juego.VICTORIA;
                } else if (!acciones(jugador)) {
                    estado = Estado_Juego.DERROTA;
                }
            } catch (Exception exc) {
                System.err.println("ERROR: " + exc.getMessage());
            } finally {
                System.out.println(jugador.getMesa());
            }
        }
        if (estado == Estado_Juego.VICTORIA) {
            System.out.println("VICTORIA, HAS GANADO !!!!!");
        } else {
            System.out.println("HAS PERDIDO");
        }
    }

    private static boolean acciones(Jugador joueur) {
        boolean toret = false;
        Mesa mesa = joueur.getMesa();
        int filas = 0;
        while (!toret && filas != Mesa.FILAS) {
            int columnas = 0;
            while (!toret && columnas != Mesa.COLUMNAS) {
                int monton = 0;
                while (!toret && monton != Palos.values().length) {
                    try {
                        if (mesa.getMontonInterior(filas, columnas).isEmpty()) {
                            if (mesa.getMontonExterior(monton).isEmpty() && mesa.getMontonInterior(filas, columnas).peek().getNumero() == 1) {
                                toret = true;
                            } else if (mesa.getMontonExterior(monton).peek().getNumero() + 1 == mesa.getMontonInterior(filas, columnas).peek().getNumero() && mesa.getMontonExterior(monton).peek().getPalo() == mesa.getMontonInterior(filas, columnas).peek().getPalo()) {
                                toret = true;
                            }
                        }
                    } catch (EmptyStackException exc) {
                        /*
                        Error recurrente a causa de la funcion .peek() que bajo ciertas condiciones
                        lanza una EmptyStackException, no tiene nigun efecto en el desarrollo de la partida
                        ni en los datos ya almacenados.
                         */
                    } catch (Exception exc) {
                        System.err.println("ERROR: " + exc.toString());
                    }
                    monton++;
                }
                int fila = 0;
                while (!toret && fila != Mesa.FILAS) {
                    int columna = 0;
                    while (!toret && columna != Mesa.COLUMNAS) {
                        try {
                            if (!mesa.getMontonInterior(filas, columnas).isEmpty() && !mesa.getMontonInterior(fila, columna).isEmpty()) {
                                if (mesa.getMontonInterior(fila, columna).peek().getNumero() - 1 == mesa.getMontonInterior(filas, columnas).peek().getNumero() && mesa.getMontonInterior(fila, columna).peek().getPalo() == mesa.getMontonInterior(filas, columnas).peek().getPalo()) {
                                    toret = true;
                                }
                            }
                        } catch (Exception exc) {
                            System.err.println("ERROR: " + exc.getMessage());
                        }
                        columna++;
                    }
                    fila++;
                }
                columnas++;
            }
            filas++;
        }
        return toret;
    }
}
