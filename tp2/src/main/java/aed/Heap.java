package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T extends Comparable<T>>{

    private ArrayList<T> elementos; 
    private Comparator<T> comparador; 
    

    public Heap(Comparator<T> comparador) {
        super();
        this.elementos = new ArrayList<>();
        this.comparador = comparador;
    }

    // Constructor para crear la cola de prioridad a partir de una secuencia (heapify)
    public Heap(T[] secuencia, Comparator<T> comparador) {
        
        this.elementos = new ArrayList<>();
        for (T elemento : secuencia) {
            this.elementos.add(elemento);
        }
        this.comparador = comparador;
        heapify(); 
    }

    public void encolar(T valor) {
        elementos.add(valor); 
        siftUp(elementos.size() - 1);
        System.out.println(this.elementos);
    }


    public T consultarRaiz() {
        return elementos.get(0); 
    }

    public T desencolarRaiz() {
        T max = elementos.get(0); 
        T ult = elementos.remove(elementos.size() - 1); 
        if (!elementos.isEmpty()) { 
            elementos.set(0, ult); 
            siftDown(0); 
        }
        return max; 
    }
// chequear si lo usamos en algun momento,si no,lo volamos 
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
        if (hijoIzq < elementos.size() && comparador.compare(elementos.get(hijoIzq), elementos.get(mayor)) > 0){
            mayor = hijoIzq;
        }
        if(hijoDer < elementos.size() && comparador.compare(elementos.get(hijoIzq),elementos.get(mayor))>0){
            mayor = hijoDer;

        }
        if(mayor != indice){
            swap(indice,mayor);
            siftDown(mayor);
        }
        
    }

    private void heapify() {
        for (int j = elementos.size() / 2 -  1; j >=0; j--) {
            siftDown(j);
        }
    }

/*@Override
public String toString() {
    StringBuilder resultado = new StringBuilder("{");
    if (!elementos.isEmpty()) {
        resultado.append(elementos.get(0));
        for (int i = 1; i < elementos.size(); i++) {
            resultado.append(", ").append(elementos.get(i));
        }
    }
    resultado.append("}");
    return resultado.toString();
}
*/
}
