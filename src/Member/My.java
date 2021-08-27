package Member;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import Beans.MemberVO;
import DB_Tool.DBClose;
import DB_Tool.DBOpen;



public class My extends JFrame {

  private static final long serialVersionUID = 1L;

   /**
   * @������ : data
   * @���� : ���̺� ǥ�õ� �����͸� �����ϴ� ���� ���� ����
   */
  @SuppressWarnings("rawtypes")
  private Vector data = null;

   /**
   * @������ : title
   * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����ϴ� ���� ���� ����
   */
  @SuppressWarnings("rawtypes")
  private Vector title = null;
  private JTable table = null;

   /**
   * @������ : model
   * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����͸� �����ϴ� �� ��ü ���� ����
   */
  private DefaultTableModel model = null;
  private JLabel top_label = null;

  String Url = "jdbc:oracle:thin:@172.16.14.12:1521:XE"; // URL ���� ���� ����
  private String user = "sys as sysdba"; // user ���� ���� ���� -> hr
  private String password = "0000"; // password ���� ���� ���� -> hr

  private Connection conn = null;
  private Statement stmt = null;
  private PreparedStatement pstmtAdd = null;
  private PreparedStatement pstmtDel = null;
  private PreparedStatement pstmtUpdate = null;

  MemberVO memberVO = new MemberVO();
  // ȸ�� ��ȣ ����
  int member_no = 1;

  // ȸ�� Ż��� ID, PW �ʵ�
  JTextField id_field = new JTextField();
  JPasswordField pw_field = new JPasswordField();

  String PHONE = null;
  String ADDRESS = null;

  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  StringBuffer sql = null;

  DBOpen dbopen = new DBOpen();
  DBClose dbclose = new DBClose();

  JTextField jt1 = new JTextField(200); // ID
  JPasswordField jt2 = new JPasswordField(200); // PW
  JTextField jt3 = new JTextField(200); // NAME
  JTextField jt4 = new JTextField(200); // TEL
  JTextField jt5 = new JTextField(200); // ADDRESS

