package os;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_widget extends JFrame {
	public Widget w;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_widget frame = new Add_widget();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Add_widget() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(300,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel upper=new JPanel();		//上部
		contentPane.add(upper,BorderLayout.NORTH);
		upper.setLayout(new GridLayout(5,2,10,10));
		JLabel lab1=new JLabel("pid:");
		JLabel lab2=new JLabel("space:");
		JLabel lab3=new JLabel("time:");
		JLabel lab4=new JLabel("priority:");

		JTextField t1=new JTextField();
		JTextField t2=new JTextField();
		JTextField t3=new JTextField();
		JTextField t4=new JTextField();

		upper.add(lab1);
		upper.add(t1);
		upper.add(lab2);
		upper.add(t2);
		upper.add(lab3);
		upper.add(t3);
		upper.add(lab4);
		upper.add(t4);

		JButton b=new JButton("确定");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[]info= {0,0,0,0};
				info[0]=Integer.parseInt(t1.getText());
				info[1]=Integer.parseInt(t2.getText());
				info[2]=Integer.parseInt(t3.getText());
				info[3]=Integer.parseInt(t4.getText());
				
				Widget.a.pcb.add(new Process(info[0],info[1],info[2],info[3],1));
				Widget.a.prepare.add(new Process(info[0],info[1],info[2],info[3],1));
				JOptionPane.showMessageDialog(null,"添加成功。");
			}
		});
		contentPane.add(b,BorderLayout.SOUTH);
	}

}
