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


	        JScrollPane scrollPane=new JScrollPane();    //�����������
	        jfrm.getContentPane().add(scrollPane,BorderLayout.CENTER);    //��������ӵ��߽粼������
	        JList<String> list=new JList<String>();
	        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        scrollPane.setViewportView(list);    //�ڹ����������ʾ�б�
	        String[] listData=new String[30];    //����һ������30��Ԫ�ص�����
	        for (int i=0;i<listData.length;i++)
	        {
	            listData[i]="�����б��ĵ�"+(i+1)+"��Ԫ��~";    //Ϊ�����и���Ԫ�ظ�ֵ
	        }
	        list.setListData(listData);    //Ϊ�б��������
	        // Add the label to the content pane.

	        JPanel btn_area=new JPanel();								//��ɾ��İ�ť����
	        jfrm.getContentPane().add(btn_area,BorderLayout.SOUTH);
	        btn_area.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
	        
	        JPanel rbtn_area=new JPanel();								//��ѡ��ť����
	        jfrm.getContentPane().add(rbtn_area,BorderLayout.NORTH);
	        rbtn_area.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
	        rbtn_area.setPreferredSize(new Dimension(580, 50));//���ô�С
	        
	        JRadioButton rbtn_1=new JRadioButton("client");				//client��ť
	        rbtn_area.add(rbtn_1);
	        
	        JRadioButton rbtn_2=new JRadioButton("bank_card");				//bank_card��ť
	        rbtn_area.add(rbtn_2);
	        
	        JRadioButton rbtn_3=new JRadioButton("finances_product");		//finances_product��ť
	        rbtn_area.add(rbtn_3);
	        
	        JRadioButton rbtn_4=new JRadioButton("fund");				//fund��ť
	        rbtn_area.add(rbtn_4);
	        
	        JRadioButton rbtn_5=new JRadioButton("insurance");				//insurance��ť
	        rbtn_area.add(rbtn_5);
	        
	        JRadioButton rbtn_6=new JRadioButton("property");				//property��ť
	        rbtn_area.add(rbtn_6);
	        
	        JLabel clum=new JLabel();									//�е�����ֵ
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
						 clum.setText("���     ����                      ����                           ����                                  �绰                        ����");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
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
						 clum.setText("             ����                             ���        �û����");
					     clum.setPreferredSize(new Dimension(580,15));
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        rbtn_3.addActionListener(new ActionListener() {				//��Ʒ�����������̫�󣬶Բ���
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						String []strr=new String[10];
						strr=Dml.fp_select();
						list.setListData(strr);
						//System.out.println(strr);
						 clum.setText("��Ʒ����     ��Ʒ���                 ��Ʒ����                       ������                      ������� ");
					        clum.setPreferredSize(new Dimension(580,15));
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
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
						 clum.setText("��������             ������                 ��������                       ������                      ���յȼ�           ������ ");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
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
						 clum.setText("��������          ���ձ��              ���ս��               ������Ⱥ              ��������           ������Ŀ ");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
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
						 clum.setText("�ʲ����  �ͻ����  ��Ʒ���  ��Ʒ����  ��Ʒ״̬  ��Ʒ����  ��Ʒ����        ����ʱ�� ");
					        clum.setPreferredSize(new Dimension(580,15));
					        
						
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
	        	}
	        });
	        
	        JButton btnNewButton = new JButton("\u4FEE\u6539");			//�޸İ�ť
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Update_widget.create_Update_widget();
	        		jfrm.dispose();
	        	}
	        });
	        btn_area.add(btnNewButton);
	        //btnNewButton.setPreferredSize(new Dimension(5,5));		//���ð�ť��С
	        
	        JButton btnNewButton_1 = new JButton("\u5220\u9664");		//ɾ����ť
	        
	        btnNewButton_1.addActionListener(new ActionListener() {		
	        	public void actionPerformed(ActionEvent e) {
	        		Delete_widget.create_Delete_widget();
	        		jfrm.dispose();
	        	}
	        });
	        
	        btn_area.add(btnNewButton_1);
	        
	        JButton btnNewButton_2 = new JButton("\u67E5\u8BE2");		//��ѯ��ť
	        btnNewButton_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						Select_widget.create_Select_widget();
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
	        		jfrm.dispose();
	        	}
	        });
	        btn_area.add(btnNewButton_2);
	        
	        JButton btnNewButton_3 = new JButton("\u6DFB\u52A0");		//��Ӱ�ť
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
