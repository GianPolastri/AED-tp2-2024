package aed;

public class Superheap {
    private Heap<Handle> reditoHeap;
    private Heap<Handle> antiguedadHeap;

    public Superheap() {
        this.reditoHeap = new Heap<>( (h1, h2) -> Integer.compare(h1.getTraslado().gananciaNeta, h2.getTraslado().gananciaNeta) );
        
        this.antiguedadHeap = new Heap<>( (h1, h2) -> Integer.compare(h1.getTraslado().timestamp, h2.getTraslado().timestamp) );
    }

    public void encolar(Traslado traslado) {
        Handle mihandle = new Handle(traslado);
        Handle otroHandle = new Handle(traslado);
        mihandle.setPosPropioHeap(reditoHeap.tamaño()); 
        int pos = reditoHeap.encolar(mihandle); 
        otroHandle.setPosOtroHeap(pos);
        int posotro = antiguedadHeap.encolar(otroHandle);
        mihandle.setPosOtroHeap(posotro);
        actualizarPosEnElOtro(antiguedadHeap, reditoHeap);
    }


    public Traslado desencolarRedito(){
        Handle desencolado = (Handle) reditoHeap.desencolarRaiz();
        actualizarPosEnElOtro(reditoHeap,antiguedadHeap);
        antiguedadHeap.desencolar(desencolado.getPosOtroHeap());
        actualizarPosEnElOtro(antiguedadHeap, reditoHeap);
        return desencolado.getTraslado();

    }


    public Traslado desencolarAntiguedad(){
        Handle desencolado = (Handle) antiguedadHeap.desencolarRaiz();
        actualizarPosEnElOtro(antiguedadHeap,reditoHeap);
        reditoHeap.desencolar(desencolado.getPosOtroHeap());
        actualizarPosEnElOtro(reditoHeap, antiguedadHeap);
        return desencolado.getTraslado();

    }
    public void actualizarPosEnElOtro(Heap h1,Heap h2){
        for(int i = 0; i<h1.tamaño();i++){
            Handle actual = (Handle)h1.get(i);
            int posactual = actual.getPosPropioHeap();
            int posenelotro = actual.getPosOtroHeap();
            Handle otro = (Handle)h2.get(posenelotro);
            otro.setPosOtroHeap(posactual);
        }
    }
}
