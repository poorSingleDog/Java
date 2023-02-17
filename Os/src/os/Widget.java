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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Widget extends JFrame  {
	public static Algorithm a;
	private JPanel contentPane;
	public JProgressBar bar;
	public JTable table;
	public JTable atable;
	public JButton[] btn;
	public JPanel model;				//内存各分区信息，空闲则隐藏按钮，占用则显示pid
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Widget frame = new Widget();
//					frame.setVisible(true);
//					int n=0;
//					for(int i=0;;i++) {
//						long totalMilliSeconds = System.currentTimeMillis();
//						if(totalMilliSeconds%100000==0)
//							System.out.println(n++);
//						continue;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	/**
	 * Create the frame.
	 */
	public Widget() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1500, 500);
		setTitle("main_widget");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		 Font f = new Font("宋体",Font.PLAIN,20);
	        Font f2 = new Font("宋体",Font.PLAIN,24);
	        
	        JPanel up=new JPanel();
			contentPane.add(up,BorderLayout.NORTH);			//上部
			JLabel time_piece=new JLabel("时间片：");
			time_piece.setFont(f2);
			bar=new JProgressBar();
			bar.setPreferredSize(new Dimension(300,35));
			up.add(time_piece);
			up.add(bar);
			bar.setValue(90);
			
			JPanel down=new JPanel();
			contentPane.add(down,BorderLayout.SOUTH);		//下部
			JButton insert=new JButton("添加");
			insert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Add_widget frame = new Add_widget();
					frame.setVisible(true);
				}
			});
//			JButton desert=new JButton("丢弃");
//			desert.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					Delete fram=new Delete();
//					fram.setVisible(true);
//				}
//			});
			insert.setFont(f);
//			desert.setFont(f);
			insert.setPreferredSize(new Dimension(200, 100));
//			desert.setPreferredSize(new Dimension(200, 100));
			down.add(insert);
//			down.add(desert);
			
			JPanel left=new JPanel();					//左部
			left.setLayout(new BorderLayout(0,0));
			contentPane.add(left,BorderLayout.WEST);
			 JScrollPane scrollPane=new JScrollPane();    //创建滚动面板
//			 JLabel clum=new JLabel("1111111111");
//			 left.add(clum,BorderLayout.NORTH);
//			 	left.add(scrollPane,BorderLayout.CENTER);    //将面板增加到边界布局中央
//		        JList<String> list=new JList<String>();
//		        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		        list.setPreferredSize(new Dimension(200,400));
//		        scrollPane.setViewportView(list);    //在滚动面板中显示列表
//		        String[] listData=new String[30];    //创建一个含有30个元素的数组
//		        for (int i=0;i<listData.length;i++)
//		        {
//		            listData[i]="这是列表框的第"+(i+1)+"个元素~";    //为数组中各个元素赋值
//		        }
//		        list.setListData(listData);    //为列表填充数据
		        String[] clum= {"pid","大小","剩余时间","优先级","状态"};
		        String[][] rowdata={
		                {"null", "null", "null", "null", "null"},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
		                {"", "", "", "", ""},
//		                {"John", "80", "80", "80", "80"}
		            
		        };
		        table = new JTable(rowdata, clum);
//		        table.getModel().setValueAt("111", 1, 0);
		        left.add(scrollPane,BorderLayout.CENTER);
		        scrollPane.setViewportView(table);
	
		        
		        JPanel mid=new JPanel();					//中部
//		        mid.setPreferredSize(new Dimension(200, 200));
		        mid.setLayout(new GridLayout(4,8,8,8));
		        contentPane.add(mid);
//		        Font f = new Font("宋体",Font.PLAIN,20); 
		        JLabel label1=new JLabel("后备队列：");
//		        label1.setFont(f);
		        JLabel label2=new JLabel("就绪队列：");
//		        label2.setFont(f);
		        JLabel label3=new JLabel("执行中：");
//		        label3.setFont(f);
		        JLabel label4=new JLabel("挂起队列：");
//		        label4.setFont(f);
		        btn=new JButton[28];
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
		        
		        btn[7].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[7].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[8].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[8].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[9].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[9].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[10].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[10].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[11].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[11].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[12].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[12].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[13].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[13].getText());
						a=Algorithm.suspend(a, k);
					}
				});
		        btn[21].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[21].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        btn[22].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[22].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        btn[23].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[23].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        btn[24].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[24].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        btn[25].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[25].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        btn[26].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[26].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        btn[27].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int k=Integer.parseInt(btn[27].getText());
						a=Algorithm.unsuspend(a, k);
					}
				});
		        
		        
		        JPanel right=new JPanel();					//右部
			    contentPane.add(right,BorderLayout.EAST);
			    right.setLayout(new BorderLayout(0,0));
			    JLabel lab=new JLabel("Memory");
//			    lab.setFont(f);
			    right.add(lab,BorderLayout.WEST);
			    JScrollPane sPane=new JScrollPane(); 
			    String[] clums= {"起址","长度","是否占用","pid"};
		        String[][] rowdatas={
		                {"null", "null", "null", "null"},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		                {"","","",""},
		       
		        };
		        atable = new JTable(rowdatas, clums);
		        right.add(sPane,BorderLayout.EAST);
		        sPane.setViewportView(atable);
