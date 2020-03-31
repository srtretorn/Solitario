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

    private final int FILAS = 4;
    private final int COLUMNAS = 4;
    private Stack<Carta>[][] montonInterior = new Stack[FILAS][COLUMNAS];
    private Stack<Carta>[] montonExterior;
    private Baraja b = new Baraja();

    public void crearMontonInterior() {
        Stack<Carta> baraja = b.crearBaraja();
        System.out.println(baraja);
        

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                Carta carta = b.devolverCarta(baraja);
                System.out.print("[" + montonInterior[i][j].push(carta) + "]\t");
            }
            System.out.println();
        }

    }

    public void crearMontonExterior() {
        montonExterior = new Stack[FILAS];
        for (int i = 0; i < FILAS; i++) {
            System.out.print("[" + montonExterior[i].size() + "]");
        }
    }
}
