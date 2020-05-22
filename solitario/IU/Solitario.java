package IU;

import Core.*;
import java.util.Stack;

public class Solitario {

    private enum Estado_Juego {
        EN_JUEGO, VICTORIA, DERROTA
    }

    public static void inicioPartida() {
        Estado_Juego estado = Estado_Juego.EN_JUEGO;
        Jugador jugador = new Jugador();
        System.out.println(jugador.getMesa());
        while (estado == Estado_Juego.EN_JUEGO) {
            try {
                jugador.mover(origen(jugador), destino(jugador));
                if (jugador.getMesa().counterZonaExterior() == 40) {
                    estado = Estado_Juego.VICTORIA;
                } else if (!jugador.getMesa().acciones(jugador)) {
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

    public static Stack origen(Jugador jugador) throws Exception {
        int numero = 0;
        while (numero < 1 || numero > 16) {
            numero = ES.pideNumero("\nIntroduzca el número del monton origen [1 - 16]: ");
            if (numero < 1 || numero > 16) {
                throw new Exception("Se esperaba un numero [1 - 16]");
            }
        }
        return jugador.getMesa().getMontonInterior(--numero / 4, numero % 4);
    }

    public static GetZonaMonton<Stack<Carta>, Boolean> destino(Jugador jugador) throws Exception {
        int numero = 0;
        GetZonaMonton<Stack<Carta>, Boolean> destino;
        while (numero < 1 || numero > 20) {
            numero = ES.pideNumero("\nIntroduzca el número del monton destino [1 - 20]: ");
            if (numero < 1 || numero > 20) {
                throw new Exception("Se esperaba un numero [1 - 20]");
            }
        }
        if (--numero / 4 != 4) {
            destino = GetZonaMonton.get(jugador.getMesa().getMontonInterior(numero / 4, numero % 4), false);
        } else {
            destino = GetZonaMonton.get(jugador.getMesa().getMontonExterior(numero % 4), true);
        }
        return destino;
    }
}
