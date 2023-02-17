package os;


import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.JSeparator;
 
@SuppressWarnings("serial")
public class Demo extends JWindow implements Runnable {
 
	// 定义加载窗口大小
	public static final int LOAD_WIDTH = 455;
	public static final int LOAD_HEIGHT = 295;
	// 获取屏幕窗口大小
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	// 定义进度条组件
	public JProgressBar progressbar;
	// 定义标签组件
	public JLabel label;
 
	// 构造函数
	public Demo() {
 
		// 创建标签,并在标签上放置一张图片
		label = new JLabel(new ImageIcon("images/background.jpg"));
		label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);
		// 创建进度条
		progressbar = new JProgressBar();
		// 显示当前进度值信息
		progressbar.setStringPainted(true);
		// 设置进度条边框不显示
		progressbar.setBorderPainted(false);
		// 设置进度条的前景色
		progressbar.setForeground(new Color(0, 210, 40));
		// 设置进度条的背景色
		progressbar.setBackground(new Color(188, 190, 194));
		progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);
		// 添加组件
		getContentPane().add(label);
		getContentPane().add(progressbar);
		// 设置布局为空
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 121, 445, 23);
		getContentPane().add(separator);
		// 设置窗口初始位置
		this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);
		// 设置窗口大小
		this.setSize(LOAD_WIDTH, LOAD_HEIGHT);
		// 设置窗口显示
		this.setVisible(true);
 
	}
 
	public static void main(String[] args) {
		Demo t = new Demo();
		new Thread(t).start();
	}
 
	@Override
	public void run() {
 
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressbar.setValue(i);
		}
		JOptionPane.showMessageDialog(this, "加载完成");
		this.dispose();
 
	}
}
