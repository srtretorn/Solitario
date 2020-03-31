/*
* Representa la baraja española, 40 cartas, 4 palos, valores de las cartas de 1 a 12 (excepto 8 y 9). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package solitario.Core;

import java.util.Collections;

import java.util.Stack;

public class Baraja {

    private final Stack<Carta> baraja = new Stack();
    private char letra;
    private Carta c;

    //Método que crea y desordena una baraja
    public Stack<Carta> crearBaraja() {
        for (int i = 1; i < 13; i++) {
            if (i != 8 && i != 9) {
                for(Palos palo: Palos.values()){
                    letra = palo.toString().charAt(0);
                    c = new Carta(i,letra);
                    baraja.push(c);
                }
            }
        }
        Collections.shuffle(baraja);
        return baraja;
    }
    
    //Método que devuelve la carta superior
    public Carta devolverCarta(Stack<Carta> b) {
        return b.pop();
    }

}
