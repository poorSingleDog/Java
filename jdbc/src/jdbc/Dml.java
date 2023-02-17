package jdbc;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Dml {
private static final String[][] String = null;
public static void main(String [] args) throws SQLException, ClassNotFoundException {
//	String [] str=new String[6];
//	for(int i =0;i<6;i++)
//		str[i]="50";
//	c_insert(str);
	//c_update();
	//delete;
	//b_select();
	//System.out.println(insert("client"));
	//System.out.print(c_get("1"));
//	String a="123";
//	a+="4";
//	System.out.print(a);
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true","root","1207417313jj");
	con.setAutoCommit(false);
	String sql="insert into tt values(?)";
	PreparedStatement ps=con.prepareStatement(sql);
	long start=System.currentTimeMillis();
	for(int i=0;i<100000;i++){
		ps.setObject(1,i);
		ps.addBatch();
		if(i%3000==0&&i!=0){
			ps.executeBatch();
			ps.clearBatch();
		}
	}
	ps.executeBatch();
	ps.clearBatch();
	con.commit();
	long end=System.currentTimeMillis();
	System.out.println("time:"+(end-start));

    
}

public static String[] select(String sql) throws SQLException{			//查询
	String url = "jdbc:mysql://localhost:3306/financial_data_model";
	//1) 使用用户名、密码、URL 得到连接对象
	Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
	//com.mysql.jdbc.JDBC4Connection@68de145
	System.out.println(connection);
	Statement statement = connection.createStatement();
    //3) 执行 SQL 语句得到结果集 ResultSet 对象
    ResultSet rs = statement.executeQuery(sql);
    //4) 循环遍历取出每一条记录
    int count = rs.getMetaData().getColumnCount();
    String [] array=new String[50];
    for(int k=0;k<50;k++)
    	array[k]="";
    int t=0;
    while(rs.next()) {
    	for(int i=1;i<=count;i++) {
    		array[t]+=rs.getString(i);
    		//System.out.println(rs.getString(i));
    		if(i<count)
    			 array[t]+="     ";
    		
    	}
    	t++;
    }
  
    rs.close();
    statement.close();
    connection.close();
    //System.out.println(array);
    return array;
}
	public static void c_insert(String [] info) throws SQLException{			//用户插入
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps =connection.prepareStatement("insert into client values(?,?,?,?,?,?)");
		ps.setString(1, info[0]);
		ps.setString(2, info[1]);
		ps.setString(3, info[2]);
		ps.setString(4, info[3]);
		ps.setString(5, info[4]);
		ps.setString(6, info[5]);
		int row=ps.executeUpdate();
		System.out.println("插入了"+row+"条client记录");	
		connection.close();
		ps.close();
		}
	
	public static void b_insert(String [] info) throws SQLException{			//银行卡插入
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps =connection.prepareStatement("insert into bank_card values(?,?,?)");
		ps.setString(1, info[0]);
		ps.setString(2, info[1]);
		ps.setString(3, info[2]);
		int row=ps.executeUpdate();
		System.out.println("插入了"+row+"条bank_card记录");	
		connection.close();
		ps.close();
		}
	
	public static void p_insert(String [] info) throws SQLException{			//资产插入
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps =connection.prepareStatement("insert into property values(?,?,?,?,?,?)");
		ps.setString(1, info[0]);
		ps.setString(2, info[1]);
		ps.setString(3, info[2]);
		ps.setString(4, info[3]);
		ps.setString(5, info[4]);
		ps.setString(6, info[5]);
		ps.setString(7, info[6]);
		ps.setString(8, info[7]);
		int row=ps.executeUpdate();
		System.out.println("插入了"+row+"条property记录");	
		connection.close();
		ps.close();
		}
	
	public static int c_update(String []info) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("update client set c_name=?,c_mail=?,c_id_card=?,c_phone=?,c_password=? where c_id=?");

		ps.setString(1, info[1]);
		ps.setString(2, info[2]);
		ps.setString(3, info[3]);
		ps.setString(4, info[4]);
		ps.setString(5, info[5]);
		ps.setString(6, info[0]);
		int row = ps.executeUpdate();
	    System.out.println("更新" + row + "条记录");
	    connection.close();
	    ps.close();
	    return row;
	
	}
	public static int b_update(String []info) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("update bank_card set b_type=?,b_c_id=? where b_number=?");

		ps.setString(1, info[1]);
		ps.setString(2, info[2]);
		ps.setString(3, info[0]);
		int row = ps.executeUpdate();
	    System.out.println("更新" + row + "条记录");
	    connection.close();
	    ps.close();
	    return row;
	
	}
	public static int p_update(String []info) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("update property set pro_c_id=?,pro_pif_id=?,pro_type=?,pro_status=?,pro_quantity=?,pro_income=?,pro_purchase_time=? where pro_id=?");

		ps.setString(1, info[1]);
		ps.setString(2, info[2]);
		ps.setString(3, info[3]);
		ps.setString(4, info[4]);
		ps.setString(5, info[5]);
		ps.setString(6, info[6]);
		ps.setString(7, info[7]);
		ps.setString(8, info[0]);
		int row = ps.executeUpdate();
	    System.out.println("更新" + row + "条记录");
	    connection.close();
	    ps.close();
	    return row;
	
	}
	public static int c_delete(String id)throws SQLException{
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("delete from client where c_id=?");

		ps.setString(1, id);
		int row = ps.executeUpdate();
	    System.out.println("删除" + row + "条记录");
	    connection.close();
	    ps.close();
	    return row;
	 }
	
	public static int b_delete(String number)throws SQLException{
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("delete from bank_card where b_number=?");

		ps.setString(1, number);
		int row = ps.executeUpdate();
	    System.out.println("删除" + row + "条记录");
	    connection.close();
	    ps.close();
	    return row;
	 }
	
	public static int p_delete(String id)throws SQLException{
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("delete from property where pro_id=?");

		ps.setString(1, id);
		int row = ps.executeUpdate();
	    System.out.println("删除" + row + "条记录");
	    connection.close();
	    ps.close();
	    return row;
	 }
	
	public static String[] c_select() throws SQLException{			//查全部
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
	    //3) 执行 SQL 语句得到结果集 ResultSet 对象
	    ResultSet rs = statement.executeQuery("select * from client");
	    //4) 循环遍历取出每一条记录
	    int num=0;
	    String[] array=new String[50];
	    for(int i=0;i<50;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        int c_id = rs.getInt("c_id");
	        String c_name = rs.getString("c_name");
	        String c_mail = rs.getString("c_mail");
	        String c_id_card = rs.getString("c_id_card");
	        String c_phone = rs.getString("c_phone");
	        String c_password = rs.getString("c_password");
	        num++;
	        array[num]="     "+ c_id + "     " + c_name + "        " + c_mail + "        " +c_id_card+"        " +c_phone+"        " +c_password;
	    //5) 输出的控制台上
	//System.out.println("编号：" + c_id + ", 姓名：" + c_name + ", 邮箱：" + c_mail + ", 卡号：" +c_id_card+", 电话：" +c_phone+", 密码：" +c_password);
	}
	    
	  
	    rs.close();
	    statement.close();
	    connection.close();
	    return array;
	}
	
	public static String[] b_select() throws SQLException{			//bank_card查全部
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
	    //3) 执行 SQL 语句得到结果集 ResultSet 对象
	    ResultSet rs = statement.executeQuery("select * from bank_card");
	    //4) 循环遍历取出每一条记录
	    int num=0;
	    String[] array=new String[50];
	    for(int i=0;i<50;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        
	        String b_number = rs.getString("b_number");
	        String b_type = rs.getString("b_type");
	        int b_c_id = rs.getInt("b_c_id");
	        num++;
	        array[num]="     "+ b_number + "     " + b_type + "        " + b_c_id ;
	    //5) 输出的控制台上
	System.out.println("     "+ b_number + "     " + b_type + "        " + b_c_id );
	}  
	    rs.close();
	    statement.close();
	    connection.close();
	    return array;
	}
	
	public static String[] fp_select() throws SQLException{			//finances_product查全部，对不齐
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
	    //3) 执行 SQL 语句得到结果集 ResultSet 对象
	    ResultSet rs = statement.executeQuery("select * from finances_product");
	    //4) 循环遍历取出每一条记录
	    int num=0;
	    String[] array=new String[10];
	    for(int i=0;i<10;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        
	        String p_name = rs.getString("p_name");
	        int p_id = rs.getInt("p_id");
	        String p_description = rs.getString("p_description");
	        int p_amount = rs.getInt("p_amount");
	        int p_year = rs.getInt("p_year");
	        num++;
	        array[num]="     "+ p_name + "     " + p_id + "        " + p_description + "        "+p_amount+"        "+p_year ;
	    //5) 输出的控制台上
	System.out.println("     "+ p_name + "     " + p_id + "        " + p_description + "        "+p_amount+"        "+p_year);
	}  
	    rs.close();
	    statement.close();
	    connection.close();
	    return array;
	}
	
	public static String[] f_select() throws SQLException{			//fund查全部
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
	    //3) 执行 SQL 语句得到结果集 ResultSet 对象
	    ResultSet rs = statement.executeQuery("select * from fund");
	    //4) 循环遍历取出每一条记录
	    int num=0;
	    String[] array=new String[10];
	    for(int i=0;i<10;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        
	        String f_name = rs.getString("f_name");
	        int f_id = rs.getInt("f_id");
	        String f_type = rs.getString("f_type");
	        int f_amount = rs.getInt("f_amount");
	        String risk_level = rs.getString("risk_level");
	        int f_manager = rs.getInt("f_manager");
	        num++;
	        array[num]= f_name + "                        " + f_id + "                            " + f_type + "                         "+f_amount+"                             "+risk_level+"                           "+ f_manager;
	    //5) 输出的控制台上
	System.out.println( f_name + "      " + f_id + "      " + f_type + "           "+f_amount+"         "+risk_level+"      "+ f_manager);
	}  
	    rs.close();
	    statement.close();
	    connection.close();
	    return array;
	}
	
	public static String[] i_select() throws SQLException{			//insurance查全部
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
	    //3) 执行 SQL 语句得到结果集 ResultSet 对象
	    ResultSet rs = statement.executeQuery("select * from insurance");
	    //4) 循环遍历取出每一条记录
	    int num=0;
	    String[] array=new String[10];
	    for(int i=0;i<10;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        
	        String i_name = rs.getString("i_name");
	        int i_id = rs.getInt("i_id");
	        int i_amount = rs.getInt("i_amount");
	        String i_person = rs.getString("i_person");
	        int i_year=rs.getInt("i_year");
	        String i_project = rs.getString("i_project");
	        num++;
	        array[num]="     "+ i_name + "          " + i_id + "                         " + i_amount+"                    "+i_person+"                         "+i_year+"                   "+ i_project;
	    //5) 输出的控制台上 
	System.out.println("     "+ i_name + "     " + i_id + "           " + i_amount+"           "+i_person+"           "+i_year+"          "+ i_project);
	}  
	    rs.close();
	    statement.close();
	    connection.close();
	    return array;
	}
	
	public static String[] p_select() throws SQLException{			//property查全部
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
	    //3) 执行 SQL 语句得到结果集 ResultSet 对象
	    ResultSet rs = statement.executeQuery("select * from property");
	    //4) 循环遍历取出每一条记录
	    int num=0;
	    String[] array=new String[10];
	    for(int i=0;i<10;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        
	        int pro_id = rs.getInt("pro_id");
	        int pro_c_id = rs.getInt("pro_c_id");
	        int pro_pif_id=rs.getInt("pro_pif_id");
	        int pro_type=rs.getInt("pro_type");
	        String pro_status = rs.getString("pro_status");
	        int pro_quantity=rs.getInt("pro_quantity");
	        int pro_income=rs.getInt("pro_income");
	        Date pro_purchase_time = rs.getDate("pro_purchase_time");
	        num++;
	        array[num]="    "+ pro_id + "             " + pro_c_id + "                " + pro_pif_id+"              "+pro_type+"                 "+pro_status+"             "+ pro_quantity+"           "+pro_income+"            "+pro_purchase_time;
	    //5) 输出的控制台上 
	//System.out.println("     "+ i_name + "     " + i_id + "           " + i_amount+"           "+i_person+"           "+i_year+"          "+ i_project);
	}  
	    rs.close();
	    statement.close();
	    connection.close();
	    return array;
	}
	public static String[] c_get(String id) throws SQLException{			//查一条c
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("select * from client where c_id=?");

		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
	    //4) 循环遍历取出每一条记录
	    String[] array=new String[6];
	    for(int i=0;i<6;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        array[0] = rs.getString("c_id");
	        array[1] = rs.getString("c_name");
	        array[2] = rs.getString("c_mail");
	        array[3]  = rs.getString("c_id_card");
	        array[4]  = rs.getString("c_phone");
	        array[5]  = rs.getString("c_password");
	    //5) 输出的控制台上
	//System.out.println("编号：" + c_id + ", 姓名：" + c_name + ", 邮箱：" + c_mail + ", 卡号：" +c_id_card+", 电话：" +c_phone+", 密码：" +c_password);
	    }
	    return array;
	}
	
	public static String[] b_get(String id) throws SQLException{			//查一条b
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("select * from bank_card where b_number=?");

		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
	    //4) 循环遍历取出每一条记录
	    String[] array=new String[3];
	    for(int i=0;i<3;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        array[0] = rs.getString("b_number");
	        array[1] = rs.getString("b_type");
	        array[2] = rs.getString("b_c_id");

	    }
	    return array;
	}
	public static String[] p_get(String id) throws SQLException{			//查一条p
		String url = "jdbc:mysql://localhost:3306/financial_data_model";
		//1) 使用用户名、密码、URL 得到连接对象
		Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
		//com.mysql.jdbc.JDBC4Connection@68de145
		System.out.println(connection);
		PreparedStatement ps=connection.prepareStatement("select * from property where pro_id=?");

		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
	    //4) 循环遍历取出每一条记录
	    String[] array=new String[8];
	    for(int i=0;i<8;i++)
	    	array[i]=null;
	    while(rs.next()) {
	        array[0] = rs.getString("pro_id");
	        array[1] = rs.getString("pro_c_id");
	        array[2] = rs.getString("pro_pif_id");
	        array[3]  = rs.getString("pro_type");
	        array[4]  = rs.getString("pro_status");
	        array[5]  = rs.getString("pro_quantity");
	        array[6]  = rs.getString("pro_income");
	        array[7]  = rs.getString("pro_purchase_time");
	    }
	    return array;
	}
}
