package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class ColaDePrioridadLog<T extends Comparable<T>> implements ColaDePrioridad<T> {
    /* Atibutos privados */

    private ArrayList<T> elementos; // ArrayList para almacenar los elementos
    private Comparator<T> comparador; // Comparador para el heap (max o min)
    
    /* Métodos públicos */

    // Constructor con un comparador (max-heap o min-heap)
    public ColaDePrioridadLog(Comparator<T> comparador) {
        this.elementos = new ArrayList<>();
        this.comparador = comparador;
    }

    // Constructor para crear la cola de prioridad a partir de una secuencia (heapify)
    public ColaDePrioridadLog(T[] secuencia, Comparator<T> comparador) {
        this.elementos = new ArrayList<>();
        for (int i = 0; i < secuencia.length; i++) {
            this.elementos.add(secuencia[i]);
        }
        this.comparador = comparador;
        heapify(); //Convertimos el array en un heap
    }
    
    @Override
    public ColaDePrioridad<T> colaDePrioridadVacía() {
        return new ColaDePrioridadLog<>(comparador); // Creamos una cola de prioridad con el mismo comparador
    }

    @Override
    public void encolar(T valor) {
        elementos.add(valor); // Añadimos el elemento al final
        siftUp(elementos.size() - 1); // Restauramos la propiedad del heap
    }

    @Override
    public T consultarMax() {
        return elementos.get(0); // La raíz del heap es el máximo o mínimo dependiendo del comparador
    }

    @Override
    public T desencolarMax() {
        T max = elementos.get(0); // La raíz del heap es el máximo o mínimo dependiendo del comparador
        T ult = elementos.remove(elementos.size() - 1); // Eliminamos el último valor
        if (!elementos.isEmpty()) { // Si la secuencia no está vacía
            elementos.set(0, ult); // Colocamos el último valor en la raíz
            siftDown(0); // Restauramos la propiedad del heap
        }
        return max; 
    }

    @Override
    public void cambiarPrioridad(T valor, T nuevaprioridad) {
        int indice = elementos.indexOf(valor);
        T valorActual = elementos.get(indice);
        elementos.set(indice, nuevaprioridad);
        if (comparador.compare(nuevaprioridad, valorActual) > 0) {
            siftUp(indice); // Si la nueva prioridad es mayor, subimos el ¿elemento o el índice?
        } else {
            siftDown(indice); // Si la nueva prioridad en menor, bajamos el ¿elemento o el índice?
        }
    }

    @Override
    public int tamaño() {
        return elementos.size();
    }

    /* Métodos privados */

    // private void siftUp(int indice)
    // private void siftDown(int indice)
    // private void heapify() 
}
