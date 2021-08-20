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

  public GucciProduct() {// ����������
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
    JTextField jt1 = new JTextField(200);

    JLabel pw = new JLabel("��й�ȣ");
    pw.setFont(new Font("���� ���", Font.BOLD, 30));
    JTextField jt2 = new JTextField(200);

    JLabel name = new JLabel("�̸�");
    name.setFont(new Font("���� ���", Font.BOLD, 30));
    JTextField jt3 = new JTextField(200);

    JLabel tel = new JLabel("��ȭ��ȣ");
    tel.setFont(new Font("���� ���", Font.BOLD, 30));
    JTextField jt4 = new JTextField(200);

    JLabel adr = new JLabel("�ּ�");
    adr.setFont(new Font("���� ���", Font.BOLD, 30));
    JTextField jt5 = new JTextField(200);

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

    String id1 = jt1.getText();
    String pw1 = jt2.getText();
    String name1 = jt3.getText();
    String tel1 = jt4.getText();
    String addr = jt5.getText();

    // �����ϱ��ư
    jb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // �ֹ���Ȯ��
        String regExp = "^[0-9]+$";
        String PHONE = jt4.getText().trim();
        String ADDRESS = jt5.getText().trim();

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
        JOptionPane.showMessageDialog(null, "������ Ż�� �Ͻðڽ��ϱ�?", "������..", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);

        rowData.remove(table);
        table.updateUI();
      }
    });

    col = new Vector<String>();
    rowData = new Vector<Vector<String>>();

    col.add("�ֹ���ȣ");
    col.add("��ǰ��ȣ");
    col.add("����");
    col.add("�ֹ���");

    table = new JTable(rowData, col);
    c.add(table);

    // ��ũ�� ����
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

    rowData.add(v);// �ؽ�Ʈ �ʵ忡 �ִ� ���� �߰��ϱ�.
    table.updateUI();

    c.add(j1);
    c.add(j, BorderLayout.NORTH);

    setVisible(true);
  }

  public static void main(String[] args) {

  }
}