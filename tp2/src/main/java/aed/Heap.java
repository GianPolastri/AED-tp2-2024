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
        this.comparador = comparador;
        for (T elemento : secuencia) {
            this.elementos.add(elemento);
            heapify(); //? Quizas habria que ver de cambiar heapify por siftDown, para mantener complejidad O(n);
        }
       // System.out.println("Lista antes de de ordenar: " + this.elementos);
        // siftUp(this.elementos.size() - 1);
        // System.out.println("Post crear el heap (se usa siftUp): " + this.elementos);
        // heapify();
        // System.out.println("Post crear el heap (se usa siftDown): " + this.elementos);
    }

    public int encolar(T valor) {
        elementos.add(valor); 
        //System.out.println("Antes del siftUp de encolar: " + this.elementos);

        return siftUp(elementos.size() - 1);

    }


    public T consultarRaiz() {
        return elementos.get(0); 
    }

    public T desencolarRaiz() {
       // System.out.println("Se llama a desencolar.");
        T max = elementos.get(0); 
        T ult = elementos.remove(elementos.size() - 1); 
        if (!elementos.isEmpty()) { 
            elementos.set(0, ult); 
            siftDown(0); 
        }
       // System.out.println(elementos);
        return max; 
    }

    public T desencolar(int indice){
        T desencolado = this.elementos.get(indice);
        T ult = this.elementos.get(this.elementos.size()-1);
        this.elementos.set(indice, ult);
        this.elementos.remove(this.elementos.size()-1);
        siftDown(indice);
        //System.out.println("Lista dsp de desencolar del medio: " + this.elementos.toString());
        return desencolado; 
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
       // System.out.println(elementos.size());
        return elementos.size();
    }

    private int swap(int i, int j) {
        T tempI = elementos.get(i);
        T tempJ = elementos.get(j);
        int ultimapos = -1;
        if(tempI.getClass().equals(Handle.class) && tempJ.getClass().equals(Handle.class)){
            Handle handleI = (Handle) tempI;
            handleI.setPosPropioHeap(j);
            Handle handleJ = (Handle) tempJ;
            handleJ.setPosPropioHeap(i);
            elementos.set(i, elementos.get(j));
            elementos.set(j, tempI);
            return j;
        }
        elementos.set(i, elementos.get(j));
        elementos.set(j, tempI);
        return j;
    }

    private int siftUp(int indice) {
        int ultimapos = indice;
       // System.out.println(indice);
        while (indice > 0) {
            int padre = (indice - 1) / 2; 
           
            if (comparador.compare(elementos.get(indice), elementos.get(padre)) > 0) {
                ultimapos = swap(indice, padre);
                // System.out.println("Despues del swap de siftUp: " + this.elementos);
                indice = padre;
               
            } else {
                // System.out.println("Padre: " + elementos.get(padre) + " es mas grande que: " + elementos.get(indice));
                break; 
            }
        }
        
        return ultimapos;
    }
    
    private int siftDown(int indice) {
        int ultimapos = indice;
        // System.out.println("elementos al entrar al siftDown: " + elementos);
        int hijoIzq = 2 * indice + 1; 
        int hijoDer = 2 * indice + 2; 
        int mayor = indice;
        int comparadorHijos = 0;
        if(hijoDer < elementos.size() && hijoIzq < elementos.size()){
            comparadorHijos = comparador.compare(elementos.get(hijoIzq), elementos.get(hijoDer));
        }
        if (hijoIzq < elementos.size() && comparador.compare(elementos.get(hijoIzq), elementos.get(mayor)) > 0 && comparadorHijos >= 0){
            ultimapos = swap(mayor, hijoIzq);
            // System.out.println("Despues del swap con hijoIzq: " + elementos);
            siftDown(hijoIzq);
        }
        if(hijoDer < elementos.size() && comparador.compare(elementos.get(hijoDer),elementos.get(mayor))>0){
            ultimapos = swap(mayor, hijoDer);
            // System.out.println("Despues del swap con hijoDer: " + elementos);
            siftDown(hijoDer);

        }
        if(mayor != indice){
            ultimapos = swap(indice,mayor);
            siftDown(mayor);
        }
        return ultimapos;
    }

    private void heapify() {
        for (int j = 0; j < this.elementos.size(); j++) {
            siftDown(j);
        }
    }

    @Override
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
    public T get(int i){
        return this.elementos.get(i);
    }
}
