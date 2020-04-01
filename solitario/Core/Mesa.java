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
package solitario.Core;

import java.util.Stack;

/**
 *
 * @author AEDI
 */
public class Mesa {

    private final int FILAS = 5;
    private final int COLUMNAS = 5;
    private Stack<Carta>[][] montonInterior = new Stack[FILAS][COLUMNAS];
    private Stack<Carta>[] montonExterior;
    private Baraja b = new Baraja();

    public void crearMontonInterior() {
        Stack<Carta> baraja = b.crearBaraja();
        for (int i = 0; i < FILAS - 1; i++) {
            for (int j = 0; j < COLUMNAS - 1; j++) {
                montonInterior[i][j] = new Stack<>();
                if (i == j || i + j == 3) {
                    Carta cartaDiagonales = b.devolverCarta(baraja);
                    montonInterior[i][j].push(cartaDiagonales);
                }
                Carta carta = b.devolverCarta(baraja);
                montonInterior[i][j].push(carta);
                Carta cartaEncima = b.devolverCarta(baraja);
                montonInterior[i][j].push(cartaEncima);
            }
        }
    }

    public void crearMontonExterior() {
        montonExterior = new Stack[FILAS];
        for (int i = 0; i < FILAS; i++) {
            montonExterior[i] = new Stack<>();
            System.out.print("[" + montonExterior[i] + "]");
        }
    }

    public String toStringMontonInterior() {
        String toret = "";
        for (int i = 0; i < FILAS - 1; i++) {
            for (int j = 0; j < COLUMNAS - 1; j++) {
                toret += "[" + montonInterior[i][j].peek() + "]\t";
            }
            toret += "\n";
        }
        return toret;
    }

    public String toStringMontonExterior() {
        String toret = "";
        for (int i = 0; i < FILAS - 1; i++) {
            toret += "[" + montonExterior[i].peek() + "]\t";
        }
        return toret;
    }
}

