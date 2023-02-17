package os;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Delete extends JFrame {
	public Widget w;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
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
	public Delete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel upper=new JPanel();		//上部
		contentPane.add(upper,BorderLayout.NORTH);
		upper.setLayout(new GridLayout(1,2,10,10));
		JLabel lab1=new JLabel("pid:");
		JTextField t1=new JTextField();
		upper.add(lab1);
		upper.add(t1);
		JButton b=new JButton("确定");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=0;
				id=Integer.parseInt(t1.getText());
				for(int i=0;i<Widget.a.pcb.size();i++) {
					if(Widget.a.pcb.get(i).get_pid()==id)
						Widget.a.pcb.remove(i);
				}
				for(int i=0;i<Widget.a.prepare.size();i++) {
					if(Widget.a.prepare.get(i).get_pid()==id)
						Widget.a.prepare.remove(i);
				}
				for(int i=0;i<Widget.a.ready.size();i++) {
					if(Widget.a.ready.get(i).get_pid()==id)
						Widget.a.ready.remove(i);
				}
				
				
			}
		});
		contentPane.add(b,BorderLayout.SOUTH);
	}

}
