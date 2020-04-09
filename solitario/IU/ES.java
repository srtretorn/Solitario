
package solitario.IU;

import jdk.swing.interop.SwingInterOpUtils;
import solitario.Core.Palos;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author AEDI
 */
public class ES {
    public static Scanner leer = new Scanner(System.in);

    public static String pideCadena(String mensaje) {
        // Poner el mensaje
        System.out.print(mensaje);

        // Pedir
        return leer.nextLine();

    }


    public static int pideNumero(String mensaje) {

        System.out.print(mensaje);

        // Pedir
        return Integer.parseInt(leer.nextLine());

    }

    public static char pidePalo() {
        int op;

        do {
            System.out.print("Introduce el palo: \n" +
                    "\t1. OROS.\n" +
                    "\t2. COPAS\n" +
                    "\t3. ESPADAS\n" +
                    "\t4. BASTOS\n");
            op = Integer.parseInt(leer.nextLine());
        } while (op < 1 || op > 4);

        switch (op) {
            case 1:
                return 'O';
            case 2:
                return 'C';
            case 3:
                return 'E';
            case 4:
                return 'B';
            default:
                return ' ';
        }
    }
}
