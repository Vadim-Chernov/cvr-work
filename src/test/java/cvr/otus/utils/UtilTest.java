package cvr.otus.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("UtilTest простой тест")
class UtilTest {

    @Test
    void stringToIntArray() {
        int [] arr = new int[] {1,2,3};
        int[] ints = Util.StringToIntArray("1,2,3", ",");
        assertArrayEquals(arr,ints);
    }

    @Test
    void examineArrays() {
        double v = Util.examineArrays(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertEquals(5.0d,v,0.0001d);
        v = Util.examineArrays(new int[]{1, 2, 3}, new int[]{1, 2, 3,4});
        assertEquals(3.750d,v,0.0001d);

    }
}