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
 
	// ������ش��ڴ�С
	public static final int LOAD_WIDTH = 455;
	public static final int LOAD_HEIGHT = 295;
	// ��ȡ��Ļ���ڴ�С
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	// ������������
	public JProgressBar progressbar;
	// �����ǩ���
	public JLabel label;
 
	// ���캯��
	public Demo() {
 
		// ������ǩ,���ڱ�ǩ�Ϸ���һ��ͼƬ
		label = new JLabel(new ImageIcon("images/background.jpg"));
		label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);
		// ����������
		progressbar = new JProgressBar();
		// ��ʾ��ǰ����ֵ��Ϣ
		progressbar.setStringPainted(true);
		// ���ý������߿���ʾ
		progressbar.setBorderPainted(false);
		// ���ý�������ǰ��ɫ
		progressbar.setForeground(new Color(0, 210, 40));
		// ���ý������ı���ɫ
		progressbar.setBackground(new Color(188, 190, 194));
		progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);
		// ������
		getContentPane().add(label);
		getContentPane().add(progressbar);
		// ���ò���Ϊ��
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 121, 445, 23);
		getContentPane().add(separator);
		// ���ô��ڳ�ʼλ��
		this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);
		// ���ô��ڴ�С
		this.setSize(LOAD_WIDTH, LOAD_HEIGHT);
		// ���ô�����ʾ
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
		JOptionPane.showMessageDialog(this, "�������");
		this.dispose();
 
	}
}
