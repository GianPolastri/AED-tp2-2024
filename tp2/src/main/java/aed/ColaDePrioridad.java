package aed;

public interface ColaDePrioridad<T> {
    /**
     * Devuelve una cola de prioridad vacía
     * 
     */
    public ColaDePrioridad<T> colaDePrioridadVacía();
    
    /**
     * Pone el parámetro valor al final de la cola 
     * @param valor 
     */
    public void encolar(T valor);
    
    /**
     * Devuelve el máximo de un max-heap 
     * @return máx
     */
    public T consultarMax();

    /**
     * Devuelve el mínimo de un min-heap 
     * @return mín
     */
    public T consultarMin();

    /**
     * Saca el valor máximo del max-heap y lo devuelve
     * @return máx
     */
    public T desencolarMax();

    /**
     * Saca el valor mínimo del min-heap y lo devuelve
     * @return mín
     */
    public T desencolarMin();

    /**
     * Dado un valor cambia la prioridad
     * @param valor
     * @param nuevaPrioridad
     */
    public void cambiarPrioridad(T valor, T nuevaPrioridad);

    /**
     * Devuelve el tamaño de la cola
     * @return tamaño
     */
    public int tamaño();
}
