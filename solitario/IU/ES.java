package IU;

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
        int toret = 0;
        boolean condition = false;

        while (!condition) {
            try { // Si se introduce algo que no sea un número se produce la exception,
                //NumberFormatException que es capturada
                toret = Integer.parseInt(pideCadena(mensaje));
                condition = true;
            } catch (NumberFormatException exc) {
                System.err.println("ERROR Caracter no valido !!!!");
            }
        }
        return toret;
    }
}
