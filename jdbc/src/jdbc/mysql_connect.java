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
//1) ʹ���û��������롢URL �õ����Ӷ���
Connection connection = DriverManager.getConnection(url, "root", "1207417313jj");
//com.mysql.jdbc.JDBC4Connection@68de145
System.out.println(connection);
Statement statement = connection.createStatement();
//3) ִ�� SQL ���õ������ ResultSet ����
ResultSet rs = statement.executeQuery("select * from client");
//4) ѭ������ȡ��ÿһ����¼
while(rs.next()) {
    int id = rs.getInt("c_id");
    String name = rs.getString("c_name");
    String mail = rs.getString("c_mail");
   // Date birthday = rs.getDate("birthday");
//5) ����Ŀ���̨��
System.out.println("��ţ�" + id + ", ������" + name + ", ���䣺" + mail );
}
//6) �ͷ���Դ
rs.close();
statement.close();
connection.close();
}

    }

