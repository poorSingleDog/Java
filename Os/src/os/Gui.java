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
        Font f = new Font("����",Font.PLAIN,20);
        Font f2 = new Font("����",Font.PLAIN,24);
		
		JPanel up=new JPanel();
		contentPane.add(up,BorderLayout.NORTH);			//�ϲ�
		JLabel time_piece=new JLabel("ʱ��Ƭ��");
		time_piece.setFont(f2);
		JProgressBar bar=new JProgressBar();
		bar.setPreferredSize(new Dimension(300,35));
		up.add(time_piece);
		up.add(bar);
		bar.setValue(10);
		
		JPanel down=new JPanel();
		contentPane.add(down,BorderLayout.SOUTH);		//�²�
		JButton insert=new JButton("���");
		JButton desert=new JButton("����");
		insert.setFont(f);
		desert.setFont(f);
		insert.setPreferredSize(new Dimension(200, 100));
		desert.setPreferredSize(new Dimension(200, 100));
		down.add(insert);
		down.add(desert);
		
		JPanel left=new JPanel();					//��
		left.setLayout(new BorderLayout(0,0));
		contentPane.add(left,BorderLayout.WEST);
		 JScrollPane scrollPane=new JScrollPane();    //�����������
		 JLabel clum=new JLabel("1111111111");
		 left.add(clum,BorderLayout.NORTH);
		 	left.add(scrollPane,BorderLayout.CENTER);    //��������ӵ��߽粼������
	        JList<String> list=new JList<String>();
	        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        list.setPreferredSize(new Dimension(200,400));
	        scrollPane.setViewportView(list);    //�ڹ����������ʾ�б�
	        String[] listData=new String[30];    //����һ������30��Ԫ�ص�����
	        for (int i=0;i<listData.length;i++)
	        {
	            listData[i]="�����б��ĵ�"+(i+1)+"��Ԫ��~";    //Ϊ�����и���Ԫ�ظ�ֵ
	        }
	        list.setListData(listData);    //Ϊ�б��������
		
	        JPanel mid=new JPanel();					//�в�
//	        mid.setPreferredSize(new Dimension(200, 200));
	        mid.setLayout(new GridLayout(4,8,8,8));
	        contentPane.add(mid);
//	        Font f = new Font("����",Font.PLAIN,20); 
	        JLabel label1=new JLabel("�󱸶��У�");
//	        label1.setFont(f);
	        JLabel label2=new JLabel("�������У�");
//	        label2.setFont(f);
	        JLabel label3=new JLabel("ִ���У�");
//	        label3.setFont(f);
	        JLabel label4=new JLabel("������У�");
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

	        
	        
	        
	    JPanel right=new JPanel();					//�Ҳ�
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
