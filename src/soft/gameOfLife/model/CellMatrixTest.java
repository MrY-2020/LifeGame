package soft.gameOfLife.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import soft.gameOfLife.util.Utils;

public class CellMatrixTest {

    CellMatrix cellMatrix = null;
    String path = "C:\\Users\\LXY\\Documents\\LifeGame\\test.txt";

    @Before
    public void setup() {
        cellMatrix = Utils.initMatrixFromFile(path);
    }

    @Test
    public void transform() throws Exception {

        //等于3，则下一状态总是活
        //等于2，则与上一状态一样
        int[][] expected = new int[][]{
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        Assert.assertArrayEquals(expected, cellMatrix.getMatrix());
        cellMatrix.transform();
        expected = new int[][]{
                {0, 0, 1},
                {1, 0, 1},
                {0, 1, 1}
        };
        Assert.assertArrayEquals(expected, cellMatrix.getMatrix());
        cellMatrix.transform();
        expected = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {0, 1, 1}
        };
        Assert.assertArrayEquals(expected, cellMatrix.getMatrix());
    }


    @Test
    public void findLifedNum() throws Exception {
        Assert.assertEquals(1, cellMatrix.findLivedNum(0, 0));
        Assert.assertEquals(4, cellMatrix.findLivedNum(0, 1));
        Assert.assertEquals(2, cellMatrix.findLivedNum(0, 2));
        Assert.assertEquals(3, cellMatrix.findLivedNum(1, 0));
        Assert.assertEquals(4, cellMatrix.findLivedNum(1, 1));
        Assert.assertEquals(3, cellMatrix.findLivedNum(1, 2));
        Assert.assertEquals(2, cellMatrix.findLivedNum(2, 0));
        Assert.assertEquals(2, cellMatrix.findLivedNum(2, 1));
        Assert.assertEquals(3, cellMatrix.findLivedNum(2, 2));
    }
    @Test
    public void getHeight() {
    }
    @Test
    public void getWidth() {
    }
    @Test
    public void getMatrix() {
    }
    @Test
    public void getTransfromNum() {
    }
    @Test
    public void getDuration() {
    }

}