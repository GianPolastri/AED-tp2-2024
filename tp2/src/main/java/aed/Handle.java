package aed;

public class Handle implements Comparable<Handle> {
    private Traslado traslado;
    private int posReditoHeap;
    private int posAntiguedadHeap;

    public Handle(Traslado traslado) {
        this.traslado = traslado;
        this.posReditoHeap = -1;
        this.posAntiguedadHeap = -1;
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
