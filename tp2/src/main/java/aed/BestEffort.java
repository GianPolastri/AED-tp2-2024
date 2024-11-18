package aed;

import java.awt.PageAttributes;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
public class BestEffort {
    private Ciudad[] ciudades;
    private ArrayList<Integer> ciudadesMayorGanancia;
    private ArrayList<Integer> ciudadesMayorPerdida;
    private int gananciaTotal = 0;
    private int cantidadDeTrasladosDespachados = 0;
    private float promedioportraslado = (this.gananciaTotal/this.cantidadDeTrasladosDespachados);
    private Superheap manager;
    private Heap superavit;
    private Comparator<Integer> maxComparator = (a, b) -> b.compareTo(a);
    
    public BestEffort(int cantCiudades, Traslado[] traslados){
        this.ciudades = new Ciudad[cantCiudades]; 
        this.superavit = new Heap(this.maxComparator);
    for (int i = 0 ; i<cantCiudades;i++){
        Ciudad nuevaciudad = new Ciudad(i);
        this.ciudades[i] = nuevaciudad;
    }
    for(int i = 0; i < traslados.length;i++){
        manager.superencolar(traslados[i]);
    }
   
    }

    public void registrarTraslados(Traslado[] traslados){   
        for(int j = 0;j< traslados.length;j++){
            manager.superencolar(traslados[j]);
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];
        for (int i = 0 ; i<n;i++){
            Traslado actual = manager.desencolarRedito();
            res[i]=actual.id;
            cantidadDeTrasladosDespachados += 1;
            gananciaTotal += actual.gananciaNeta;
            this.ciudades[actual.origen].addEarnings(actual.gananciaNeta);
            this.ciudades[actual.destino].addLosses(actual.gananciaNeta);

            if(this.ciudades[this.ciudadesMayorGanancia.get(0)].getEarnings()< this.ciudades[actual.origen].getEarnings()){
                this.ciudadesMayorGanancia.set(0,actual.origen);
            }else if(this.ciudades[this.ciudadesMayorGanancia.get(0)].getEarnings() == this.ciudades[actual.origen].getEarnings()){
                this.ciudadesMayorGanancia.add(actual.origen);
            }
            if(this.ciudades[this.ciudadesMayorPerdida.get(0)].getLosses()> this.ciudades[actual.destino].getLosses()){
                this.ciudadesMayorPerdida.set(0,actual.destino);
            }else if(this.ciudades[this.ciudadesMayorPerdida.get(0)].getLosses() == this.ciudades[actual.origen].getLosses()){
                this.ciudadesMayorPerdida.add(actual.destino);
            }
            if(this.superavit.tamaño() == 0 ){
                this.superavit.encolar(actual.origen);

            }
            int iDraiz = (int) superavit.consultarRaiz();
            if(this.ciudades[actual.origen].getSuperavit() > this.ciudades[iDraiz].getSuperavit()){
                this.superavit = new Heap(this.maxComparator);
                this.superavit.encolar(actual.origen);

            }else if (this.ciudades[actual.origen].getSuperavit() == this.ciudades[iDraiz].getSuperavit()){
                this.superavit.encolar(actual.origen);
            }
        }
        
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];

        for (int i = 0 ; i<n;i++){
            Traslado actual = manager.desencolarAntiguedad();
            res[i]= actual.id;
            cantidadDeTrasladosDespachados += 1;
            gananciaTotal += actual.gananciaNeta;
            this.ciudades[actual.origen].addEarnings(actual.gananciaNeta);
            this.ciudades[actual.destino].addLosses(actual.gananciaNeta);

            if(this.ciudades[this.ciudadesMayorGanancia.get(0)].getEarnings()< this.ciudades[actual.origen].getEarnings()){

                this.ciudadesMayorGanancia.set(0,actual.origen);

            }else if(this.ciudades[this.ciudadesMayorGanancia.get(0)].getEarnings() == this.ciudades[actual.origen].getEarnings()){

                this.ciudadesMayorGanancia.add(actual.origen);
            }
            if(this.ciudades[this.ciudadesMayorPerdida.get(0)].getLosses()> this.ciudades[actual.destino].getLosses()){

                this.ciudadesMayorPerdida.set(0,actual.destino);

            }else if(this.ciudades[this.ciudadesMayorPerdida.get(0)].getLosses() == this.ciudades[actual.origen].getLosses()){

                this.ciudadesMayorPerdida.add(actual.destino);
            }
        
            if(this.superavit.tamaño() == 0 ){

            this.superavit.encolar(actual.origen);

            }
            int iDraiz = (int) superavit.consultarRaiz();
            if(this.ciudades[actual.origen].getSuperavit() > this.ciudades[iDraiz].getSuperavit()){
            this.superavit = new Heap(this.maxComparator);
            this.superavit.encolar(actual.origen);

            }else if (this.ciudades[actual.origen].getSuperavit() == this.ciudades[iDraiz].getSuperavit()){
            this.superavit.encolar(actual.origen);
        }
    }
        
        return res;
    }

    public int ciudadConMayorSuperavit(){

        Ciudad actual= (Ciudad) superavit.desencolarRaiz();
        return actual.getId();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        
        return this.ciudadesMayorGanancia;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        
        return this.ciudadesMayorPerdida;
    }

    public int gananciaPromedioPorTraslado(){
        
        return (Math.round(this.promedioportraslado));
    }
    
}
