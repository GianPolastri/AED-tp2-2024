package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class ColaDePrioridadLog<T extends Comparable<T>> implements ColaDePrioridad<T> {

    private ArrayList<T> elementos; 
    private Comparator<T> comparador; 
    

    public ColaDePrioridadLog(Comparator<T> comparador) {
        super();
        this.elementos = new ArrayList<>();
        this.comparador = comparador;
    }

    // Constructor para crear la cola de prioridad a partir de una secuencia (heapify)
    public ColaDePrioridadLog(T[] secuencia, Comparator<T> comparador) {
        super();
        this.elementos = new ArrayList<>();
        for (int i = 0; i < secuencia.length; i++) {
            this.elementos.add(secuencia[i]);
        }
        this.comparador = comparador;
        heapify(); 
    }
    public ColaDePrioridad<T> colaDePrioridadVacía() {

        return new ColaDePrioridadLog<>(comparador); 
    }

    public void encolar(T valor) {
        elementos.add(valor); 
        siftUp(elementos.size() - 1); 
    }


    public T consultarMax() {
        return elementos.get(0); 
    }

    public T desencolarMax() {
        T max = elementos.get(0); 
        T ult = elementos.remove(elementos.size() - 1); 
        if (!elementos.isEmpty()) { 
            elementos.set(0, ult); 
            siftDown(0); 
        }
        return max; 
    }

    public void cambiarPrioridad(T valor, T nuevaprioridad) {
        int indice = elementos.indexOf(valor);
        T valorActual = elementos.get(indice);
        elementos.set(indice, nuevaprioridad);
        if (comparador.compare(nuevaprioridad, valorActual) > 0) {
            siftUp(indice); 
        } else {
            siftDown(indice); 
        }
    }
    public int tamaño() {
        return elementos.size();
    }

    private void swap(int i, int j) {
        T temp = elementos.get(i);
        elementos.set(i, elementos.get(j));
        elementos.set(j, temp);
    }

    private void siftUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2; 
            if (comparador.compare(elementos.get(indice), elementos.get(padre)) > 0) {
                swap(indice, padre); 
                indice = padre;      
            } else {
                break; 
            }
        }
    }
    
    private void siftDown(int indice) {
        int hijoIzq = 2 * indice + 1; 
        int hijoDer = 2 * indice + 2; 
        int mayor = indice;           
        
    }

    private void heapify() {
        for (int j = elementos.size() / 2 -  1; j >=0; j--) {
            siftDown(j);
        }
    }
}
