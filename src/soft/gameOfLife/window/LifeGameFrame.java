package soft.gameOfLife.window;

import soft.gameOfLife.model.CellMatrix;
import soft.gameOfLife.util.Utils;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class LifeGameFrame extends JFrame {
    /**
     * 常数.
     */
    private static final int WINDOWS_WIDTH = 800;
    /**
     * 常数.
     */
    private static final int WINDOWS_HEIGHT = 800;
    /**
     * 选择文件按钮.
     */
    private JButton openFileBtn = new JButton("选择文件");
    /**
     * 开始游戏按钮.
     */
    private JButton startGameBtn = new JButton("开始游戏");
    /**
     * 间隔.
     */
    //private JLabel durationPromtLabel = new JLabel("动画间隔设置");
    /**
     * 手动输入间隔field.
     */
    //private JTextField durationTextField = new JTextField();
    /**
     * 游戏是否开始的标志.
     */
    private boolean isStart = false;

    /**
     * 游戏结束的标志.
     */
    private boolean stop = false;
    /**
     * 模型.
     */
    private CellMatrix cellMatrix;
    /**
     * 控制按钮区域panel.
     */
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    /**
     * 游戏主界面.
     */
    private JPanel gridPanel = new JPanel();
    /**
     * 界面里的细胞槽.
     */
    private JLabel[][] textMatrix;


    /**
     * 动画默认间隔200ms.
     */
    private static final int DEFAULT_DURATION = 2000;

    /**
     * 间隔.
     */
    private int duration = DEFAULT_DURATION;
    /**
     * 初始化方法.
     */
    public LifeGameFrame() {
        setTitle("生命游戏");
        openFileBtn.addActionListener(new OpenFileActioner());
        startGameBtn.addActionListener(new StartGameActioner());

        buttonPanel.add(openFileBtn);
        buttonPanel.add(startGameBtn);
        buttonPanel.setBackground(Color.WHITE);

        getContentPane().add("North", buttonPanel);

        this.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private class OpenFileActioner implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            JFileChooser fcDlg = new JFileChooser(".");
            fcDlg.setDialogTitle("请选择初始文件");
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                isStart = false;
                stop = true;
                startGameBtn.setText("开始游戏");

                String filepath = fcDlg.getSelectedFile().getPath();
                cellMatrix = Utils.initMatrixFromFile(filepath);
                initGridLayout();
                showMatrix();
                gridPanel.updateUI();
            }
        }


    }
    /**
     * <p>按照现有的模型数据更新棋盘ui.</p>
     */
    public void showMatrix() {

        int[][] matrix = cellMatrix.getMatrix();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == 1) {
                    textMatrix[y][x].setBackground(Color.black);
                } else {
                    textMatrix[y][x].setBackground(Color.white);
                }
            }
        }
    }

    /**
     * 创建显示的gridlayout布局.
     */
    private void initGridLayout() {
        int rows = cellMatrix.getHeight();
        int cols = cellMatrix.getWidth();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        textMatrix = new JLabel[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                textMatrix[y][x] = new JLabel();
                textMatrix[y][x].setOpaque(true);
                gridPanel.add(textMatrix[y][x]);
            }
        }
        add("Center", gridPanel);
    }


    private class StartGameActioner implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (!isStart) {

                //获取时间
                //try {
                  //  duration =
                    //        Integer.parseInt(durationTextField.getText());
                //} catch (NumberFormatException e1) {
                 //   duration = DEFAULT_DURATION;
                //}

                new Thread(new GameControlTask()).start();
                isStart = true;
                stop = false;
                startGameBtn.setText("暂停游戏");
            } else {
                stop = true;
                isStart = false;
                startGameBtn.setText("开始游戏");
            }
        }
    }

    private class GameControlTask implements Runnable {

        @Override
        public void run() {
            int i = cellMatrix.getTransfromNum();
            while (!stop && i > 0) {
                cellMatrix.transform();
                showMatrix();
                i--;
                try {
                    TimeUnit.MILLISECONDS.sleep(cellMatrix.getDuration());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            stop = true;
            isStart = false;
            startGameBtn.setText("开始游戏");
        }
    }
}
