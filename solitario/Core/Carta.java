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
        int counter = 0;
        for (Palos iter : Palos.values()) {
            if (palo != iter) {
                counter++;
            }
        }
        if (counter != 3 || (numero < 1 || numero > 12 || numero == 8 || numero == 9)) {
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
        if (getNumero() == 10) {
            toret.append("Sota");
        }
        if (getNumero() == 11) {
            toret.append("Caballo");
        }
        if (getNumero() == 12) {
            toret.append("Rey");
        } else if (getNumero() != 10 && getNumero() != 11 && getNumero() != 12) {
            toret.append(getNumero());
        }
        toret.append(" | ").append(getPalo());
        return toret.toString();
    }
}
