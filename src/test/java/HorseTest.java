import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {
    @Test
    public void nullNameException() {
        System.out.println("Start");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1), "Name is not null. Ho ho ho");
        System.out.println("End");
        assertEquals("Name cannot be null. He he", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1), "Name is not blank.");
        assertEquals("Name cannot be blank. Hi hi", e.getMessage());
    }

    @Test
    public void negativeSpeedException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Horsey", -1, 1), "Speed is not negative.");
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistanceException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Horsey", 1, -1), "Speed is not negative.");
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void checkName() {
        Horse horse = new Horse("Horsey", 1, 1);
        assertEquals("Horsey", horse.getName());
    }

    @Test
    public void checkSpeed() {
        Horse horse = new Horse("Horsey", 10, 1);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    public void checkDistance() {
        Horse horse = new Horse("Horsey", 1, 10);
        assertEquals(10, horse.getDistance());
    }

    @Test
    public void checkZeroDistance() {
        Horse horse = new Horse("Horsey", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void checkGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Horsey", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.1, 0.5, 0.9, 1, 10})
    public void checkGetRandomDoubleParametrized(double number) {
        double speed = 10;
        double distance = 20;
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Horsey", speed, distance);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(number);
            horse.move();
            assertEquals((speed * number) + distance, horse.getDistance());
        }
    }
}
