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
        heapify(); //Convierto el array en un heap
    }
    
    @Override
    public ColaDePrioridad<T> colaDePrioridadVacía() {
        return new ColaDePrioridadLog<>(comparador); // Creo una cola de prioridad con el mismo comparador
    }

    @Override
    public void encolar(T valor) {
        elementos.add(valor); // Añado el elemento al final
        siftUp(elementos.size() - 1); // Restauro la propiedad del heap
    }

    @Override
    public T consultarMax() {
        return elementos.get(0); // El máximo del max-heap es la raíz
    }

    @Override
    public T consultarMin() {
        return elementos.get(0); // El mínimo del min-heap es la raíz
    }

    @Override
    public T desencolarMax() {
        T max = elementos.get(0); // La raíz del heap es el máximo (para un max-heap)
        T ult = elementos.remove(elementos.size() - 1); // Elimino el último valor
        if (!elementos.isEmpty()) { // Si la secuencia no está vacía
            elementos.set(0, ult); // Coloco el último valor en la raíz
            siftDown(0); // Restauro la propiedad del heap
        }
        return max; 
    }

    @Override
    public T desencolarMin() {
        T min = elementos.get(0); // La raíz del heap es el mínimo (para un min-heap)
        T ult = elementos.remove(elementos.size() - 1); // Elimino el último valor
        if (!elementos.isEmpty()) { // Si la secuencia no está vacía
            elementos.set(0, ult); // Coloco el último valor en la raíz
            siftDown(0); // Restauro la propiedad del heap
        }
        return min;
    }

    @Override
    public void cambiarPrioridad(T valor, T nuevaprioridad) {
        int indice = elementos.indexOf(valor); // Busco el índice del valor
        T valorActual = elementos.get(indice); // Obtengo el valor actual en ese índice
        elementos.set(indice, nuevaprioridad); // Actualizo el valor con la nueva prioridad
        if (comparador.compare(nuevaprioridad, valorActual) > 0) {
            siftUp(indice); // Si la nueva prioridad es mayor, muevo el valor hacia arriba del heap
        } else {
            siftDown(indice); // Si la nueva prioridad en menor, muevo el valor hacia abajo del heap
        }
    }

    @Override
    public int tamaño() {
        return elementos.size();
    }

    /* Métodos privados */

    private void swap(int i, int j) {
        T temp = elementos.get(i);
        elementos.set(i, elementos.get(j));
        elementos.set(j, temp);
    }

    private void siftUp(int indice) {
        // Caso base
        if (indice > 0) {
            int padre = (indice - 1) / 2;
            // Si el nodo actual es mayor que el padre, los intercambio
            if (comparador.compare(elementos.get(indice), elementos.get(padre)) > 0);
            swap(indice, padre);
            // Y hago la llamada recursiva para seguir subiendo
            siftUp(padre);
        }
    }

    private void siftDown(int indice) {
        int hijoIzq = 2 * indice + 1; // Índice del hijo izquierdo
        int hijoDer = 2 * indice + 2; // Índice del hijo derecho
        int mayor = indice;           // Asumo que el mayor es índice actual
        
    }

    private void heapify() {
        for (int j = elementos.size() / 2 -  1; j >=0; j--) {
            siftDown(j);
        }
    }
}
