/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package solitario.Core;

/**
 *
 * @author AEDI
 */
public class Carta {
    private int numero;
    private char letraPalo;
    
    public Carta(int n, char lp){
        numero = n;
        letraPalo = lp;
    }

    
    public int getNumero(){
        return numero;
    }
    
    public char getLetraPalo(){
        return letraPalo;
    }
    
    public void setNumero(int n){
        n = numero;
    }
    
    public void setLetraPalo(char lp){
        lp = letraPalo;
    }
    
    @Override
    public String toString(){
        return  numero +" "+ letraPalo;
    }
    
}
