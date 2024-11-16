package aed;

public class Superheap {
    private Heap<Handle> reditoHeap;
    private Heap<Handle> antiguedadHeap;

    public Superheap() {
        this.reditoHeap = new Heap<>( (h1, h2) -> Integer.compare(h1.getTraslado().gananciaNeta, h2.getTraslado().gananciaNeta) );
        
        this.antiguedadHeap = new Heap<>( (h1, h2) -> Integer.compare(h1.getTraslado().timestamp, h2.getTraslado().timestamp) );
    }

    public void encolar(Traslado traslado) {
        Handle handle = new Handle(traslado);

        handle.setPosRedito(reditoHeap.tamaño());
        handle.setPosAntiguedad(antiguedadHeap.tamaño());

        reditoHeap.encolar(handle);
        antiguedadHeap.encolar(handle);
        //? Faltaria de alguna manera pasar el indice que le queda al handle en el heap x despues de terminar de encolar y ordenar, para actualizar el valor de la pos en el otro heap. Lo mismo habría que hacer para desencolarMax y desencolar.

        //? Se me ocurre que para saber que indice queda en el heap que se esta trabajando para despues pasarlo y cambiar la variable posOtroHeap, podemos hacer que swap vaya devolviendo los indices que usa y tanto en siftUp como siftDown usar una varaiable que sea indexTracker, donde en la ultima pos debería quedar el indice con el que quedo el elemento despues del ordenamiento, asi podemos pasarlo y actualizar.

        //? El unico problema que estoy viendo es como actualizar constantemente todos los cambios de indice, y ahora no se me estaría ocurriendo como hacerlo (son las 7am lo, hago lo que puedo).
    }

}
