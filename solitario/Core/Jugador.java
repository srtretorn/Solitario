 /*
  *  Representa al único jugador de la partida, identificado por el nombre
  * Funcionalidad: le da la vuelta a una carta que está boca abajo, escoge una carta para moverla o al montón de descarte
  *                o la mueve encima de otra carta del interior
  */
 package solitario.Core;

 import solitario.IU.ES;

 import java.util.Stack;

 /**
  * @author AEDI
  */
 public class Jugador {
     private String nombre;

     public String getNombre() {
         return nombre;
     }

     public void darVuelta(Stack<Carta> monton) {
         if (!monton.empty()) {
             monton.peek().setBocaArriba(true);
         }
     }

     public void moverCarta(Stack<Carta>[][] montonInterior, Stack<Carta>[] montonExterior) {

         System.out.println("Que carta quieres mover?");
         int numero = ES.pideNumero("número: ");
         char palo = ES.pidePalo();
         Carta carta = new Carta(numero, palo);
         carta.setBocaArriba(true);

         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < 4; j++) {
                 if (!montonInterior[i][j].empty() && carta.equals(montonInterior[i][j].peek())) {

                     Carta cartaParaMover = montonInterior[i][j].peek();

                     switch (cartaParaMover.getLetraPalo()) {
                         case 'O':
                             efectuarMovimiento(montonInterior, montonInterior[i][j], montonExterior[Palos.OROS.getIndexPalo()], montonInterior[i][j].peek());
                             break;
                         case 'C':
                             efectuarMovimiento(montonInterior, montonInterior[i][j], montonExterior[Palos.COPAS.getIndexPalo()], montonInterior[i][j].peek());
                             break;
                         case 'E':
                             efectuarMovimiento(montonInterior, montonInterior[i][j], montonExterior[Palos.ESPADAS.getIndexPalo()], montonInterior[i][j].peek());
                             break;
                         case 'B':
                             efectuarMovimiento(montonInterior, montonInterior[i][j], montonExterior[Palos.BASTOS.getIndexPalo()], montonInterior[i][j].peek());
                             break;
                     }
                 }
             }
         }
     }

     public boolean sePuedeMoverCarta(Stack<Carta> cartasPalo, Carta carta, boolean externo) {
         Carta cartaSuperior = getCartaSuperior(cartasPalo);

         if (externo) {
             if (cartaSuperior == null) {
                 return carta.getNumero() == 1;
             }
             if (cartaSuperior.getNumero() == carta.getNumero() - 1) {
                 return true;
             }
             return cartaSuperior.getNumero() == 7 && carta.getNumero() == 10;
         }
         if (cartaSuperior == null) {
             return false;
         }
         if (cartaSuperior.getNumero() == carta.getNumero() + 1) {
             return true;
         }
         return cartaSuperior.getNumero() == 10 && carta.getNumero() == 7;
     }

     private Carta getCartaSuperior(Stack<Carta> cartasPalo) {
         return cartasPalo.empty() ? null : cartasPalo.peek();
     }

     private void efectuarMovimiento(Stack<Carta>[][] montonInterior, Stack<Carta> montonInteriorOrigen, Stack<Carta> montonExteriorObjetivo, Carta cartaParaMover) {
         boolean sePuedeMoverFuera;
         boolean sePuedeMoverDentro = false;
         Stack<Carta> montonInteriorObjetivo = null;

         sePuedeMoverFuera = sePuedeMoverCarta(montonExteriorObjetivo, cartaParaMover, true);
         for (int k = 0; k < 4; k++) {
             for (int l = 0; l < 4; l++) {
                 if (sePuedeMoverCarta(montonInterior[k][l], cartaParaMover, false)) {
                     sePuedeMoverDentro = true;
                     montonInteriorObjetivo = montonInterior[k][l];
                 }
             }
         }
         if (sePuedeMoverDentro) {
             System.out.println("Puedes mover la carta en el montón interior.");
         } else {
             System.out.println("No puedes mover la carta en el montón interior.");
         }
         if (sePuedeMoverFuera) {
             System.out.println("Puedes mover la carta al montón exterior.");
         } else {
             System.out.println("No puedes mover la carta al montón exterior.");
         }
         if (sePuedeMoverDentro && sePuedeMoverFuera) {
             int op = ES.pideNumero("A donde quieres mover la carta?\n" +
                     "\t1. Interior\n" +
                     "\t2. Exterior\n");
             switch (op) {
                 case 1:
                     montonInteriorOrigen.pop();
                     darVuelta(montonInteriorOrigen);
                     montonInteriorObjetivo.push(cartaParaMover);
                     break;
                 case 2:
                     montonInteriorOrigen.pop();
                     darVuelta(montonInteriorOrigen);
                     montonExteriorObjetivo.push(cartaParaMover);
                     break;
             }
         } else if (sePuedeMoverDentro) {
             montonInteriorOrigen.pop();
             darVuelta(montonInteriorOrigen);
             montonInteriorObjetivo.push(cartaParaMover);
         } else if (sePuedeMoverFuera) {
             montonInteriorOrigen.pop();
             darVuelta(montonInteriorOrigen);
             montonExteriorObjetivo.push(cartaParaMover);
         }
     }
 }
