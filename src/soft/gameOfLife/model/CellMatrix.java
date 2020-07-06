package soft.gameOfLife.model;

import java.util.Arrays;

public class CellMatrix {
    /**
     * 常数.
     */
    private static final int THREE = 3;
    /**
     * 常数.
     */
    private static final int TWO = 2;
    /**
     * 矩阵高度.
     */
    private int height;

    /**
     * 矩阵宽度.
     */
    private int width;

    /**
     * 动画速度，每两个状态之间的毫秒数.
     */
    private int duration;

    /**
     * 总的变化次数.
     */
    private int transfromNum = 0;

    /**
     * 矩阵状态，1表示活，0表示死.
     */
    private int[][] matrix;
    /**
     * 模型初始化方法.
     * @param height 高度.
     * @param width .
     * @param duration 间隔.
     * @param transfromNum num.
     * @param matrix 格子.
     */
    public CellMatrix(final int height, final int width,
            final int duration, final int transfromNum, final int[][] matrix) {
        this.height = height;
        this.width = width;
        this.duration = duration;
        this.transfromNum = transfromNum;
        this.matrix = matrix;
    }

    /**
     * 上一个状态到下一个状态的转移.
     * 根据规则可以总结得出两条规则:
     * 1. 对于周围活着的细胞为3的情况,下一个状态该细胞总是为活
     * 2. 对于周围活着的细胞为2的情况,下一个状态与上一状态相同
     */
    public void transform() {
        int[][] nextMatrix = new int[height][width];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                nextMatrix[y][x] = 0;
                int nearNum = findLivedNum(y, x);
                //等于3，则下一状态总是活
                // 等于2，则与上一状态一样
                if (nearNum == THREE) {
                    nextMatrix[y][x] = 1;
                } else if (nearNum == 2) {
                    nextMatrix[y][x] = matrix[y][x];
                }
            }
        }
        matrix = nextMatrix;
    }



    /**
     * 统计每个细胞周围活着的个数.
     * @param  x 横坐标
     * @param  y 纵坐标
     * @return  活着的细胞
     */
    public int findLivedNum(final int y, final int x) {
        int num = 0;
        //左边
        if (x != 0) {
            num += matrix[y][x - 1];
        }
        //左上角
        if (x != 0 && y != 0) {
            num += matrix[y - 1][x - 1];
        }
        //上边
        if (y != 0) {
            num += matrix[y - 1][x];
        }
        //右上
        if (x != width-1 && y != 0) {
            num += matrix[y - 1][x + 1];
        }
        //右边
        if (x != width - 1) {
            num += matrix[y][x + 1];
        }
        //右下
        if (x != width - 1 && y != height - 1) {
            num += matrix[y + 1][x + 1];
        }
        //下边
        if (y != height - 1) {
            num += matrix[y + 1][x];
        }
        //左下
        if (x != 0 && y != height - 1) {
            num += matrix[y + 1][x - 1];
        }
        return num;
    }

    /**
     * 游戏是否开始的标志.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(Arrays.toString(matrix[i]) + "\n");
        }
        return sb.toString();
    }
    /**
     * 游戏是否开始的标志.
     * @return  height.
     */
    public int getHeight() {
        return height;
    }
    /**
     * 游戏是否开始的标志.
     * @return  width.
     */
    public int getWidth() {
        return width;
    }
    /**
     * 游戏是否开始的标志.
     * @return matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }
    /**
     * 游戏是否开始的标志.
     * @return num
     */
    public int getTransfromNum() {
        return transfromNum;
    }
    /**
     * 游戏是否开始的标志.
     * @return 间隔
     */
    public int getDuration() {
        return duration;
    }
}
