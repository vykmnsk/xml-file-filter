package vykmnsk;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UtilsTest {


    @Test
    public void canDetectAnagrams(){
        assertTrue(Utils.areAnagrams("binary", "brainy"));
        assertTrue(Utils.areAnagrams("Binary", "brainY"));
        assertTrue(Utils.areAnagrams("FRIED", "FIRED"));
    }

    @Test
    public void canDetectNonAnagrams(){
        assertFalse(Utils.areAnagrams("one", "two"));
        assertFalse(Utils.areAnagrams("GAP", "GOP"));
        assertFalse(Utils.areAnagrams("", ""));
        assertFalse(Utils.areAnagrams(null, null));
    }

}
