package DB_Tool;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {
  public Connection getConnection() {
    Connection con = null;

    // MySQL
    // String className = "org.gjt.mm.mysql.Driver"; // MySQL ���� Drvier
    // String url =
    // "jdbc:mysql://localhost:3306/javadb?useUnicode=true&characterEncoding=euckr";
    // String user = "javauser";
    // String password = "1234";

    // Oracle
    String className = "oracle.jdbc.driver.OracleDriver";      // Oracle ���� Drvier
    String url = "jdbc:oracle:thin:@172.16.14.12:1521:XE";
    String user = "sys as sysdba";
    String password = "0000";

    try {
      Class.forName(className);                               // memory�� Ŭ������ �ε���, ��ü�� �������� ����.
      con = DriverManager.getConnection(url, user, password); // Oracle ����

    } catch (ClassNotFoundException e) {
      System.out.println("JDBC ����̹��� ���°� �����ϴ�.");
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return con;
  }

}
