package aed;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class HeapTest {
    private Heap<Integer> heap;

    @BeforeEach
    void setUp() {
        Comparator<Integer> maxComparator = (a, b) -> a.compareTo(b);
        heap = new Heap<>(maxComparator);
    }

    @Test
    void testConstructorVacio() {
        assertEquals(0, heap.tamaño());
    }

    @Test
    void testConstructorConSecuencia() {
        Integer[] secuencia = {4, 2, 8, 5, 1};
        Heap<Integer> queue = new Heap<>(secuencia, (a, b) -> a.compareTo(b));

        assertEquals(5, queue.tamaño());
    }

    @Test
    void testEncolar() {
        heap.encolar(5);
        heap.encolar(3);
        heap.encolar(7);
        System.out.println(this.heap);
        assertEquals(3, heap.tamaño());
    }

    @Test
    void testConsultarRaiz() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(8, heap.consultarRaiz());
    }
    @Test
    void testEncolaryDesencolar() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(8, heap.consultarRaiz());
        heap.desencolarRaiz();
        assertEquals(5, heap.consultarRaiz());
        heap.desencolarRaiz();
        assertEquals(3, heap.consultarRaiz());
    }

    /* @Test
    void testConsultarMin() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(3, heap.consultarMin());
    } */

    @Nested
    class DesencolarRaizTests {
        @Test
        void cuandoLaColaEstaVacia() {
            assertThrows(IllegalStateException.class, () -> heap.desencolarRaiz());
        }

        @Test
        void cuandoLaColaTieneUnElemento() {
            heap.encolar(5);

            assertEquals(5, heap.desencolarRaiz());
            assertEquals(0, heap.tamaño());
        }

        @Test
        void cuandoLaColaTieneVariosElementos() {
            heap.encolar(5);
            heap.encolar(8);
            heap.encolar(3);

            assertEquals(8, heap.desencolarRaiz());
            assertEquals(2, heap.tamaño());
        }
    }

/*     @Nested
    class DesencolarMinTests {
        @Test
        void cuandoLaColaEstaVacia() {
            assertThrows(IllegalStateException.class, () -> heap.desencolarMin());
        }

        @Test
        void cuandoLaColaTieneUnElemento() {
            heap.encolar(5);

            assertEquals(5, heap.desencolarMin());
            assertEquals(0, heap.tamaño());
        }

        @Test
        void cuandoLaColaTieneVariosElementos() {
            heap.encolar(5);
            heap.encolar(8);
            heap.encolar(3);

            assertEquals(3, heap.desencolarMin());
            assertEquals(2, heap.tamaño());
        }
    }
 */
    /* @Test
    void testCambiarPrioridad() {
        heap.encolar(5);
        heap.encolar(3);
        heap.encolar(7);

        heap.cambiarPrioridad(3, 9);
        assertEquals(9, heap.consultarRaiz());
    } */
}