package jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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

public class Update_widget extends JFrame {
	public Update_widget() {
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create_Update_widget();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void create_Update_widget() {
		Update_widget frame = new Update_widget();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("修改");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(900,300);
		frame.contentPane = new JPanel();
		frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(frame.contentPane);
		JPanel btn = new JPanel();	
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
		
        JPanel mid = new JPanel();						//中部
        frame.contentPane.add(mid,BorderLayout.CENTER);
		mid.setLayout(new GridLayout(2,8,13,13));
		JLabel clum1=new JLabel();
		JLabel clum2=new JLabel();
		JLabel clum3=new JLabel();
		JLabel clum4=new JLabel();
		JLabel clum5=new JLabel();
		JLabel clum6=new JLabel();
		JLabel clum7=new JLabel();
		JLabel clum8=new JLabel();
		JTextField t1=new JTextField();
		JTextField t2=new JTextField();
		JTextField t3=new JTextField();
		JTextField t4=new JTextField();
		JTextField t5=new JTextField();
		JTextField t6=new JTextField();
		JTextField t7=new JTextField();
		JTextField t8=new JTextField();
		mid.add(clum1);
		mid.add(clum2);
		mid.add(clum3);
		mid.add(clum4);
		mid.add(clum5);
		mid.add(clum6);
		mid.add(clum7);
		mid.add(clum8);
		mid.add(t1);
		mid.add(t2);
		mid.add(t3);
		mid.add(t4);
		mid.add(t5);
		mid.add(t6);
		mid.add(t7);
		mid.add(t8);
        
        JPanel under = new JPanel();						//下部
        frame.contentPane.add(under,BorderLayout.SOUTH);
        JButton get=new JButton("GET");
        
        JButton yes=new JButton("确定修改");
       
        JButton clear=new JButton("清空");
        
        JButton back=new JButton("返回");					//回到主界面
        back.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
        		Gui.createAndShowGUI();
        		frame.dispose();
       	}
        });
        under.add(get);
        under.add(yes);
        under.add(clear);
        under.add(back);
		
        rbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clum1.setText("客户编号不可改");
				clum2.setText("客户名称");
				clum3.setText("客户邮箱");
				clum4.setText("客户身份证");
				clum5.setText("客户电话");
				clum6.setText("客户密码");
				clum7.setText("");
				clum8.setText("");
			}
		});
		rbtn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clum1.setText("银行卡号不可改");
				clum2.setText("银行卡类型");
				clum3.setText("所属客户编号");
				clum4.setText("");
				clum5.setText("");
				clum6.setText("");
				clum7.setText("");
				clum8.setText("");
			}
		});
		
		rbtn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clum1.setText("资产编号不可改");
				clum2.setText("客户编号");
				clum3.setText("商品编号");
				clum4.setText("商品类型");
				clum5.setText("商品状态");
				clum6.setText("商品数量");
				clum7.setText("商品收益");
				clum8.setText("购买时间");
			}
		});
        
		 get.addActionListener(new ActionListener() {					//将对应id的数据给出到text框
				public void actionPerformed(ActionEvent e) {
					try {
						if(clum1.getText()=="客户编号不可改") {
							String [] s=Dml.c_get(t1.getText());
							t1.setText(s[0]);
							t2.setText(s[1]);
							t3.setText(s[2]);
							t4.setText(s[3]);
							t5.setText(s[4]);
							t6.setText(s[5]);
						}
						else if(clum1.getText()=="银行卡号不可改") {
							String [] s2=Dml.b_get(t1.getText());
							t1.setText(s2[0]);
							t2.setText(s2[1]);
							t3.setText(s2[2]);
						}
						else if(clum1.getText()=="资产编号不可改") {
							String [] s3=Dml.p_get(t1.getText());
							t1.setText(s3[0]);
							t2.setText(s3[1]);
							t3.setText(s3[2]);
							t4.setText(s3[3]);
							t5.setText(s3[4]);
							t6.setText(s3[5]);
							t7.setText(s3[6]);
							t8.setText(s3[7]);
						}
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			});
		 
		 
		 clear.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		t1.setText("");
	        		t2.setText("");
	        		t3.setText("");
	        		t4.setText("");
	        		t5.setText("");
	        		t6.setText("");
	        		t7.setText("");
	        		t8.setText("");
	        	}
	        });
		 yes.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String[] str=new String[8];
	        		
	        			try {
	        				if(clum1.getText()=="客户编号不可改") {
	    	        			
	    	        			str[0]=t1.getText();
	    	        			str[1]=t2.getText();
	    	        			str[2]=t3.getText();
	    	        			str[3]=t4.getText();
	    	        			str[4]=t5.getText();
	    	        			str[5]=t6.getText();
							Dml.c_update(str);
							JOptionPane.showMessageDialog(null,"修改一条client记录。");}
	        				else if(clum1.getText()=="银行卡号不可改") {
	        					str[0]=t1.getText();
	    	        			str[1]=t2.getText();
	    	        			str[2]=t3.getText();
	    	        			Dml.b_update(str);
	    	        			JOptionPane.showMessageDialog(null,"修改一条bank_card记录。");
	        				}
	        				else if(clum1.getText()=="资产编号不可改") {
	        					str[0]=t1.getText();
	    	        			str[1]=t2.getText();
	    	        			str[2]=t3.getText();
	    	        			str[3]=t4.getText();
	    	        			str[4]=t5.getText();
	    	        			str[5]=t6.getText();
	    	        			str[6]=t7.getText();
	    	        			str[7]=t8.getText();
	    	        			Dml.p_update(str);
	    	        			JOptionPane.showMessageDialog(null,"修改一条property记录。");
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
