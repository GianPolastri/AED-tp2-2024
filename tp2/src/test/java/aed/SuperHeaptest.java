package aed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class SuperHeaptest {
    private Heap<Integer> heap;
    /*@Test
    void encolarydesencolartest(){
        Superheap superheap = new Superheap();

        Traslado t1 = new Traslado(1, 101, 201, 100, 10);
        Traslado t2 = new Traslado(2, 102, 202, 300, 20);
        Traslado t3 = new Traslado(3, 103, 203, 200, 30);
        Traslado t4 = new Traslado(1, 101, 201, 120, 40);

        // Encolar los traslados
        superheap.superencolar(t1);
        superheap.superencolar(t2);
        superheap.superencolar(t3);
        superheap.superencolar(t4);
        //System.out.println(" antiguedad puntero into  "+superheap.antiguedadHeap.get(0).getTraslado()+ " timestamp   " +superheap.antiguedadHeap.get(0).getTraslado().timestamp+ "redito      "+ superheap.antiguedadHeap.get(0).getTraslado().gananciaNeta);
        //System.out.println(" antiguedad puntero into  "+superheap.antiguedadHeap.get(1).getTraslado()+ " timestamp   " +superheap.antiguedadHeap.get(1).getTraslado().timestamp+ "redito      "+ superheap.antiguedadHeap.get(1).getTraslado().gananciaNeta);
        //System.out.println(" antiguedad puntero into  "+superheap.antiguedadHeap.get(2).getTraslado()+ " timestamp   " +superheap.antiguedadHeap.get(2).getTraslado().timestamp+ "redito      "+ superheap.antiguedadHeap.get(2).getTraslado().gananciaNeta);
        //System.out.println(" antiguedad puntero into  "+superheap.antiguedadHeap.get(3).getTraslado()+ " timestamp   " +superheap.antiguedadHeap.get(3).getTraslado().timestamp+ "redito      "+ superheap.antiguedadHeap.get(3).getTraslado().gananciaNeta);
        //System.out.println("REDITO HEAP    "+superheap.reditoHeap.toString());
        //System.out.println("ANTIGUEDAD HEAP "+superheap.antiguedadHeap.toString());
       


        // Desencolar por ganancia y verificar el orden correcto
        Traslado resultado1 = superheap.desencolarRedito();
        if (resultado1.gananciaNeta != 300) {
            throw new AssertionError("Error: se esperaba gananciaNeta 300, pero se obtuvo " + resultado1.gananciaNeta);
        }

        Traslado resultado2 = superheap.desencolarRedito();
        if (resultado2.gananciaNeta != 200) {
            throw new AssertionError("Error: se esperaba gananciaNeta 200, pero se obtuvo " + resultado2.gananciaNeta);
        }

        Traslado resultado3 = superheap.desencolarRedito();
        if (resultado3.gananciaNeta != 120) {
            throw new AssertionError("Error: se esperaba gananciaNeta 120, pero se obtuvo " + resultado3.gananciaNeta);
        }
        Traslado resultado4 = superheap.desencolarRedito();
        if (resultado4.gananciaNeta != 100) {
            throw new AssertionError("Error: se esperaba gananciaNeta 120, pero se obtuvo " + resultado3.gananciaNeta);
        }

        // Encolar los traslados nuevamente para probar desencolar por antigüedad
        superheap.superencolar(t1);
        superheap.superencolar(t2);
        superheap.superencolar(t3);
        superheap.superencolar(t4);

        Traslado resultado8 = superheap.desencolarAntiguedad();
        if (resultado8.timestamp != 40) {
            throw new AssertionError("Error: se esperaba timestamp 10, pero se obtuvo " + resultado4.timestamp);
        }

        Traslado resultado5 = superheap.desencolarAntiguedad();
        if (resultado5.timestamp != 30) {
            throw new AssertionError("Error: se esperaba timestamp 10, pero se obtuvo " + resultado4.timestamp);
        }

        Traslado resultado7 = superheap.desencolarAntiguedad();
        if (resultado7.timestamp != 20) {
            throw new AssertionError("Error: se esperaba timestamp 20, pero se obtuvo " + resultado5.timestamp);
        }

        Traslado resultado6 = superheap.desencolarAntiguedad();
        if (resultado6.timestamp != 10) {
            throw new AssertionError("Error: se esperaba timestamp 30, pero se obtuvo " + resultado6.timestamp);
        }

        // Mensaje final de éxito
        System.out.println("Todas las pruebas pasaron.");
    }*/
    @Test
    void borraruno(){
        Superheap superheap = new Superheap();

        Traslado t1 = new Traslado(1, 101, 201, 100, 10);
        Traslado t2 = new Traslado(2, 102, 202, 300, 20);
        Traslado t3 = new Traslado(3, 103, 203, 200, 30);
        Traslado t4 = new Traslado(4, 101, 201, 120, 40);

        superheap.superencolar(t1);
        superheap.superencolar(t2);
        superheap.superencolar(t3);
        superheap.superencolar(t4);
       

        superheap.desencolarRedito();
        assertEquals(3,superheap.antiguedadHeap.tamaño());
        assertEquals(3,superheap.reditoHeap.tamaño());
        assertEquals(40, superheap.antiguedadHeap.get(0).getTraslado().timestamp);
        assertEquals(200, superheap.reditoHeap.get(0).getTraslado().gananciaNeta);
        assertEquals(30, superheap.antiguedadHeap.get(1).getTraslado().timestamp);
        assertEquals(120, superheap.reditoHeap.get(1).getTraslado().gananciaNeta);
        assertEquals(10, superheap.antiguedadHeap.get(2).getTraslado().timestamp);
        assertEquals(100, superheap.reditoHeap.get(2).getTraslado().gananciaNeta);
        assertEquals(200,superheap.desencolarRedito().gananciaNeta);
        assertEquals(40, superheap.antiguedadHeap.get(0).getTraslado().timestamp);
        assertEquals(120, superheap.reditoHeap.get(0).getTraslado().gananciaNeta);

    }
    @Test
        void elemntos(){
        Superheap superheap = new Superheap();
        Traslado t1 = new Traslado(1, 101, 201, 10, 10);
        Traslado t2 = new Traslado(2, 102, 202, 30, 0);
        Traslado t3 = new Traslado(2, 102, 202, 40, 5);
        superheap.superencolar(t1);
        superheap.superencolar(t2);
        superheap.superencolar(t3);
        superheap.superencolar(t3);

        }

}