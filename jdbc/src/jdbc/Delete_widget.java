package jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Delete_widget extends JFrame {
	public Delete_widget() {
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create_Delete_widget();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void create_Delete_widget() {
		Delete_widget frame = new Delete_widget();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,150);
		frame.setTitle("ɾ��");
		frame.setBounds(100, 100, 450, 300);
		frame.contentPane = new JPanel();
		frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(frame.contentPane);
		JPanel btn = new JPanel();							//�ϲ�
		frame.contentPane.add(btn,BorderLayout.NORTH);
		JRadioButton rbtn_1=new JRadioButton("client");				//client��ť
		
		btn.add(rbtn_1);
        
        JRadioButton rbtn_2=new JRadioButton("bank_card");				//bank_card��ť
        btn.add(rbtn_2);
        
        JRadioButton rbtn_3=new JRadioButton("property");				//property��ť
        btn.add(rbtn_3);
        
        ButtonGroup group=new ButtonGroup();
        group.add(rbtn_1);
        group.add(rbtn_2);
        group.add(rbtn_3);
        
        JPanel under = new JPanel();						//�²�
        frame.contentPane.add(under,BorderLayout.SOUTH);
        JButton yes=new JButton("ȷ��");
       
        JButton clear=new JButton("���");
        
        JButton back=new JButton("����");					//�ص�������
        back.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
        		Gui.createAndShowGUI();
        		frame.dispose();
       	}
        });
        
        under.add(yes);
        under.add(clear);
        under.add(back);
		
        JPanel mid = new JPanel();						//�в�
        frame.contentPane.add(mid,BorderLayout.CENTER);
        mid.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        JLabel key=new JLabel("ѡ��id:");
        key.setFont(new java.awt.Font("Dialog", 1, 20));
        JTextField t=new JTextField();
		key.setPreferredSize(new Dimension(150,40));
		t.setPreferredSize(new Dimension(200,40));
		mid.add(key);
		mid.add(t);
		 
		rbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("�ͻ���ţ�");
				
			}
		});
		rbtn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("���п��ţ�");
				
			}
		});
		rbtn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("�ʲ���ţ�");
				
			}
		});
		clear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.setText("");
        		
        	}
        });
		
		yes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String str=new String();
        		
        			try {
        				if(key.getText()=="�ͻ���ţ�") {
    	        			
    	        			str=t.getText();
    	        			int i=Dml.c_delete(str);
						JOptionPane.showMessageDialog(null,"ɾ��"+i+"��client��¼��");}
        				else if(key.getText()=="���п��ţ�") {
        					str=t.getText();
    	        			int i=Dml.b_delete(str);
    	        			JOptionPane.showMessageDialog(null,"ɾ��"+i+"��bank_card��¼��");
        				}
        				else if(key.getText()=="�ʲ���ţ�") {
        					str=t.getText();
    	        			int i=Dml.p_delete(str);
    	        			JOptionPane.showMessageDialog(null,"ɾ��"+i+"��property��¼��");
        				}
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,"����");
					}
        		
        		
        	}
        });
		
		frame.setVisible(true);
	}

}
