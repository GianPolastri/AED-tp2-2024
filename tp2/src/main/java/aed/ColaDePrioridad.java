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
     * Devuelve el máximo o el mínimo según sea un max-heap o un min-heap
     * @return máx o mín
     */
    public T consultarMax();

    /**
     * Saca el valor máximo (o mínimo) de cola y lo devuelve
     * @return máx o mín
     */
    public T desencolarMax();

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
