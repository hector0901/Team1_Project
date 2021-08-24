package Product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Beans.BuyVO;
import Beans.MemberVO;
import DB_Tool.DBClose;
import DB_Tool.DBOpen;
import Member.My;

public class PradaProduct3 extends JFrame {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;

	DBOpen dbopen = new DBOpen();
	DBClose dbclose = new DBClose();
	
	BuyVO buyVO = new BuyVO();

	int ea;
	ImageIcon gg[] = { new ImageIcon(".//image//����� �ö��� ��Ŀ �� ĵ���� ��Ŷ��.png"), new ImageIcon(".//image//Ŭ���� �귯�õ� ���� ���.png"),
			new ImageIcon(".//image//ũ�� ��Ʋ���� �� ī���.png") };

	// ȸ����ȣ 1����, ��ǰ��ȣ�� �� ��ǰ���� 1~6���� ���� ����
	// 6�� ��ǰ
	int member_no = 1;
	int product_no = 6;

	JTextField jt = new JTextField(10);// ���� �Է��ʵ�

	public PradaProduct3() {
		super("��ǰ â");
		Container c = getContentPane();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// ���� ���� �г�
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.setPreferredSize(new Dimension(1200, 195));
		JLabel my = new JLabel(new ImageIcon(".//image//��ǰ.png"));
		panel1.add(my);

		c.add(panel1, BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.white);
		panel2.setLayout(null);
		// �귣���̸�
		JLabel j1 = new JLabel("PRADA");
		j1.setBounds(1200, 50, 400, 90);
		j1.setFont(new Font("���� ����", Font.BOLD, 60));

		// ��ǰ�̸�
		JLabel j2 = new JLabel("ũ�� ��Ʋ���� �� ī���");
		j2.setFont(new Font("���� ����", Font.BOLD, 30));
		j2.setBounds(1200, 160, 700, 50);

		// ����
		JLabel j3 = new JLabel("$1,990");
		j3.setFont(new Font("���� ����", Font.BOLD, 30));
		j3.setBounds(1200, 200, 300, 100);

		// ��ǰ����
		JButton jb1 = new JButton("��ǰ����");
		jb1.setForeground(Color.WHITE);
		jb1.setBackground(Color.BLACK);
		jb1.setFont(new Font("���� ����", Font.BOLD, 30));
		jb1.setBounds(1300, 600, 300, 100);

		// ��ǰ����
		/*
		 * JTextArea ja1=new JTextArea("��ǰ����"); ja1.setFont(new
		 * Font("���� ����",Font.BOLD,20)); ja1.setBounds(1200, 350, 600, 350);
		 */

		JLabel text = new JLabel("ũ�� ��Ʋ���� �� ī�������, ���õ� �ŷ°� ������ ������");
		JLabel text2 = new JLabel("V ��ũ������ ���� ��� ��ư���� ��¦�̴� Ŭ����");
		text.setFont(new Font("���� ����", Font.BOLD, 18));
		text2.setFont(new Font("���� ����", Font.BOLD, 18));
		text.setBounds(1200, 320, 600, 50);
		text2.setBounds(1200, 350, 600, 50);

		// ��ǰ��ȣ
		JLabel j4 = new JLabel("no.6");
		j4.setFont(new Font("���� ����", Font.BOLD, 20));
		j4.setBounds(250, 20, 100, 40);

		// �̹���
		JLabel pro1 = new JLabel(new ImageIcon(".//image//����� �ö��� ��Ŀ �� ĵ���� ��Ŷ��.png"));
		JLabel pro2 = new JLabel(new ImageIcon(".//image//Ŭ���� �귯�õ� ���� ���.png"));
		JLabel pro3 = new JLabel(new ImageIcon(".//image//ũ�� ��Ʋ���� �� ī���.png"));

		pro1.setBounds(400, 65, 500, 500);
		pro2.setBounds(400, 90, 500, 500);
		pro3.setBounds(400, 110, 500, 500);

		// panel2.add(pro1);
		// panel2.add(pro2);
		panel2.add(pro3);

		JButton plus = new JButton("+");
		JButton minus = new JButton("-");

		jt.setFont(new Font("���� ����", Font.BOLD, 20));

		minus.setBackground(Color.BLACK);
		minus.setForeground(Color.WHITE);
		minus.setFont(new Font("���� ����", Font.BOLD, 20));
		plus.setBackground(Color.BLACK);
		plus.setForeground(Color.WHITE);
		plus.setFont(new Font("���� ����", Font.BOLD, 20));

		minus.setBounds(1310, 500, 70, 50);// -
		jt.setBounds(1393, 500, 105, 50);// jt�ʵ�
		plus.setBounds(1513, 500, 70, 50);// +
		panel2.add(plus);
		panel2.add(minus);
		panel2.add(jt);
		jt.setText("0");

		JLabel jt1=new JLabel("$ 0");//����
        jt1.setFont(new Font("���� ����",Font.BOLD,30));
        jt1.setBounds(1393,450,105,50);
        panel2.add(jt1);
     
        plus.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           //+
           ea++;
           jt.setText(String.valueOf(ea));
           jt1.setText(String.valueOf("$"+(ea*1990)));
        }
        });
        minus.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           //-
           ea--;
           jt.setText(String.valueOf(ea));
           jt1.setText(String.valueOf("$"+(ea*1990)));
        }
        });

		// ��ǰ����
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// jt.setText("");
				
				buy(product_no, member_no);
				My my = new My();
                my.setVisible(true);
				
			}
		});

		panel2.add(j1);
		panel2.add(j2);
		panel2.add(j3);
		panel2.add(jb1);
		panel2.add(text);
		panel2.add(text2);
		panel2.add(j4);
		c.add(panel2);
		setVisible(true);
	}


	 /**
	 * �ֹ��ϱ� �޼ҵ�
	 * @param product_no
	 * @param member_no
	 * @return
	 */
	public int buy(int product_no, int member_no) {
		int count = 0; // ��ϵ� ���ڵ� ����
		
		product_no = 6;
		member_no = 1;

		try {
			con = this.dbopen.getConnection();

			sql = new StringBuffer();
			sql.append(
					"insert into buy(buy_no, productno, member_no, buy_cnt,buy_date) values (buy_no_seq.nextval, ?, ?, ?,sysdate)");

			// �� �ؽ�Ʈ�ʵ忡 �Էµ� ���� ������ ����
			String regExp = "^[0-9]+$";
			
			String CNT = jt.getText().trim();
			int cnt = Integer.parseInt(CNT);

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, product_no);
			pstmt.setInt(2, member_no);
			pstmt.setInt(3, cnt);
			
			rs = pstmt.executeQuery(); 
			
			if (CNT == null) {
				JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
				jt.setText("");
			}
			if (cnt <= 0) {
				JOptionPane.showMessageDialog(null, "�ֹ��� 1���̻���� �����մϴ�");
				jt.setText("0");
			}
			if (cnt <= 0 || CNT == null || !(CNT.matches(regExp))) {
				JOptionPane.showMessageDialog(null, "���� ����, �ٽ� �õ��� �ּ���.");
			} else {
				JOptionPane.showMessageDialog(null, "�ֹ��� �Ϸ� �Ǿ����ϴ�!", "�ֹ� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
				jt.setText("");
				dispose();
			}
			
			System.out.println(count);

		} catch (Exception e1) {
			System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
			e1.printStackTrace();
		} finally {
			this.dbclose.close(con, pstmt);
		}

		return count;

	}

	public static void main(String[] args) {

	}
}