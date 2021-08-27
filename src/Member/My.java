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
   * @변수명 : data
   * @설명 : 테이블에 표시될 데이터를 저장하는 벡터 변수 선언
   */
  @SuppressWarnings("rawtypes")
  private Vector data = null;

   /**
   * @변수명 : title
   * @설명 : 테이블에 표시될 타이틀을 저장하는 벡터 변수 선언
   */
  @SuppressWarnings("rawtypes")
  private Vector title = null;
  private JTable table = null;

   /**
   * @변수명 : model
   * @설명 : 테이블에 표시될 타이틀과 데이터를 저장하는 모델 객체 변수 선언
   */
  private DefaultTableModel model = null;
  private JLabel top_label = null;

  String Url = "jdbc:oracle:thin:@172.16.14.12:1521:XE"; // URL 정보 저장 변수
  private String user = "sys as sysdba"; // user 정보 저장 변수 -> hr
  private String password = "0000"; // password 정보 저장 변수 -> hr

  private Connection conn = null;
  private Statement stmt = null;
  private PreparedStatement pstmtAdd = null;
  private PreparedStatement pstmtDel = null;
  private PreparedStatement pstmtUpdate = null;

  MemberVO memberVO = new MemberVO();
  // 회원 번호 변경
  int member_no = 1;

  // 회원 탈퇴용 ID, PW 필드
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

  public My() {// 마이페이지
    preDbTreatment();
    data = new Vector<>();

    title = new Vector<>();

    title.add("주문 번호");
    title.add("상품명");
    title.add("수량");
    title.add("주문일");

    // 테이블에 표시될 모델 객체 생성
    model = new DefaultTableModel();

    // selectAll() : 데이터베이스 Member 테이블에 있는 모든 데이터를 가지고 오는 메소드
    // 벡터 result에 저장
    Vector result = selectAll();

    // 모델에 변경된 데이터(result)를 새로 적용
    model.setDataVector(result, title);

    // 모델을 통해 테이블 생성
    table = new JTable(model);

    // 테이블에 스크롤팬 생성
    JScrollPane sp = new JScrollPane(table);

    Dimension frameSize = this.getSize();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setTitle("MY PAGE");
    // setLocationRelativeTo(null);
    Container c = getContentPane();
    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    c.setBackground(Color.white);

    // 스크롤 설정
    JPanel scroll_page = new JPanel();
    c.add(scroll_page);
    scroll_page.add(sp);
    scroll_page.setBackground(Color.white);
    scroll_page.setBounds(250, 318, 600, 500);
    // scroll_page.setBorder(new LineBorder(Color.gray,1));
    table.setPreferredScrollableViewportSize(new Dimension(580,450));
    sp.getViewport().setBackground(Color.WHITE);
    // 프레임 종료시 처리될 이벤트 처리

    JPanel j = new JPanel();
    j.setBackground(Color.white);
    j.setPreferredSize(new Dimension(1200, 195));
    ImageIcon image = new ImageIcon(".//image//마페.png");
    JLabel my = new JLabel(image);
    j.add(my);

    JPanel j1 = new JPanel();
    j1.setBackground(Color.white);
    j1.setLayout(null);

    JLabel str = new JLabel("개인 정보");
    str.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JLabel str1 = new JLabel("주문 조회");
    str1.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JLabel id = new JLabel("아이디");
    id.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JLabel pw = new JLabel("비밀번호");
    pw.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JLabel name = new JLabel("이름");
    name.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JLabel tel = new JLabel("전화번호");
    tel.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JLabel adr = new JLabel("주소");
    adr.setFont(new Font("맑은 고딕", Font.BOLD, 30));

    JButton jb = new JButton("수정하기");
    jb.setBackground(Color.black);
    jb.setForeground(Color.WHITE);
    jb.setFont(new Font("맑은 고딕", Font.BOLD, 20));

    JButton jb1 = new JButton("탈퇴하기");
    jb1.setBackground(Color.black);
    jb1.setForeground(Color.WHITE);
    jb1.setFont(new Font("맑은 고딕", Font.BOLD, 20));

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

    // 수정하기버튼
    jb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // 주문서확인
        String regExp = "^[0-9]+$";
        String PHONE = jt4.getText().trim();
        String ADDRESS = jt5.getText().trim();

        MemberVO memberVO = new MemberVO();
        int update = member_update(memberVO);

        if (!(PHONE.length() == 10 || PHONE.length() == 11)) {
          JOptionPane.showMessageDialog(null, "전화번호는 10-11자리만 가능합니다");
          jt4.setText("");
        } else if (!(PHONE.matches(regExp))) {
          JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력할 수 있습니다");
          jt4.setText("");
        }
        if (ADDRESS.length() == 0) {
          JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
          jt5.setText("");
        }
        if (!(PHONE.length() == 10 || PHONE.length() == 11) || ADDRESS.length() == 0 || !(PHONE.matches(regExp))) {
          JOptionPane.showMessageDialog(null, "수정 실패, 다시 시도해 주세요.");
        } else {
          JOptionPane.showMessageDialog(null, "수정이 완료되었습니다", "수정하기", JOptionPane.INFORMATION_MESSAGE);
        }

      }
    });

    // 탈퇴하기
    jb1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        JFrame member_drop = new JFrame();

        member_drop.setTitle("탈퇴하기");

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

        JLabel label = new JLabel("탈퇴하기");

        Font font = new Font("맑은 고딕 Semilight", Font.BOLD, 14);

        label.setForeground(Color.white);

        label.setFont(font);

        label.setBounds(170, 0, 160, 50);

        title.add(label);

        member_drop.add(title);

        JLabel name = new JLabel("ID");
        Font font2 = new Font("맑은 고딕 Semilight", Font.PLAIN, 18);
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

        ImageIcon findI = new ImageIcon(".//image//탈퇴하기.png");
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
              JOptionPane.showMessageDialog(null, "회원탈퇴가 진행되었습니다. 프로그램이 종료됩니다.");

              System.exit(0);

            } else if (check_id.length() == 0 || check_pw.length() == 0) {
              JOptionPane.showMessageDialog(null, "ID나 PW가 입력되지 않았습니다.");
              id_field.setText("");
              pw_field.setText("");
            } else {
              JOptionPane.showMessageDialog(null, "ID나 PW가 일치하지 않습니다. \n다시 시도해주세요");
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

        Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

        int buy_no = Integer.parseInt(rs.getString(1));
        String productname = rs.getString(2);
        int buy_cnt = Integer.parseInt(rs.getString(3));
        String buy_date = rs.getString(4);

        // 벡터에 각각의 값 추가
        in.add(buy_no);
        in.add(productname);
        in.add(buy_cnt);
        in.add(buy_date);

        // 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가

        data.add(in);

      }

    } catch (Exception e) {

      e.printStackTrace();

    }

    return data; // 전체 데이터 저장하는 data 벡터 리턴

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
   * 회원탈퇴 메소드
   * @param member_id
   * @param member_passwd
   * @return
   */
  public int member_delete(String member_id, String member_passwd) {
    int count = 0; // 삭제된 레코드 갯수

    try {
      con = this.dbopen.getConnection();

      // 삭제 처리
      sql = new StringBuffer();
      sql.append(" DELETE FROM member");
      sql.append(" WHERE member_id = ? AND member_passwd = ?");

      String ID = id_field.getText().trim();
      String PW = pw_field.getText().trim();

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, ID);
      pstmt.setString(2, PW);
      count = pstmt.executeUpdate(); // SQL 실행

    } catch (SQLException e) {
      System.out.println("SQL 문법에 문제가 있는것 같습니다.");
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt, rs);
    }

    return count;

  }

   /**
   * 개별 회원 정보 조회
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
      rs = pstmt.executeQuery(); // SQL 실행

      if (rs.next() == true) { // 첫번째 레코드 -> 마지막 레코드로 이동

        memberVO = new MemberVO();

        memberVO.setMember_id(rs.getString("member_id"));
        memberVO.setMember_passwd(rs.getString("member_passwd"));
        memberVO.setMember_name(rs.getString("member_name"));
        memberVO.setMember_tel(rs.getString("member_tel"));
        memberVO.setMember_address(rs.getString("member_address"));

      }

    } catch (SQLException e) {
      System.out.println("SQL 문법에 문제가 있는것 같습니다.");
      e.printStackTrace();
    } finally {
      this.dbclose.close(con, pstmt, rs);
    }

    return memberVO;

  }

  /**
   * 회원 레코드 수정
   * @param memberVO
   * @return
   */
  public int member_update(MemberVO memberVO) {
    int count = 0; // 수정된 레코드의 갯수

    try {
      con = this.dbopen.getConnection();

      sql = new StringBuffer();
      sql.append(" UPDATE member");
      sql.append(" SET member_tel = ?, member_address=?"); // ?는 변수의 값으로 대체됨
      sql.append(" WHERE member_no = ?");

      String PHONE = jt4.getText().trim();
      String ADDRESS = jt5.getText().trim();

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, PHONE);
      pstmt.setString(2, ADDRESS);
      pstmt.setInt(3, member_no);

      count = pstmt.executeUpdate(); // SQL 실행

    } catch (SQLException e) {
      System.out.println("SQL 문법에 문제가 있는것 같습니다.");
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