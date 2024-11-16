package aed;

import java.util.ArrayList;
import java.util.Comparator;
import org.javatuples.Pair;

public class Heap<T extends Comparable<T>>{
    private Pair<Traslado,Handle> tupla;
    private Comparator<T> comparador;
    private ArrayList<Pair<T,Handle>> elementos;
    

    public Heap(Comparator<T> comparador) {
        super();
        this.elementos = new ArrayList<Pair<T,Handle>>();
        this.comparador = comparador;
        this.tupla = new Pair<Traslado,Handle>(null, null);
    }

    // Constructor para crear la cola de prioridad a partir de una secuencia (heapify)
    public Heap(T[] secuencia, Comparator<T> comparador) {
        this.elementos = new ArrayList<>();
        for (T elemento : secuencia) {
            this.elementos.add(new Pair<T,Handle>(elemento,new Handle(this.elementos.size())));
        }
        this.comparador = comparador;
        heapify(); 
     }

    public void encolar( Pair tupla) {
        elementos.add(tupla); 
        
        siftUp(elementos.size() - 1);
        
    }


    public T consultarRaiz() {
        return elementos.get(0).getValue0(); 
    }

    public T desencolarRaiz() {

        T max = elementos.get(0).getValue0(); 
        Pair ult = elementos.remove(elementos.size() - 1); 
        if (!elementos.isEmpty()) { 
            elementos.set(0, ult); 
            siftDown(0); 
        }
        return max; 
    }
// chequear si lo usamos en algun momento,si no,lo volamos 
    //public void cambiarPrioridad(T valor, T nuevaprioridad) {
      //  int indice = elementos.indexOf(valor);
        //T valorActual = elementos.get(indice);
       // elementos.set(indice, nuevaprioridad);
        //if (comparador.compare(nuevaprioridad, valorActual) > 0) {
        //    siftUp(indice); 
       // } else {
        //    siftDown(indice); 
       // }
   // }
    public int tamaño() {
        return elementos.size();
    }

    private void swap(int i, int j) {
        Pair temp = elementos.get(i);
        elementos.set(i, elementos.get(j));
        elementos.set(j, temp);
    }

    private void siftUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2; 
            if (comparador.compare(elementos.get(indice).getValue0(), elementos.get(padre).getValue0()) > 0) {
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
        int comparadorHijos = 0;
        if(hijoDer < elementos.size() && hijoIzq < elementos.size()){
            comparadorHijos = comparador.compare(elementos.get(hijoIzq).getValue0(), elementos.get(hijoDer).getValue0());
        }
        if (hijoIzq < elementos.size() && comparador.compare(elementos.get(hijoIzq).getValue0(), elementos.get(mayor).getValue0()) > 0 && comparadorHijos >= 0){
            swap(mayor, hijoIzq);
            
            siftDown(hijoIzq);
        }
        if(hijoDer < elementos.size() && comparador.compare(elementos.get(hijoDer).getValue0(),elementos.get(mayor).getValue0())>0){
            swap(mayor, hijoDer);
            siftDown(hijoDer);

        }
        if(mayor != indice){
            swap(indice,mayor);
            siftDown(mayor);
        }
        
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

}
