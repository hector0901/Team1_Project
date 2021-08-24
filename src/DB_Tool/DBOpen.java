package DB_Tool;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {
  public Connection getConnection() {
    Connection con = null;

    // MySQL
    // String className = "org.gjt.mm.mysql.Driver"; // MySQL 연결 Drvier
    // String url =
    // "jdbc:mysql://localhost:3306/javadb?useUnicode=true&characterEncoding=euckr";
    // String user = "javauser";
    // String password = "1234";

    // Oracle
    String className = "oracle.jdbc.driver.OracleDriver";      // Oracle 연결 Drvier
    String url = "jdbc:oracle:thin:@172.16.14.12:1521:XE";
    String user = "sys as sysdba";
    String password = "0000";

    try {
      Class.forName(className);                               // memory로 클래스를 로딩함, 객체는 생성하지 않음.
      con = DriverManager.getConnection(url, user, password); // Oracle 연결

    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버가 없는것 같습니다.");
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return con;
  }

}
