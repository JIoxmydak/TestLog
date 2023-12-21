import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void nameNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,0.0));
    }

    @Test
    void nameNullExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null,0.0));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "     ", "\t\t", "\n\n\n\n"})
    void nameBlankException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "     ", "\t\t", "\n\n\n\n"})
    void nameBlankExceptionMessage(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    void speedNegativeException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("null",-1.0));
    }

    @Test
    void speedNegativeExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("null",-1.0));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    void distanceNegativeException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("null", 1.0, -1.0));
    }

    @Test
    void distanceNegativeExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("null", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    void getNameTest() {
        Horse horse = new Horse("Декабрь", 1.0);
        assertEquals("Декабрь", horse.getName());
    }

    @Test
    void getSpeedTest() {
        Horse horse = new Horse("Январь", 1.0);
        assertEquals(1.0, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse horse = new Horse("Февраль", 1.0, 1.0);
        Horse horse2 = new Horse("Февраль", 1.0);
        assertEquals(1.0, horse.getDistance());
        assertEquals(0, horse2.getDistance());
    }

    @Test
    void moveTest() {
        try (MockedStatic<Horse> staticMock = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Март",1.0, 1.0);
            staticMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            assertEquals(1.5, horse.getDistance());
            staticMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}