//			    model=new JPanel();
//			    right.add(model,BorderLayout.EAST);
//			    JButton []area=new JButton[4];
//			    model.setLayout(new GridLayout(4,1,0,0));
//			    for(int n=0;n<3;n++) {
//		       	 area[n]=new JButton();
//		       	 model.add(area[n]);
//			    }
	}

	public void renew(Algorithm a) {			//根据最新的a对界面进行设置值
		String[][]s=new String[a.pcb.size()][5];		//pcb
		for(int i=0;i<a.pcb.size();i++) {
			s[i][0]=String.valueOf(a.pcb.get(i).get_pid());
			s[i][1]=String.valueOf(a.pcb.get(i).get_space());
			s[i][2]=String.valueOf(a.pcb.get(i).get_time());		
			s[i][3]=String.valueOf(a.pcb.get(i).get_priority());
			s[i][4]=String.valueOf(a.pcb.get(i).get_status());
		}
		for(int x=0;x<17;x++) {
			for(int y=0;y<5;y++)
				table.getModel().setValueAt("", x, y);
		}
		for(int x=0;x<a.pcb.size();x++) {
			for(int y=0;y<5;y++)
				table.getModel().setValueAt(s[x][y], x, y);
		}
		
		for(int i=0;i<28;i++)						//队列
			btn[i].setVisible(false);
		for(int i=0;i<a.prepare.size();i++) {
			btn[i].setText(String.valueOf(a.prepare.get(i).get_pid()));
			btn[i].setVisible(true);
		}
		for(int i=7;i<a.ready.size()+7;i++) {
			btn[i].setText(String.valueOf(a.ready.get(i-7).get_pid()));
			btn[i].setVisible(true);
		}
		for(int i=21;i<a.susque.size()+21;i++) {
			btn[i].setText(String.valueOf(a.susque.get(i-21).get_pid()));
			btn[i].setVisible(true);
		}
		if(a.excu.get_priority()!=-99) {
			btn[14].setText(String.valueOf(a.excu.get_pid()));
			btn[14].setVisible(true);}
			

			String[][]str=new String[a.area_table.size()][4];		//内存区域
			for(int i=0;i<a.area_table.size();i++) {
				str[i][0]=String.valueOf(a.area_table.get(i).get_start());
				str[i][1]=String.valueOf(a.area_table.get(i).get_length());
				str[i][2]=String.valueOf(a.area_table.get(i).get_status());		
				str[i][3]=String.valueOf(a.area_table.get(i).get_p_id());
			}
			for(int x=0;x<17;x++) {
				for(int y=0;y<4;y++)
					atable.getModel().setValueAt("", x, y);
			}
			for(int x=0;x<a.area_table.size();x++) {
				for(int y=0;y<4;y++)
					atable.getModel().setValueAt(str[x][y], x, y);
			}
			
		

	}
		
	public static void main(String[] args) {
		a=new Algorithm();
		Widget w = new Widget();
		w.setVisible(true);
		for(int i=0;;i++) {
//				Thread.sleep(1000);
				for(int j=0;j<100;j++) {
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					w.bar.setValue(j);
				}
			a=Algorithm.exover(a);
			a=Algorithm.combine(a);
			a=Algorithm.enter_memory(a);
			a=Algorithm.excute(a);

			w.renew(a);
		}
//		for(int i=0;i<a.area_table.size();i++) {
//			System.out.println(a.area_table.get(i).get_start()+"   "+a.area_table.get(i).get_length()+"   "+a.area_table.get(i).get_status());
//		}
//		System.out.println();
//		for(int i=0;i<a.pcb.size();i++) {
//			System.out.println(a.pcb.get(i).get_pid()+"   "+a.pcb.get(i).get_space()+"   "+a.pcb.get(i).get_time()+"   "+a.pcb.get(i).get_priority()+"   "+a.pcb.get(i).get_status());
//		}
//		System.out.println("prepare");
//		for(int i=0;i<a.prepare.size();i++) {
//			System.out.println(a.prepare.get(i).get_pid()+"   "+a.prepare.get(i).get_space()+"   "+a.prepare.get(i).get_time()+"   "+a.prepare.get(i).get_priority()+"   "+a.prepare.get(i).get_status());
//		}
//		System.out.println("ready");
//		for(int i=0;i<a.ready.size();i++) {
//			System.out.println(a.ready.get(i).get_pid()+"   "+a.ready.get(i).get_space()+"   "+a.ready.get(i).get_time()+"   "+a.ready.get(i).get_priority()+"   "+a.ready.get(i).get_status());
//		}
//		System.out.println("excute");
//			System.out.println(a.excu.get_pid()+"   "+a.excu.get_space()+"   "+a.excu.get_time()+"   "+a.excu.get_priority()+"   "+a.excu.get_status());
//		
//		System.out.println();
//		for(int i=0;i<a.finish.size();i++) {
//			System.out.println(a.finish.get(i).get_pid()+"   "+a.finish.get(i).get_space()+"   "+a.finish.get(i).get_time()+"   "+a.finish.get(i).get_priority()+"   "+a.finish.get(i).get_status());
//		}
//		System.out.println();
	}
// 


}