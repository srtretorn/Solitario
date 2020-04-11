/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package Core;

/**
 * @author AEDI
 */
public class Carta {

    private Palos palo;
    private int numero;

    public Carta(Palos palo, int numero) throws Exception {
        if ((palo != Palos.BASTOS && palo != Palos.COPAS && palo != Palos.ESPADAS && palo != Palos.OROS) || (numero < 1 || numero > 10)) {
            throw new Exception("Carta no valida");
        }
        this.palo = palo;
        this.numero = numero;
    }

    public Palos getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        if (getNumero() == 8) {
            toret.append("Sota");
        }
        if (getNumero() == 9) {
            toret.append("Caballo");
        }
        if (getNumero() == 10) {
            toret.append("Rey");
        }else if(getNumero() != 8 && getNumero() != 9 && getNumero() != 10) {
            toret.append(getNumero());
        }
        toret.append(" | ").append(getPalo());
        return toret.toString();
    }
}
