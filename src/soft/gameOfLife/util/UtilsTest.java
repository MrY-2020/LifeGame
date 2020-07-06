package soft.gameOfLife.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import soft.gameOfLife.model.CellMatrix;

public class UtilsTest {

    CellMatrix cellMatrix = null;
    String path = "C:\\Users\\LXY\\Documents\\LifeGame\\test.txt";

    @Before
    public void setup() {
        cellMatrix = Utils.initMatrixFromFile(path);
    }

    @Test
    public void initMatrixFromFile() throws Exception {
        Assert.assertEquals(3, cellMatrix.getHeight());
        Assert.assertEquals(3, cellMatrix.getWidth());
        Assert.assertEquals(60, cellMatrix.getTransfromNum());
        Assert.assertEquals(200, cellMatrix.getDuration());
        int[][] expected = new int[][]{
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        Assert.assertArrayEquals(expected, cellMatrix.getMatrix());
        //空路径返回null
        Assert.assertNull(Utils.initMatrixFromFile(""));
    }
}