  public My() {// ����������
    preDbTreatment();
    data = new Vector<>();

    title = new Vector<>();

    title.add("�ֹ� ��ȣ");
    title.add("��ǰ��");
    title.add("����");
    title.add("�ֹ���");

    // ���̺� ǥ�õ� �� ��ü ����
    model = new DefaultTableModel();

    // selectAll() : �����ͺ��̽� Member ���̺� �ִ� ��� �����͸� ������ ���� �޼ҵ�
    // ���� result�� ����
    Vector result = selectAll();

    // �𵨿� ����� ������(result)�� ���� ����
    model.setDataVector(result, title);

    // ���� ���� ���̺� ����
    table = new JTable(model);

    // ���̺� ��ũ���� ����
    JScrollPane sp = new JScrollPane(table);

    Dimension frameSize = this.getSize();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setTitle("MY PAGE");
    // setLocationRelativeTo(null);
    Container c = getContentPane();
    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    c.setBackground(Color.white);

    // ��ũ�� ����
    JPanel scroll_page = new JPanel();
    c.add(scroll_page);
    scroll_page.add(sp);
    scroll_page.setBackground(Color.white);
    scroll_page.setBounds(250, 318, 600, 500);
    // scroll_page.setBorder(new LineBorder(Color.gray,1));
    table.setPreferredScrollableViewportSize(new Dimension(580,450));
    sp.getViewport().setBackground(Color.WHITE);
    // ������ ����� ó���� �̺�Ʈ ó��

    JPanel j = new JPanel();
    j.setBackground(Color.white);
    j.setPreferredSize(new Dimension(1200, 195));
    ImageIcon image = new ImageIcon(".//image//����.png");
    JLabel my = new JLabel(image);
    j.add(my);

    JPanel j1 = new JPanel();
    j1.setBackground(Color.white);
    j1.setLayout(null);

    JLabel str = new JLabel("���� ����");
    str.setFont(new Font("���� ���", Font.BOLD, 30));

    JLabel str1 = new JLabel("�ֹ� ��ȸ");
    str1.setFont(new Font("���� ���", Font.BOLD, 30));

    JLabel id = new JLabel("���̵�");
    id.setFont(new Font("���� ���", Font.BOLD, 30));

    JLabel pw = new JLabel("��й�ȣ");
    pw.setFont(new Font("���� ���", Font.BOLD, 30));

    JLabel name = new JLabel("�̸�");
    name.setFont(new Font("���� ���", Font.BOLD, 30));

    JLabel tel = new JLabel("��ȭ��ȣ");
    tel.setFont(new Font("���� ���", Font.BOLD, 30));

    JLabel adr = new JLabel("�ּ�");
    adr.setFont(new Font("���� ���", Font.BOLD, 30));

    JButton jb = new JButton("�����ϱ�");
    jb.setBackground(Color.black);
    jb.setForeground(Color.WHITE);
    jb.setFont(new Font("���� ���", Font.BOLD, 20));

    JButton jb1 = new JButton("Ż���ϱ�");
    jb1.setBackground(Color.black);
    jb1.setForeground(Color.WHITE);
    jb1.setFont(new Font("���� ���", Font.BOLD, 20));

    id.setBounds(1000, 100, 100, 100);
    pw.setBounds(1000, 200, 200, 100);
    name.setBounds(1000, 300, 100, 100);
    tel.setBounds(1000, 400, 200, 100);
    adr.setBounds(1000, 500, 100, 100);
    str.setBounds(1300, 5, 500, 100);
    str1.setBounds(483, 5, 500, 100);

    jt1.setBounds(1170, 120, 500, 50);
    jt1.setEnabled(false);
    jt2.setBounds(1170, 220, 500, 50);
    jt2.setEnabled(false);
    jt3.setBounds(1170, 320, 500, 50);
    jt3.setEnabled(false);
    jt4.setBounds(1170, 420, 500, 50);
    jt5.setBounds(1170, 520, 500, 50);
    jb.setBounds(1170, 650, 200, 50);
    jb1.setBounds(1450, 650, 200, 50);

    j1.add(id);
    j1.add(jt1);
    j1.add(pw);
    j1.add(jt2);
    j1.add(name);
    j1.add(jt3);
    j1.add(tel);
    j1.add(jt4);
    j1.add(adr);
    j1.add(jt5);
    j1.add(str);
    j1.add(str1);
    j1.add(jb);
    j1.add(jb1);

    String id1 = jt1.getText().trim();
    String pw1 = jt2.getText().trim();
    String name1 = jt3.getText().trim();
    String tel1 = jt4.getText().trim();
    String addr = jt5.getText().trim();

    MemberVO memberVO = read(member_no);

    jt1.setText(memberVO.getMember_id());
    jt2.setText(memberVO.getMember_passwd());
    jt3.setText(memberVO.getMember_name());
    jt4.setText(memberVO.getMember_tel());
    jt5.setText(memberVO.getMember_address());

    // �����ϱ��ư
    jb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // �ֹ���Ȯ��
        String regExp = "^[0-9]+$";
        String PHONE = jt4.getText().trim();
        String ADDRESS = jt5.getText().trim();

        MemberVO memberVO = new MemberVO();
        int update = member_update(memberVO);

        if (!(PHONE.length() == 10 || PHONE.length() == 11)) {
          JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� 10-11�ڸ��� �����մϴ�");
          jt4.setText("");
        } else if (!(PHONE.matches(regExp))) {
          JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڸ� �Է��� �� �ֽ��ϴ�");
          jt4.setText("");
        }
        if (ADDRESS.length() == 0) {
          JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���");
          jt5.setText("");
        }
        if (!(PHONE.length() == 10 || PHONE.length() == 11) || ADDRESS.length() == 0 || !(PHONE.matches(regExp))) {
          JOptionPane.showMessageDialog(null, "���� ����, �ٽ� �õ��� �ּ���.");
        } else {
          JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�", "�����ϱ�", JOptionPane.INFORMATION_MESSAGE);
        }

      }
    });

    // Ż���ϱ�
    jb1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        JFrame member_drop = new JFrame();

        member_drop.setTitle("Ż���ϱ�");

        Container con = member_drop.getContentPane();

        con.setBackground(Color.white);

        con.setLayout(null);

        member_drop.setResizable(false);

        member_drop.setSize(500, 500);

        member_drop.setVisible(true);

        JPanel title = new JPanel();

        title.setLayout(null);

        title.setBounds(0, 0, 500, 50);

        title.setBackground(Color.black);

        JLabel label = new JLabel("Ż���ϱ�");

        Font font = new Font("���� ��� Semilight", Font.BOLD, 14);

        label.setForeground(Color.white);

        label.setFont(font);

        label.setBounds(170, 0, 160, 50);

        title.add(label);

        member_drop.add(title);

        JLabel name = new JLabel("ID");
        Font font2 = new Font("���� ��� Semilight", Font.PLAIN, 18);
        name.setFont(font2);
        con.add(name);
        name.setBounds(140, 100, 80, 50);

        con.add(id_field);
        id_field.setBounds(135, 155, 220, 30);

        JLabel tel = new JLabel("Password");
        tel.setFont(font2);
        con.add(tel);
        tel.setBounds(140, 200, 80, 50);

        con.add(pw_field);
        pw_field.setBounds(135, 255, 220, 30);

        ImageIcon findI = new ImageIcon(".//image//Ż���ϱ�.png");
        Image findI2 = findI.getImage();
        Image find1 = findI2.getScaledInstance(60, 25, Image.SCALE_SMOOTH);
        ImageIcon findI3 = new ImageIcon(find1);
        JButton findbutton = new JButton(findI3);
        con.add(findbutton);
        findbutton.setBorderPainted(false);
        findbutton.setContentAreaFilled(false);
        findbutton.setFocusPainted(false);
        findbutton.setBounds(210, 330, 60, 25);

        findbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            String check_id = id_field.getText().trim();
            String check_pw = pw_field.getText().trim();
            int result = member_delete(check_id, check_pw);

            if (result > 0) {
              JOptionPane.showMessageDialog(null, "ȸ��Ż�� ����Ǿ����ϴ�. ���α׷��� ����˴ϴ�.");

              System.exit(0);

            } else if (check_id.length() == 0 || check_pw.length() == 0) {
              JOptionPane.showMessageDialog(null, "ID�� PW�� �Էµ��� �ʾҽ��ϴ�.");
              id_field.setText("");
              pw_field.setText("");
            } else {
              JOptionPane.showMessageDialog(null, "ID�� PW�� ��ġ���� �ʽ��ϴ�. \n�ٽ� �õ����ּ���");
              id_field.setText("");
              pw_field.setText("");
            }

          }
        });

      }
    });

    Vector<String> v = new Vector<String>();
    v.add(pw1);
    v.add(id1);
    v.add(name1);
    v.add(tel1);
    v.add(addr);

    c.add(j1);
    c.add(j, BorderLayout.NORTH);

    setVisible(true);

  }

  private Vector selectAll() {

    data.clear();

    try {

      ResultSet rs = stmt.executeQuery(
          "select b.buy_no, p.productname, b.buy_cnt, b.buy_date from buy b, product p where b.productno = p.productno order by buy_no desc");

      while (rs.next()) {

        Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

        int buy_no = Integer.parseInt(rs.getString(1));
        String productname = rs.getString(2);
        int buy_cnt = Integer.parseInt(rs.getString(3));
        String buy_date = rs.getString(4);

        // ���Ϳ� ������ �� �߰�
        in.add(buy_no);
        in.add(productname);
        in.add(buy_cnt);
        in.add(buy_date);

        // ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�

        data.add(in);

      }

    } catch (Exception e) {

      e.printStackTrace();

    }

    return data; // ��ü ������ �����ϴ� data ���� ����

  }

  private void preDbTreatment() {

    try {

      Class.forName("oracle.jdbc.driver.OracleDriver");

      conn = DriverManager.getConnection(Url, user, password);
      stmt = conn.createStatement();

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

  /**
   * ȸ��Ż�� �޼ҵ�
   * @param member_id
   * @param member_passwd
   * @return
   */
  public int member_delete(String member_id, String member_passwd) {
    int count = 0; // ������ ���ڵ� ����

    try {
      con = this.dbopen.getConnection();

      // ���� ó��
      sql = new StringBuffer();
      sql.append(" DELETE FROM member");
      sql.append(" WHERE member_id = ? AND member_passwd = ?");

      String ID = id_field.getText().trim();
      String PW = pw_field.getText().trim();

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, ID);
      pstmt.setString(2, PW);
      count = pstmt.executeUpdate(); // SQL ����

    } catch (SQLException e) {
      System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt, rs);
    }

    return count;

  }

   /**
   * ���� ȸ�� ���� ��ȸ
   * @param member_no
   * @return
   */
  public MemberVO read(int member_no) {
    MemberVO memberVO = null;

    try {
      con = this.dbopen.getConnection();

      sql = new StringBuffer();
      sql.append(" SELECT member_id, member_passwd, member_name, member_tel, member_address");
      sql.append(" FROM member");
      sql.append(" WHERE member_no = ?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, member_no);
      rs = pstmt.executeQuery(); // SQL ����

      if (rs.next() == true) { // ù��° ���ڵ� -> ������ ���ڵ�� �̵�

        memberVO = new MemberVO();

        memberVO.setMember_id(rs.getString("member_id"));
        memberVO.setMember_passwd(rs.getString("member_passwd"));
        memberVO.setMember_name(rs.getString("member_name"));
        memberVO.setMember_tel(rs.getString("member_tel"));
        memberVO.setMember_address(rs.getString("member_address"));

      }

    } catch (SQLException e) {
      System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
      e.printStackTrace();
    } finally {
      this.dbclose.close(con, pstmt, rs);
    }

    return memberVO;

  }

  /**
   * ȸ�� ���ڵ� ����
   * @param memberVO
   * @return
   */
  public int member_update(MemberVO memberVO) {
    int count = 0; // ������ ���ڵ��� ����

    try {
      con = this.dbopen.getConnection();

      sql = new StringBuffer();
      sql.append(" UPDATE member");
      sql.append(" SET member_tel = ?, member_address=?"); // ?�� ������ ������ ��ü��
      sql.append(" WHERE member_no = ?");

      String PHONE = jt4.getText().trim();
      String ADDRESS = jt5.getText().trim();

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, PHONE);
      pstmt.setString(2, ADDRESS);
      pstmt.setInt(3, member_no);

      count = pstmt.executeUpdate(); // SQL ����

    } catch (SQLException e) {
      System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt, rs);
    }

    return count;

  }

  public static void main(String[] args) {
    new My();

  }

}