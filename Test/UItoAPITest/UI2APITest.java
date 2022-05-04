package UItoAPITest;

import org.junit.Assert;
import org.junit.Test;
import UI.MillUI;


public class UI2APITest {

    @Test
    public void gutTest1(){
        MillUI x = new MillUI();
        String xKoord = "A";

        Assert.assertEquals(0, x.merchString(xKoord));
    }
    @Test
    public void gutTest2() {
        MillUI x = new MillUI();
        String xKoord = "B";
        x.merchString(xKoord);

        Assert.assertEquals(1, x.merchString(xKoord));
    }
    @Test
    public void gutTest3(){
        MillUI x = new MillUI();
        String xKoord = "C";
        x.merchString(xKoord);

        Assert.assertEquals(2, x.merchString(xKoord));
    }
    @Test
    public void schlechtTest1(){
        MillUI x = new MillUI();
        String xKoord = "D";
        x.merchString(xKoord);

    }

}
