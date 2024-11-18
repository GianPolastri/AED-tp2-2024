package aed;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class BestEffortTests {
    int cantCiudades;
    Traslado[] listaTraslados;

    @BeforeEach
    void init() {
        cantCiudades = 4;
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 10),
            new Traslado(2, 1, 2, 200, 20),
            new Traslado(3, 2, 3, 300, 30)
        };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2)
                if (e1 == e2) encontrado = true;
            assertTrue(encontrado, "No se encontró el elemento " + e1 + " en el arreglo " + s2.toString());
        }
    }

    @Test
    void testConstructor() {
        BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
        assertNotNull(sis);
    }

    @Nested
    class RegistrarTraslados {
        @Test
        void registrarUnoSolo() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            Traslado[] nuevos = new Traslado[] {
                new Traslado(4, 0, 1, 400, 40)
            };
            sis.registrarTraslados(nuevos);
        }

        @Test
        void registrarMultiples() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            Traslado[] nuevos = new Traslado[] {
                new Traslado(4, 0, 1, 400, 40),
                new Traslado(5, 1, 2, 500, 50),
                new Traslado(6, 2, 3, 600, 60)
            };
            sis.registrarTraslados(nuevos);
            int[] despachados = sis.despacharMasRedituables(6);
            assertEquals(6, despachados.length);
            assertEquals(6, despachados[0]);
            assertEquals(5, despachados[1]);
        }
    }

    @Nested
    class DespacharRedituables {
        @Test
        void casoBase() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            int[] despachados = sis.despacharMasRedituables(2);
            assertEquals(2, despachados.length);
            assertEquals(3, despachados[0]);
            assertEquals(2, despachados[1]);
        }

        @Test
        void conEmpate() {
            BestEffort sis = new BestEffort(cantCiudades, new Traslado[] {
                new Traslado(1, 0, 1, 200, 10),
                new Traslado(2, 1, 2, 200, 20),
                new Traslado(3, 2, 3, 200, 30)
            });
            int[] despachados = sis.despacharMasRedituables(3);
            assertEquals(3, despachados.length);
            assertTrue(despachados[0] > despachados[1]);
            assertTrue(despachados[1] > despachados[2]);
        }
    }

    @Nested
    class DespacharAntiguos {
        @Test
        void casoBase() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            int[] despachados = sis.despacharMasAntiguos(2);
            assertEquals(2, despachados.length);
            assertEquals(1, despachados[0]);
            assertEquals(2, despachados[1]);
        }

        @Test
        void conEmpate() {
            BestEffort sis = new BestEffort(cantCiudades, new Traslado[] {
                new Traslado(1, 0, 1, 100, 10),
                new Traslado(2, 1, 2, 200, 10),
                new Traslado(3, 2, 3, 300, 10)
            });
            int[] despachados = sis.despacharMasAntiguos(3);
            assertEquals(3, despachados.length);
            assertEquals(1, despachados[0]);
            assertEquals(2, despachados[1]);
            assertEquals(3, despachados[2]);
        }
    }

    @Nested
    class CiudadMayorSuperavit {
        @Test
        void casoBase() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            sis.despacharMasRedituables(3);
            assertEquals(2, sis.ciudadConMayorSuperavit());
        }

        @Test
        void conEmpate() {
            BestEffort sis = new BestEffort(cantCiudades, new Traslado[] {
                new Traslado(1, 0, 1, 100, 10),
                new Traslado(2, 1, 0, 100, 20),
                new Traslado(3, 2, 3, 100, 30)
            });
            sis.despacharMasRedituables(3);
            assertEquals(0, sis.ciudadConMayorSuperavit());
        }
    }

    @Nested
    class CiudadesMayorGanancia {
        @Test
        void casoBase() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            sis.despacharMasRedituables(3);
            assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
        }

        @Test
        void conMultiplesGananciosas() {
            BestEffort sis = new BestEffort(cantCiudades, new Traslado[] {
                new Traslado(1, 0, 1, 200, 10),
                new Traslado(2, 1, 2, 200, 20),
                new Traslado(3, 2, 1, 200, 30)
            });
            sis.despacharMasRedituables(3);
            assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        }
    }

    @Nested
    class GananciaPromedio {
        @Test
        void casoBase() {
            BestEffort sis = new BestEffort(cantCiudades, listaTraslados);
            sis.despacharMasRedituables(3);
            assertEquals(200, sis.gananciaPromedioPorTraslado());
        }

        @Test
        void conDecimales() {
            BestEffort sis = new BestEffort(cantCiudades, new Traslado[] {
                new Traslado(1, 0, 1, 101, 10),
                new Traslado(2, 1, 2, 102, 20)
            });
            sis.despacharMasRedituables(2);
            assertEquals(101, sis.gananciaPromedioPorTraslado());
        }
    }
}
