package jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class Select_widget extends JFrame {
	public Select_widget() {
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create_Select_widget();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public static void create_Select_widget() throws SQLException {
		Select_widget frame = new Select_widget();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(700,600);
		frame.contentPane = new JPanel();
		frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(frame.contentPane);
		frame.setTitle("��ѯ");
		JPanel btn = new JPanel();							//�ϲ�
		frame.contentPane.add(btn,BorderLayout.NORTH);
		JLabel lab=new JLabel("��ѯ��䣺");
		JTextField sql=new JTextField();
		btn.add(lab);
		sql.setPreferredSize(new Dimension(300,30));
		btn.add(sql);
		JScrollPane scrollPane=new JScrollPane();    //�����������
	    frame.getContentPane().add(scrollPane,BorderLayout.CENTER);    //��������ӵ��߽粼������
	    JList<String> list=new JList<String>();
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    scrollPane.setViewportView(list);    //�ڹ����������ʾ�б�
//	    String [] ary=new String[50];
//		ary=Dml.select("select * from bank_card");
//	    list.setListData(ary);
	    JPanel under = new JPanel();						//�²�
        frame.contentPane.add(under,BorderLayout.SOUTH);
        JButton yes=new JButton("ȷ��");
       
        JButton back=new JButton("����");
        back.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
            		Gui.createAndShowGUI();
            		frame.dispose();
           	}
            });
        under.add(yes);
        under.add(back);
        yes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        			try {
        				String [] ary=new String[50];
        				ary=Dml.select(sql.getText());
        				list.setListData(ary);
						
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
