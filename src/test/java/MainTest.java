import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Disabled //to avoid wasting time during build, especially on automated test cases
    @Test
    @Timeout(value = 22)
    public void timeout() throws Exception{
        Main.main(null);
    }
}
