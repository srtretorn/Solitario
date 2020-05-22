package Core;

/**
 * @author StephanJ98
 * @param <U> Carta
 * @param <V> Zona de la carta True -> Exterior , False -> Interior
 */
public class GetZonaMonton<U, V> {

    public final U carta;
    public final V zona;

    private GetZonaMonton(U carta, V zona) {
        this.carta = carta;
        this.zona = zona;
    }

    public static <U, V> GetZonaMonton get(U carta, V zona) {
        return new GetZonaMonton<>(carta, zona);
    }
}
