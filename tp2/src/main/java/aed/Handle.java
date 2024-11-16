package aed;

public class Handle implements Comparable<Handle> {
    private Traslado traslado;
    private int posReditoHeap;
    private int posAntiguedadHeap;
    private int posOtroHeap;
    private int posPropioHeap;
    //? Idealmente me gustaria que las variables de pos se llamen distinto. Al momento de actualizar en el swap, se hace engorroso pensar en cual es la posicion a modificar. Quizas usando una sola variable para indicar la pos en el otro heap alcanza. La pos en el heap que se trabaja ya se tiene para trabajar en el propio heap. Agrego las variables con los nombres cambiados para poder avanzar un poco.

    public Handle(Traslado traslado) {
        this.traslado = traslado;
        this.posReditoHeap = -1;
        this.posAntiguedadHeap = -1;
        this.posPropioHeap = -1;
        this.posOtroHeap = -1;
    }

    public Traslado getTraslado() {
        return this.traslado;
    }

    public int getPosRedito() {
        return this.posReditoHeap;
    }

    public int getPosAntiguedad() {
        return this.posAntiguedadHeap;
    }

    public void setPosRedito(int pos) {
        this.posReditoHeap = pos;
    }

    public void setPosAntiguedad(int pos) {
        this.posAntiguedadHeap = pos;
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

    public void actualizarPosiciones(int posRedito, int posAntiguedad) {
        this.posReditoHeap = posRedito;
        this.posAntiguedadHeap = posAntiguedad;
    }

    @Override
    public int compareTo(Handle other) {
        // Ejemplo de comparación basada en la ganancia neta del Traslado
        return Integer.compare(this.traslado.gananciaNeta, other.traslado.gananciaNeta);
    }

    @Override
    public String toString() {
        return "Handle {Traslado: " + traslado + ", posReditoHeap: " + posReditoHeap + ", posAntiguedadHeap: " + posAntiguedadHeap + "}";
    }
}
