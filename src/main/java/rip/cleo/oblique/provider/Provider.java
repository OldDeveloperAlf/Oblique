package rip.cleo.oblique.provider;

/**
 * Basic Provider interface easily implemented using lambdas
 *
 * @param <T> the object to provide for
 * @param <V> the value
 */
public interface Provider<T, V> {

    /**
     * Invoked to provide an {@link V} for the {@link T}
     *
     * @param t the {@link T}
     * @return {@link V}
     */
    V get(T t);

}
