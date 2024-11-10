package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.Test;

public class CiudadesTest {

    @Test
    void crear_ciudad_y_agregar_valores(){
        Ciudades c1 = new Ciudades(0);
        c1.addEarnings(1500);
        c1.addLosses(500);

        assertEquals(1500, c1.getEarnings());
        assertEquals(500, c1.getLosses());
        assertEquals(1000, c1.getSuperavit());

        c1.addEarnings(25);
        c1.addLosses(500);

        assertEquals(1525, c1.getEarnings());
        assertEquals(1000, c1.getLosses());
        assertEquals(525, c1.getSuperavit());
    }

    @Test
    void comparar_dos_ciudades(){
        Ciudades c1 = new Ciudades(0);
        Ciudades c2 = new Ciudades(1);

        c1.addEarnings(1500);
        c1.addLosses(500);
        c2.addEarnings(2000);
        c2.addLosses(300);

        assertEquals(1500, c1.getEarnings());
        assertEquals(500, c1.getLosses());
        assertEquals(1000, c1.getSuperavit());
        assertEquals(2000, c2.getEarnings());
        assertEquals(300, c2.getLosses());
        assertEquals(1700, c2.getSuperavit());
        assertTrue(c1.getEarnings() < c2.getEarnings());
        assertFalse(c1.getLosses() < c2.getLosses());
        assertTrue(c2.getSuperavit() > c1.getSuperavit());

        c1.addEarnings(3000);
        c2.addLosses(1500);

        assertEquals(4500, c1.getEarnings());
        assertEquals(1800, c2.getLosses());
        assertFalse(c1.getEarnings() < c2.getEarnings());
        assertTrue(c1.getLosses() < c2.getLosses());
        assertTrue(c1.getSuperavit() > c2.getSuperavit());
        
    }
}
