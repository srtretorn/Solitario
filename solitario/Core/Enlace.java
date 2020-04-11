package Core;

/**
 * @author StephanJ98
 * @param <U> Carta
 * @param <V> Zona de la carta
 */
public class Enlace<U, V> {

    public final U carta;
    public final V zona;

    private Enlace(U carta, V zona) {
        this.carta = carta;
        this.zona = zona;
    }

    public static <U, V> Enlace of(U carta, V zona) {
        return new Enlace<>(carta, zona);
    }
}
