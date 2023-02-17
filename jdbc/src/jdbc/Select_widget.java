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
		frame.setTitle("查询");
		JPanel btn = new JPanel();							//上部
		frame.contentPane.add(btn,BorderLayout.NORTH);
		JLabel lab=new JLabel("查询语句：");
		JTextField sql=new JTextField();
		btn.add(lab);
		sql.setPreferredSize(new Dimension(300,30));
		btn.add(sql);
		JScrollPane scrollPane=new JScrollPane();    //创建滚动面板
	    frame.getContentPane().add(scrollPane,BorderLayout.CENTER);    //将面板增加到边界布局中央
	    JList<String> list=new JList<String>();
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    scrollPane.setViewportView(list);    //在滚动面板中显示列表
//	    String [] ary=new String[50];
//		ary=Dml.select("select * from bank_card");
//	    list.setListData(ary);
	    JPanel under = new JPanel();						//下部
        frame.contentPane.add(under,BorderLayout.SOUTH);
        JButton yes=new JButton("确定");
       
        JButton back=new JButton("返回");
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
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,"错误。");
					}
        		
        		
        	}
        });
		
		frame.setVisible(true);
	}

}
