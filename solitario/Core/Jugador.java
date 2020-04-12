/*
  *  Representa al único jugador de la partida, identificado por el nombre
  * Funcionalidad: le da la vuelta a una carta que está boca abajo, escoge una carta para moverla o al montón de descarte
  *                o la mueve encima de otra carta del interior
 */
package Core;

import java.util.Stack;
import IU.ES;

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
                    colocarCarta(baraja.devolverCarta(), mesa.getMontonInterior(fila, columna));
                } catch (Exception exc) {
                    System.err.println("ERROR: " + exc.getMessage());
                }
            }
        }
        for (int fila = 0; fila < Mesa.FILAS; fila++) {
            try {
                colocarCarta(baraja.devolverCarta(), mesa.getMontonInterior(fila, fila));
                colocarCarta(baraja.devolverCarta(), mesa.getMontonInterior(fila, 3 - fila));
            } catch (Exception exc) {
                System.err.println("ERROR: " + exc.getMessage());
            }
        }
        for (int fila = 0; fila < Mesa.FILAS; fila++) {
            for (int columna = 0; columna < Mesa.COLUMNAS; columna++) {
                try {
                    colocarCarta(baraja.devolverCarta(), mesa.getMontonInterior(fila, columna));
                } catch (Exception exc) {
                    System.err.println("ERROR: " + exc.getMessage());
                }
            }
        }
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void colocarCarta(Carta carta, Stack<Carta> destination) {
        destination.push(carta);
    }

    public void mover(Stack<Carta> origen, GetZonaMonton<Stack<Carta>, Boolean> destino) throws Exception {
        if (!origen.isEmpty()) {
            if (destino.zona) {
                if (destino.carta.isEmpty()) {
                    if (origen.peek().getNumero() == 1) {
                        colocarCarta(origen.pop(), destino.carta);
                    } else {
                        throw new Exception("No se puede mover la carta a la zona exterior");
                    }
                } else {
                    if ((destino.carta.peek().getNumero() == origen.peek().getNumero() - 1) && (destino.carta.peek().getPalo() == origen.peek().getPalo())) {
                        colocarCarta(origen.pop(), destino.carta);
                    } else {
                        throw new Exception("No se puede mover la carta a la zona elegida");
                    }
                }
            } else {
                if (destino.carta.isEmpty()) {
                    System.err.println("No se puede mover la carta a la zona elegida");
                } else {
                    if ((destino.carta.peek().getNumero() == origen.peek().getNumero() + 1) && (destino.carta.peek().getPalo() == origen.peek().getPalo())) {
                        colocarCarta(origen.pop(), destino.carta);
                    } else {
                        throw new Exception("No se puede mover la carta a la zona elegida");
                    }
                }
            }
        } else {
            throw new Exception("No es posible mover");
        }
    }

    public Stack origen() throws Exception {
        int numero = 0;
        while (numero < 1 || numero > 16) {
            numero = ES.pideNumero("\nIntroduzca la carta que se movera [1 - 16]: ");
            if (numero < 1 || numero > 16) {
                System.err.println("Se esperaba un numero [1 - 16]");
            }
        }
        return mesa.getMontonInterior(--numero / 4, numero % 4);
    }

    public GetZonaMonton<Stack<Carta>, Boolean> destino() throws Exception {
        int numero = 0;
        GetZonaMonton<Stack<Carta>, Boolean> destino;
        while (numero < 1 || numero > 20) {
            numero = ES.pideNumero("\nIntroduzca la carta que se movera [1 - 20]: ");
            if (numero < 1 || numero > 20) {
                throw new Exception("Se esperaba un numero [1 - 20]");
            }
        }
        if (--numero / 4 != 4) {
            destino = GetZonaMonton.get(mesa.getMontonInterior(numero / 4, numero % 4), false);
        } else {
            destino = GetZonaMonton.get(mesa.getMontonExterior(numero % 4), true);
        }
        return destino;
    }
}
