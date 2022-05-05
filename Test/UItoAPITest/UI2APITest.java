package UItoAPITest;

import org.junit.Assert;
import org.junit.Test;
import UI.MillUI;


public class UI2APITest {

    @Test
    public void gutTest1(){
        MillUI x = new MillUI();
        String xKoord = "A";

        Assert.assertEquals(0, x.mergeStringX(xKoord));
    }
    @Test
    public void gutTest2() {
        MillUI x = new MillUI();
        String xKoord = "B";
        x.mergeStringX(xKoord);

        Assert.assertEquals(1, x.mergeStringX(xKoord));
    }
    @Test
    public void gutTest3(){
        MillUI x = new MillUI();
        String xKoord = "C";
        x.mergeStringX(xKoord);

        Assert.assertEquals(2, x.mergeStringX(xKoord));
    }
    @Test
    public void schlechtTest1(){
        MillUI x = new MillUI();
        String xKoord = "D";
        x.mergeStringX(xKoord);

    }

}
