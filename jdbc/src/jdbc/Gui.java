package jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
public class Gui {

	
	 public static void createAndShowGUI() {
	        // Create a new JFrame container.
	        JFrame jfrm = new JFrame("main widget");
	        // Give the frame an initial size.
	        jfrm.setSize(600, 600);
	        // Terminate the program when the user closes the application.
	        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	        JScrollPane scrollPane=new JScrollPane();    //创建滚动面板
	        jfrm.getContentPane().add(scrollPane,BorderLayout.CENTER);    //将面板增加到边界布局中央
	        JList<String> list=new JList<String>();
	        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        scrollPane.setViewportView(list);    //在滚动面板中显示列表
	        String[] listData=new String[30];    //创建一个含有30个元素的数组
	        for (int i=0;i<listData.length;i++)
	        {
	            listData[i]="这是列表框的第"+(i+1)+"个元素~";    //为数组中各个元素赋值
	        }
	        list.setListData(listData);    //为列表填充数据
	        // Add the label to the content pane.

	        JPanel btn_area=new JPanel();								//增删查改按钮区域
	        jfrm.getContentPane().add(btn_area,BorderLayout.SOUTH);
	        btn_area.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
	        
	        JPanel rbtn_area=new JPanel();								//单选按钮区域
	        jfrm.getContentPane().add(rbtn_area,BorderLayout.NORTH);
	        rbtn_area.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
	        rbtn_area.setPreferredSize(new Dimension(580, 50));//设置大小
	        
	        JRadioButton rbtn_1=new JRadioButton("client");				//client按钮
	        rbtn_area.add(rbtn_1);
	        
	        JRadioButton rbtn_2=new JRadioButton("bank_card");				//bank_card按钮
	        rbtn_area.add(rbtn_2);
	        
	        JRadioButton rbtn_3=new JRadioButton("finances_product");		//finances_product按钮
	        rbtn_area.add(rbtn_3);
	        
	        JRadioButton rbtn_4=new JRadioButton("fund");				//fund按钮
	        rbtn_area.add(rbtn_4);
	        
	        JRadioButton rbtn_5=new JRadioButton("insurance");				//insurance按钮
	        rbtn_area.add(rbtn_5);
	        
	        JRadioButton rbtn_6=new JRadioButton("property");				//property按钮
	        rbtn_area.add(rbtn_6);
	        
	        JLabel clum=new JLabel();									//列的属性值
	        rbtn_area.add(clum);
	        
	        ButtonGroup group=new ButtonGroup();
	        group.add(rbtn_1);
	        group.add(rbtn_2);
	        group.add(rbtn_3);
	        group.add(rbtn_4);
	        group.add(rbtn_5);
	        group.add(rbtn_6);
	        
	        rbtn_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[50];
						strr=Dml.c_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("编号     姓名                      邮箱                           卡号                                  电话                        密码");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        	}
	        });
	      

	        rbtn_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[50];
						strr=Dml.b_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("             卡号                             类别        用户编号");
					     clum.setPreferredSize(new Dimension(580,15));
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        rbtn_3.addActionListener(new ActionListener() {				//产品描述字数相差太大，对不齐
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[10];
						strr=Dml.fp_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("产品名称     产品编号                 产品描述                       购买金额                      理财年限 ");
					        clum.setPreferredSize(new Dimension(580,15));
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        rbtn_4.addActionListener(new ActionListener() {				
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[10];
						strr=Dml.f_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("基金名称             基金编号                 基金类型                       基金金额                      风险等级           管理者 ");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        rbtn_5.addActionListener(new ActionListener() {				
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[10];
						strr=Dml.i_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("保险名称          保险编号              保险金额               适用人群              保险年限           保障项目 ");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        rbtn_6.addActionListener(new ActionListener() {				
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[10];
						strr=Dml.p_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("资产编号  客户编号  商品编号  商品类型  商品状态  商品数量  商品收益        购买时间 ");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        JButton btnNewButton = new JButton("\u4FEE\u6539");			//修改按钮
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Update_widget.create_Update_widget();
	        		jfrm.dispose();
	        	}
	        });
	        btn_area.add(btnNewButton);
	        //btnNewButton.setPreferredSize(new Dimension(5,5));		//设置按钮大小
	        
	        JButton btnNewButton_1 = new JButton("\u5220\u9664");		//删除按钮
	        
	        btnNewButton_1.addActionListener(new ActionListener() {		
	        	public void actionPerformed(ActionEvent e) {
	        		Delete_widget.create_Delete_widget();
	        		jfrm.dispose();
	        	}
	        });
	        
	        btn_area.add(btnNewButton_1);
	        
	        JButton btnNewButton_2 = new JButton("\u67E5\u8BE2");		//查询按钮
	        btnNewButton_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						Select_widget.create_Select_widget();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        		jfrm.dispose();
	        	}
	        });
	        btn_area.add(btnNewButton_2);
	        
	        JButton btnNewButton_3 = new JButton("\u6DFB\u52A0");		//添加按钮
	        btnNewButton_3.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Add_widget.create_Add_widget();
	        		jfrm.dispose();
	        	}
	        });
	        btn_area.add(btnNewButton_3);
	        // Display the frame.
	        jfrm.setVisible(true);
	    }

	    public static void main(String args[]) {
	        // Create the frame on the event dispatching thread.
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
}
