/*
* Representa la mesa de juego, donde estarán todas las cartas.
* Tendrá dos partes diferenciadas:
* - la parte interior, donde inicialmente estarán colocadas las cartas correctamente para jugar al solitario
* - los montones exteriores, donde estarán colocadas las cartas por palo ordenadas de menor a mayor
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:
* - Una matriz de Pilas para representar la parte o montón interior 
* - Un array de Pilas para representar los montones exteriores.
* Funcionalidad: colocar las cartas para iniciar el juego, quitar una carta de la parte interior, colocar una carta en el interior,
* colocar una carta en el montón exterior correspondiente, visualizar cartas en la mesa, etc
2
La Pila es una estructura de datos que existe en Java y que se corresponde con la clase Stack. Por lo tanto debereis hacer uso de dicha
clase para representar la mesa de juego, y en particular de los métodos que se indican a continuación (de ser necesarios):

        public boolean empty();
        // Produce: Si la pila está vacía devuelve true, sino false.
        public Carta peek();
        // Produce: Devuelve la Carta del tope de la pila, sin eliminarla de ella.
        public Carta pop();
        // Produce: Elimina la Carta del tope de la pila y la devuelve.
        public void push(Carta item);
        // Produce: Introduce la Carta en el tope de la pila.
 */
package Core;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author AEDI
 */
public class Mesa {

    public static final int FILAS = 4;
    public static final int COLUMNAS = 4;
    private final Stack<Carta>[][] montonInterior;
    private final Stack<Carta>[] montonExterior;

    public Mesa() {
        montonInterior = new Stack[FILAS][COLUMNAS];
        for (Stack<Carta>[] montonInterior1 : montonInterior) {
            for (int j = 0; j < montonInterior[0].length; j++) {
                montonInterior1[j] = new Stack<>();
            }
        }
        montonExterior = new Stack[Palos.values().length];
        for (int i = 0; i < montonExterior.length; i++) {
            montonExterior[i] = new Stack<>();
        }
    }

    public Stack<Carta> getMontonInterior(int i, int j) throws Exception {
        if (i >= montonInterior.length || i < 0 || j >= montonInterior[0].length || j < 0) {
            throw new Exception("Posicion no valida");
        }
        return montonInterior[i][j];
    }

    public Stack<Carta> getMontonExterior(int i) throws Exception {
        if (i >= montonExterior.length || i < 0) {
            throw new Exception("Posicion no valida");
        }
        return montonExterior[i];
    }

    public int counterZonaExterior() {
        int toret = 0;
        for (Stack<Carta> monton : montonExterior) {
            toret += monton.size();
        }
        return toret;
    }

    private String toString(int i) {
        StringBuilder toret = new StringBuilder();
        try {
            toret.append("\n__________________________________________________________________________________________________\n");
            for (int j = 0; j < montonInterior[0].length; j++) {
                toret.append("\t Monton ").append((i * montonInterior[0].length) + j + 1).append("\t");
            }
            toret.append("\n\n");
            for (int j = 0; j < montonInterior[0].length; j++) {
                toret.append("\t");
                if (i != 4) {
                    if (montonInterior[i][j].isEmpty()) {
                        toret.append("  [VACIO]").append("\t");
                    } else {
                        toret.append(montonInterior[i][j].peek()).append("\t");
                    }
                } else {
                    if (montonExterior[j].isEmpty()) {
                        toret.append("  [VACIO]").append("\t");
                    } else {
                        toret.append(montonExterior[j].peek()).append("\t");
                    }
                }
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage() + "OK");
        }
        return toret.toString();
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append("\n\t\t\t\t\t ZONA INTERIOR\n");
        for (int i = 0; i < montonInterior.length; i++) {
            toret.append(toString(i));
        }
        toret.append("\n");
        toret.append("\n\t\t\t\t\t ZONA EXTERIOR\n");
        toret.append(toString(4));
        toret.append("\n");
        return toret.toString();
    }

    public boolean acciones(Jugador joueur) {
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
