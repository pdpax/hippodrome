import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void nullListException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null), "List is not null. Ho ho ho");
        assertEquals("Horses cannot be null.", e.getMessage());
    }
    @Test
    public void emptyListException(){
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses), "List is not empty. Ho ho ho");
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void checkGetHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horsey " + i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void checkMove(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses){
            verify(horse).move();
        }
    }

    @Test
    public void checkWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            horses.add(new Horse("Horsey " + i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horses.get(4),hippodrome.getWinner());
    }

}
