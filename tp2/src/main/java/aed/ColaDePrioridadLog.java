package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class ColaDePrioridadLog<T extends Comparable<T>> implements ColaDePrioridad<T> {
    /* Atributos privados */
    private ArrayList<T> elementos; // ArrayList para almacenar los elementos
    private Comparator<T> comparador; // Comparador para el heap (max-heap o min-heap)
    
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
        heapify(); // Convierte el array en un heap
    }
    
    // Método para obtener una cola de prioridad vacía con el mismo comparador
    public ColaDePrioridad<T> colaDePrioridadVacía() {
        return new ColaDePrioridadLog<>(comparador); // Crea una cola de prioridad con el mismo comparador
    }

    // Método para encolar un valor
    public void encolar(T valor) {
        elementos.add(valor); // Añado el elemento al final
        siftUp(elementos.size() - 1); // Restauro la propiedad del heap
    }

    // Método para consultar el máximo (si es max-heap)
    public T consultarMax() {
        return elementos.get(0); // El máximo del max-heap está en la raíz
    }

    // Método para consultar el mínimo (si es min-heap)
    public T consultarMin() {
        return elementos.get(0); // El mínimo del min-heap está en la raíz
    }

    // Método para desencolar el máximo (si es max-heap)
    public T desencolarMax() {
        T max = elementos.get(0); // La raíz del heap es el máximo (para un max-heap)
        T ult = elementos.remove(elementos.size() - 1); // Elimino el último valor
        if (!elementos.isEmpty()) { // Si la secuencia no está vacía
            elementos.set(0, ult); // Coloco el último valor en la raíz
            siftDown(0); // Restauro la propiedad del heap
        }
        return max; 
    }

    // Método para desencolar el mínimo (si es min-heap)
    public T desencolarMin() {
        T min = elementos.get(0); // La raíz del heap es el mínimo (para un min-heap)
        T ult = elementos.remove(elementos.size() - 1); // Elimino el último valor
        if (!elementos.isEmpty()) { // Si la secuencia no está vacía
            elementos.set(0, ult); // Coloco el último valor en la raíz
            siftDown(0); // Restauro la propiedad del heap
        }
        return min;
    }

    // Método para cambiar la prioridad de un valor
    public void cambiarPrioridad(T valor, T nuevaprioridad) {
        int indice = elementos.indexOf(valor); // Busco el índice del valor
        T valorActual = elementos.get(indice); // Obtengo el valor actual en ese índice
        elementos.set(indice, nuevaprioridad); // Actualizo el valor con la nueva prioridad
        if (comparador.compare(nuevaprioridad, valorActual) > 0) {
            siftUp(indice); // Si la nueva prioridad es mayor, muevo el valor hacia arriba del heap
        } else {
            siftDown(indice); // Si la nueva prioridad es menor, muevo el valor hacia abajo del heap
        }
    }

    // Método para obtener el tamaño de la cola
    public int tamaño() {
        return elementos.size();
    }

    /* Métodos privados */

    // Método auxiliar para intercambiar dos elementos
    private void swap(int i, int j) {
        T temp = elementos.get(i);
        elementos.set(i, elementos.get(j));
        elementos.set(j, temp);
    }

    // Método para restablecer la propiedad del heap moviendo el valor hacia arriba
    private void siftUp(int indice) {
        int padre = (indice - 1) / 2; // Índice del padre
        while (indice > 0 && comparador.compare(elementos.get(indice), elementos.get(padre)) > 0) {
            swap(indice, padre); // Intercambia el nodo actual con su padre
            indice = padre;      // Actualiza el índice al del padre
            padre = (indice - 1) / 2; // Calcula el nuevo índice del padre
        }
    }
    
    // Método para restablecer la propiedad del heap moviendo el valor hacia abajo
    private void siftDown(int indice) {
        int tamaño = elementos.size(); // Longitud del array 
        int hijoIzq = 2 * indice + 1;  // Índice del hijo izquierdo
        int hijoDer = 2 * indice + 2;  // Índice del hijo derecho
        int mayor;
        while (hijoIzq < tamaño) {
            // Determino cuál de los dos hijos es el mayor
            if (hijoDer < tamaño && comparador.compare(elementos.get(hijoDer), elementos.get(hijoIzq)) > 0) {
                mayor = hijoDer; // Si el hijo derecho es mayor, lo elijo
            } else {
                mayor = hijoIzq; // Si no, elijo el hijo izquierdo
            }
            // Si el elemento actual es mayor o igual que el mayor hijo, entonces interrumpo el ciclo
            if (comparador.compare(elementos.get(indice), elementos.get(mayor)) >= 0) {
                break;
            } else { // Si no, intercambio y continúo
                swap(indice, mayor);
                indice = mayor;
                hijoIzq = 2 * indice + 1;
                hijoDer = 2 * indice + 2;
            }
        }
    }

    // Método para convertir el array en un heap
    private void heapify() {
        for (int j = elementos.size() / 2 - 1; j >= 0; j--) {
            siftDown(j);
        }
    }
}