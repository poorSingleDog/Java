package os;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Gui extends JFrame {
	public Gui() {
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void createGUI() {
		JFrame frm = new Gui();
		Algorithm a=new Algorithm();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setBounds(100, 100, 450, 300);
		frm.setSize(900, 500);
		frm.setTitle("main_widget");
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frm.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
        Font f = new Font("宋体",Font.PLAIN,20);
        Font f2 = new Font("宋体",Font.PLAIN,24);
		
		JPanel up=new JPanel();
		contentPane.add(up,BorderLayout.NORTH);			//上部
		JLabel time_piece=new JLabel("时间片：");
		time_piece.setFont(f2);
		JProgressBar bar=new JProgressBar();
		bar.setPreferredSize(new Dimension(300,35));
		up.add(time_piece);
		up.add(bar);
		bar.setValue(10);
		
		JPanel down=new JPanel();
		contentPane.add(down,BorderLayout.SOUTH);		//下部
		JButton insert=new JButton("添加");
		JButton desert=new JButton("丢弃");
		insert.setFont(f);
		desert.setFont(f);
		insert.setPreferredSize(new Dimension(200, 100));
		desert.setPreferredSize(new Dimension(200, 100));
		down.add(insert);
		down.add(desert);
		
		JPanel left=new JPanel();					//左部
		left.setLayout(new BorderLayout(0,0));
		contentPane.add(left,BorderLayout.WEST);
		 JScrollPane scrollPane=new JScrollPane();    //创建滚动面板
		 JLabel clum=new JLabel("1111111111");
		 left.add(clum,BorderLayout.NORTH);
		 	left.add(scrollPane,BorderLayout.CENTER);    //将面板增加到边界布局中央
	        JList<String> list=new JList<String>();
	        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        list.setPreferredSize(new Dimension(200,400));
	        scrollPane.setViewportView(list);    //在滚动面板中显示列表
	        String[] listData=new String[30];    //创建一个含有30个元素的数组
	        for (int i=0;i<listData.length;i++)
	        {
	            listData[i]="这是列表框的第"+(i+1)+"个元素~";    //为数组中各个元素赋值
	        }
	        list.setListData(listData);    //为列表填充数据
		
	        JPanel mid=new JPanel();					//中部
//	        mid.setPreferredSize(new Dimension(200, 200));
	        mid.setLayout(new GridLayout(4,8,8,8));
	        contentPane.add(mid);
//	        Font f = new Font("宋体",Font.PLAIN,20); 
	        JLabel label1=new JLabel("后备队列：");
//	        label1.setFont(f);
	        JLabel label2=new JLabel("就绪队列：");
//	        label2.setFont(f);
	        JLabel label3=new JLabel("执行中：");
//	        label3.setFont(f);
	        JLabel label4=new JLabel("挂起队列：");
//	        label4.setFont(f);
	        JButton []btn=new JButton[28];
	        for(int n=0;n<28;n++) {
	        	 btn[n]=new JButton();
	        }
	        mid.add(label1);
	        for(int i=0;i<7;i++)
	        	mid.add(btn[i]); 

	        mid.add(label2);
	        for(int i=7;i<14;i++)
	        	mid.add(btn[i]); 

	        mid.add(label3);
	        for(int i=14;i<21;i++)
	        	mid.add(btn[i]); 

	        mid.add(label4);
	        for(int i=21;i<28;i++)
	        	mid.add(btn[i]); 

	        
	        
	        
	    JPanel right=new JPanel();					//右部
	    contentPane.add(right,BorderLayout.EAST);
	    right.setLayout(new BorderLayout(0,0));
	    JLabel lab=new JLabel("memory");
	    lab.setFont(f);
	    right.add(lab,BorderLayout.WEST);
	    JPanel model=new JPanel();
	    right.add(model,BorderLayout.EAST);
	    JButton []area=new JButton[4];
	    model.setLayout(new GridLayout(4,1,0,0));
	    for(int n=0;n<3;n++) {
       	 area[n]=new JButton();
       	 model.add(area[n]);
       }
	    
		frm.setVisible(true);
	}

}
