package aed;

public class Handle implements Comparable<Handle> {
    private Traslado traslado;
    private int posOtroHeap;
    private int posPropioHeap;
    //? Idealmente me gustaria que las variables de pos se llamen distinto. Al momento de actualizar en el swap, se hace engorroso pensar en cual es la posicion a modificar. Quizas usando una sola variable para indicar la pos en el otro heap alcanza. La pos en el heap que se trabaja ya se tiene para trabajar en el propio heap. Agrego las variables con los nombres cambiados para poder avanzar un poco.

    public Handle(Traslado traslado) {
        this.traslado = traslado;
        this.posPropioHeap = -1;
        this.posOtroHeap = -1;
    }

    public Traslado getTraslado() {
        return this.traslado;
    }

    public int getPosPropioHeap() {
        return this.posPropioHeap;
    }

    public int getPosOtroHeap() {
        return this.posOtroHeap;
    }

    public void setPosPropioHeap(int pos) {
        this.posPropioHeap = pos;
    }

    public void setPosOtroHeap(int pos) {
        this.posOtroHeap = pos;
    }


    @Override
    public int compareTo(Handle other) {
        return Integer.compare(this.traslado.gananciaNeta, other.traslado.gananciaNeta);
    }
    

    @Override
    public String toString() {
        return "Handle {Traslado: " + traslado + ", posPropioHeap: " + posPropioHeap + ", posOtroHeap: " + posOtroHeap + "}";
    }
}
