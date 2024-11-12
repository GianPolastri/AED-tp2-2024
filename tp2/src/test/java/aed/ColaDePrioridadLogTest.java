package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Comparator;
import org.junit.jupiter.api.Nested;

public class ColaDePrioridadLogTest {
    private ColaDePrioridadLog<Integer> heap;

    @BeforeEach
    void setUp() {
        Comparator<Integer> maxComparator = (a, b) -> a.compareTo(b);
        heap = new ColaDePrioridadLog<>(maxComparator);
    }

    @Test
    void testConstructorVacio() {
        assertEquals(0, heap.tamaño());
    }

    @Test
    void testConstructorConSecuencia() {
        Integer[] secuencia = {4, 2, 8, 5, 1};
        ColaDePrioridadLog<Integer> queue = new ColaDePrioridadLog<>(secuencia, (a, b) -> a.compareTo(b));

        assertEquals(5, queue.tamaño());
    }

    @Test
    void testEncolar() {
        heap.encolar(5);
        heap.encolar(3);
        heap.encolar(7);

        assertEquals(3, heap.tamaño());
        assertEquals(7, heap.consultarMax());
    }

    @Test
    void testConsultarMax() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(8, heap.consultarMax());
    }

    @Test
    void testConsultarMin() {
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(3);

        assertEquals(3, heap.consultarMin());
    }

    @Nested
    class DesencolarMaxTests {
        @Test
        void cuandoLaColaEstaVacia() {
            assertThrows(IllegalStateException.class, () -> heap.desencolarMax());
        }

        @Test
        void cuandoLaColaTieneUnElemento() {
            heap.encolar(5);

            assertEquals(5, heap.desencolarMax());
            assertEquals(0, heap.tamaño());
        }

        @Test
        void cuandoLaColaTieneVariosElementos() {
            heap.encolar(5);
            heap.encolar(8);
            heap.encolar(3);

            assertEquals(8, heap.desencolarMax());
            assertEquals(2, heap.tamaño());
        }
    }

    @Nested
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

    @Test
    void testCambiarPrioridad() {
        heap.encolar(5);
        heap.encolar(3);
        heap.encolar(7);

        heap.cambiarPrioridad(3, 9);
        assertEquals(9, heap.consultarMax());
    }
}
