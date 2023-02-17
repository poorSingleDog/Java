package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class mysql_connect {


public static void main(String[] args) throws SQLException {
String url = "jdbc:mysql://localhost:3306/financial_data_model";
//1) 使用用户名、密码、URL 得到连接对象
Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
//com.mysql.jdbc.JDBC4Connection@68de145
System.out.println(connection);
Statement statement = connection.createStatement();
//3) 执行 SQL 语句得到结果集 ResultSet 对象
ResultSet rs = statement.executeQuery("select * from client");
//4) 循环遍历取出每一条记录
while(rs.next()) {
    int id = rs.getInt("c_id");
    String name = rs.getString("c_name");
    String mail = rs.getString("c_mail");
   // Date birthday = rs.getDate("birthday");
//5) 输出的控制台上
System.out.println("编号：" + id + ", 姓名：" + name + ", 邮箱：" + mail );
}
//6) 释放资源
rs.close();
statement.close();
connection.close();
}

    }

