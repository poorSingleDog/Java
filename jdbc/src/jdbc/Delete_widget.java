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
		frame.setTitle("删除");
		frame.setBounds(100, 100, 450, 300);
		frame.contentPane = new JPanel();
		frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(frame.contentPane);
		JPanel btn = new JPanel();							//上部
		frame.contentPane.add(btn,BorderLayout.NORTH);
		JRadioButton rbtn_1=new JRadioButton("client");				//client按钮
		
		btn.add(rbtn_1);
        
        JRadioButton rbtn_2=new JRadioButton("bank_card");				//bank_card按钮
        btn.add(rbtn_2);
        
        JRadioButton rbtn_3=new JRadioButton("property");				//property按钮
        btn.add(rbtn_3);
        
        ButtonGroup group=new ButtonGroup();
        group.add(rbtn_1);
        group.add(rbtn_2);
        group.add(rbtn_3);
        
        JPanel under = new JPanel();						//下部
        frame.contentPane.add(under,BorderLayout.SOUTH);
        JButton yes=new JButton("确定");
       
        JButton clear=new JButton("清空");
        
        JButton back=new JButton("返回");					//回到主界面
        back.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
        		Gui.createAndShowGUI();
        		frame.dispose();
       	}
        });
        
        under.add(yes);
        under.add(clear);
        under.add(back);
		
        JPanel mid = new JPanel();						//中部
        frame.contentPane.add(mid,BorderLayout.CENTER);
        mid.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        JLabel key=new JLabel("选择id:");
        key.setFont(new java.awt.Font("Dialog", 1, 20));
        JTextField t=new JTextField();
		key.setPreferredSize(new Dimension(150,40));
		t.setPreferredSize(new Dimension(200,40));
		mid.add(key);
		mid.add(t);
		 
		rbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("客户编号：");
				
			}
		});
		rbtn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("银行卡号：");
				
			}
		});
		rbtn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("资产编号：");
				
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
        				if(key.getText()=="客户编号：") {
    	        			
    	        			str=t.getText();
    	        			int i=Dml.c_delete(str);
						JOptionPane.showMessageDialog(null,"删除"+i+"条client记录。");}
        				else if(key.getText()=="银行卡号：") {
        					str=t.getText();
    	        			int i=Dml.b_delete(str);
    	        			JOptionPane.showMessageDialog(null,"删除"+i+"条bank_card记录。");
        				}
        				else if(key.getText()=="资产编号：") {
        					str=t.getText();
    	        			int i=Dml.p_delete(str);
    	        			JOptionPane.showMessageDialog(null,"删除"+i+"条property记录。");
        				}
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,"错误。");
					}
        		
        		
        	}
        });
		
		frame.setVisible(true);
	}

}
