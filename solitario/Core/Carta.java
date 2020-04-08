/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package solitario.Core;

/**
 * @author AEDI
 */
public class Carta {
    private int numero;
    private char letraPalo;
    private boolean bocaArriba;

    public Carta(int n, char lp) {
        numero = n;
        letraPalo = lp;
    }

    public int getNumero() {
        return numero;
    }

    public char getLetraPalo() {
        return letraPalo;
    }

    public boolean isBocaArriba() {
        return bocaArriba;
    }

    public void setNumero(int n) {
        numero = n;
    }

    public void setLetraPalo(char lp) {
        letraPalo = lp;
    }

    public void setBocaArriba(boolean bocaArriba) {
        this.bocaArriba = bocaArriba;
    }

    @Override
    public String toString() {
        return numero + " " + letraPalo + " " + bocaArriba;
    }
}