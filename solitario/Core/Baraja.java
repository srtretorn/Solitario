/*
* Representa la baraja española, 40 baraja, 4 palos, valores de las baraja de 1 a 12 (excepto 8 y 9). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de baraja
 */
package Core;

import java.util.Collections;
import java.util.Stack;

public class Baraja {

    private Stack<Carta> baraja;

    public Baraja() {
        baraja = new Stack<>();
        for (int num = 1; num <= (40 / Palos.values().length); num++) {
            for (Palos palo : Palos.values()) {
                try {
                    switch(num){
                        case 8:
                            baraja.add(new Carta(palo,10));
                            break;
                        case 9:
                            baraja.add(new Carta(palo,11));
                            break;
                        case 10:
                            baraja.add(new Carta(palo,12));
                            break;
                        default:
                            baraja.add(new Carta(palo,num));
                    }
                } catch (Exception exc) {
                    System.err.println("ERROR: " + exc.getMessage());
                }
            }
        }
        Collections.shuffle(baraja);
    }

    public Carta devolverCarta() {
        return baraja.pop();
    }

}
