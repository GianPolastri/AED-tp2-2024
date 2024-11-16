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
    }

}
