/*
  *  Representa al único jugador de la partida, identificado por el nombre
  * Funcionalidad: le da la vuelta a una carta que está boca abajo, escoge una carta para moverla o al montón de descarte
  *                o la mueve encima de otra carta del interior
 */
package Core;

import java.util.Stack;

/**
 * @author AEDI
 */
public class Jugador {

    private final Mesa mesa;

    public Jugador() {
        mesa = new Mesa();
        GenerarMesa();
    }

    public final void GenerarMesa() {
        Baraja baraja = new Baraja();
        for (int fila = 0; fila < Mesa.FILAS; fila++) {
            for (int columna = 0; columna < Mesa.COLUMNAS; columna++) {
                try {
                    mesa.getMontonInterior(fila, columna).add(baraja.devolverCarta());
                } catch (Exception exc) {
                    System.err.println("ERROR: " + exc.getMessage());
                }
            }
        }
        for (int fila = 0; fila < Mesa.FILAS; fila++) {
            try {
                mesa.getMontonInterior(fila, fila).add(baraja.devolverCarta());
                mesa.getMontonInterior(fila, 3 - fila).add(baraja.devolverCarta());
            } catch (Exception exc) {
                System.err.println("ERROR: " + exc.getMessage());
            }
        }
        for (int fila = 0; fila < Mesa.FILAS; fila++) {
            for (int columna = 0; columna < Mesa.COLUMNAS; columna++) {
                try {
                    mesa.getMontonInterior(fila, columna).add(baraja.devolverCarta());
                } catch (Exception exc) {
                    System.err.println("ERROR: " + exc.getMessage());
                }
            }
        }
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void mover(Stack<Carta> origen, GetZonaMonton<Stack<Carta>, Boolean> destino) throws Exception {
        if (!origen.isEmpty()) {
            if (destino.zona) { // True = Exterior
                if (destino.carta.isEmpty()) {
                    if (origen.peek().getNumero() == 1) {
                        destino.carta.add(origen.pop());
                    } else {
                        throw new Exception("No se puede mover la carta a la zona interior");
                    }
                } else {
                    if ((destino.carta.peek().getNumero() == origen.peek().getNumero() - 1) && (destino.carta.peek().getPalo() == origen.peek().getPalo())) {
                        destino.carta.add(origen.pop());
                    } else if ((destino.carta.peek().getNumero() == 7 && origen.peek().getNumero() == 10) && (destino.carta.peek().getPalo() == origen.peek().getPalo())) {
                        destino.carta.add(origen.pop());
                    } else {
                        throw new Exception("No se puede mover la carta a la zona elegida");
                    }
                }
            } else { //False = Interior
                if (destino.carta.isEmpty()) {
                    System.err.println("No se puede mover la carta a la zona elegida");
                } else {
                    if ((destino.carta.peek().getNumero() == origen.peek().getNumero() + 1) && (destino.carta.peek().getPalo() == origen.peek().getPalo())) {
                        destino.carta.add(origen.pop());
                    } else if ((destino.carta.peek().getNumero() == 10 && origen.peek().getNumero() == 7) && (destino.carta.peek().getPalo() == origen.peek().getPalo())) {
                        destino.carta.add(origen.pop());
                    } else {
                        throw new Exception("No se puede mover la carta a la zona elegida");
                    }
                }
            }
        } else {
            throw new Exception("No es posible mover");
        }
    }
}
