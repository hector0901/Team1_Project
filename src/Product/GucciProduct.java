package Product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class GucciProduct extends JFrame {
  JTable table;
  JScrollPane jsp;
  Vector<String> col;
  Vector<Vector<String>> rowData;

  public GucciProduct() {// 마이페이지
    Dimension frameSize = this.getSize();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setTitle("MY PAGE");
    // setLocationRelativeTo(null);
    Container c = getContentPane();
    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    c.setBackground(Color.white);

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
    JTextField jt1 = new JTextField(200);

    JLabel pw = new JLabel("비밀번호");
    pw.setFont(new Font("맑은 고딕", Font.BOLD, 30));
    JTextField jt2 = new JTextField(200);

    JLabel name = new JLabel("이름");
    name.setFont(new Font("맑은 고딕", Font.BOLD, 30));
    JTextField jt3 = new JTextField(200);

    JLabel tel = new JLabel("전화번호");
    tel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
    JTextField jt4 = new JTextField(200);

    JLabel adr = new JLabel("주소");
    adr.setFont(new Font("맑은 고딕", Font.BOLD, 30));
    JTextField jt5 = new JTextField(200);

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

    String id1 = jt1.getText();
    String pw1 = jt2.getText();
    String name1 = jt3.getText();
    String tel1 = jt4.getText();
    String addr = jt5.getText();

    // 수정하기버튼
    jb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // 주문서확인
        String regExp = "^[0-9]+$";
        String PHONE = jt4.getText().trim();
        String ADDRESS = jt5.getText().trim();

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
        JOptionPane.showMessageDialog(null, "정말로 탈퇴 하시겠습니까?", "가지마..", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);

        rowData.remove(table);
        table.updateUI();
      }
    });

    col = new Vector<String>();
    rowData = new Vector<Vector<String>>();

    col.add("주문번호");
    col.add("상품번호");
    col.add("수량");
    col.add("주문일");

    table = new JTable(rowData, col);
    c.add(table);

    // 스크롤 부착
    jsp = new JScrollPane(table);
    jsp.getViewport().setBackground(Color.WHITE);
    jsp.setBounds(250, 318, 600, 500);
    c.add(jsp);

    Vector<String> v = new Vector<String>();
    v.add(pw1);
    v.add(id1);
    v.add(name1);
    v.add(tel1);
    v.add(addr);

    rowData.add(v);// 텍스트 필드에 있는 값을 추가하기.
    table.updateUI();

    c.add(j1);
    c.add(j, BorderLayout.NORTH);

    setVisible(true);
  }

  public static void main(String[] args) {

  }
}