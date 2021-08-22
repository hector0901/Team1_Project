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
import DB_Tool.DBClose;
import DB_Tool.DBOpen;

public class GucciProduct2 extends JFrame {
//DB관련 변수들
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;

	DBOpen dbopen = new DBOpen();
	DBClose dbclose = new DBClose();

	// Buy Beans
	BuyVO buyVO = new BuyVO();

	// 회원번호 1고정, 상품번호는 각 상품마다 1~6으로 따로 지정
	// 2번 상품
	int member_no = 1;
	int product_no = 2;

	JTextField jt = new JTextField(10);// 수량 입력필드

	int ea;
	ImageIcon gg[] = { new ImageIcon(".//image//구찌 스몰 마틀라세 숄더백.png"), new ImageIcon(".//image//스퀘어 G 체크 트위드 드레스.png"),
			new ImageIcon(".//image//오버사이즈 케이블 니트 가디건.png") };

	public GucciProduct2() {
		super("상품 창");
		Container c = getContentPane();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// 위쪽 사진 패널
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.setPreferredSize(new Dimension(1200, 195));
		JLabel my = new JLabel(new ImageIcon(".//image//상품.png"));
		panel1.add(my);

		c.add(panel1, BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.white);
		panel2.setLayout(null);
		// 브랜드이름
		JLabel j1 = new JLabel("GUCCI");
		j1.setBounds(1200, 50, 400, 90);
		j1.setFont(new Font("맑은 고딕", Font.BOLD, 60));

		// 상품이름
		JLabel j2 = new JLabel("스퀘어 G 체크 트위드 드레스");
		j2.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		j2.setBounds(1200, 160, 700, 50);

		// 가격
		JLabel j3 = new JLabel("$3,700");
		j3.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		j3.setBounds(1200, 200, 300, 100);

		// 상품구매
		JButton jb1 = new JButton("상품구매");
		jb1.setForeground(Color.WHITE);
		jb1.setBackground(Color.BLACK);
		jb1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jb1.setBounds(1300, 600, 300, 100);

		// 상품설명
		/*
		 * JTextArea ja1=new JTextArea("상품설명"); ja1.setFont(new
		 * Font("맑은 고딕",Font.BOLD,20)); ja1.setBounds(1200, 350, 600, 350);
		 */

		JLabel text = new JLabel("체크 모티브와 구찌오 구찌(Guccio Gucci)");
		JLabel text2 = new JLabel("모노그램을 조합한 쇼트 슬리브 트위드 드레스.");
		text.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		text2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		text.setBounds(1200, 320, 600, 50);
		text2.setBounds(1200, 350, 600, 50);

		// 상품번호
		JLabel j4 = new JLabel("no.2");
		j4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		j4.setBounds(250, 20, 100, 40);

		// 이미지
		JLabel pro1 = new JLabel(new ImageIcon(".//image//구찌 스몰 마틀라세 숄더백.png"));
		JLabel pro2 = new JLabel(new ImageIcon(".//image//스퀘어 G 체크 트위드 드레스.png"));
		JLabel pro3 = new JLabel(new ImageIcon(".//image//오버사이즈 케이블 니트 가디건.png"));

		pro1.setBounds(400, 65, 500, 500);
		pro2.setBounds(400, 110, 500, 500);
		pro3.setBounds(400, 90, 500, 500);

		// panel2.add(pro1);
		panel2.add(pro2);
		// panel2.add(pro3);

		JButton plus = new JButton("+");
		JButton minus = new JButton("-");

		jt.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		minus.setBackground(Color.BLACK);
		minus.setForeground(Color.WHITE);
		minus.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		plus.setBackground(Color.BLACK);
		plus.setForeground(Color.WHITE);
		plus.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		minus.setBounds(1310, 500, 70, 50);// -
		jt.setBounds(1393, 500, 105, 50);// jt필드
		plus.setBounds(1513, 500, 70, 50);// +
		panel2.add(plus);
		panel2.add(minus);
		panel2.add(jt);

		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// +
				ea++;
				jt.setText(String.valueOf(ea));
			}
		});
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// -
				ea--;
				jt.setText(String.valueOf(ea));
			}
		});

		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 buy(product_no, member_no);

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
		 * 주문하기 메소드
		 * @param product_no
		 * @param member_no
		 * @return
		 */
	public int buy(int product_no, int member_no) {
		int count = 0; // 등록된 레코드 갯수

		product_no = 2;
		member_no = 1;

		try {
			con = this.dbopen.getConnection();

			sql = new StringBuffer();
			sql.append(
					"insert into buy(buy_no, productno, member_no, buy_cnt,buy_date) values (buy_no_seq.nextval, ?, ?, ?,sysdate)");

			// 각 텍스트필드에 입력된 값을 변수에 저장
			String regExp = "^[0-9]+$";

			String CNT = jt.getText().trim();
			int cnt = Integer.parseInt(CNT);

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, product_no);
			pstmt.setInt(2, member_no);
			pstmt.setInt(3, cnt);

			rs = pstmt.executeQuery();

			if (CNT == null) {
				JOptionPane.showMessageDialog(null, "수량를 입력해주세요");
				jt.setText("");
			}
			if (cnt <= 0) {
				JOptionPane.showMessageDialog(null, "주문은 1개이상부터 가능합니다");
				jt.setText("0");
			}
			if (cnt <= 0 || CNT == null || !(CNT.matches(regExp))) {
				JOptionPane.showMessageDialog(null, "구매 실패, 다시 시도해 주세요.");
			} else {
				JOptionPane.showMessageDialog(null, "주문이 완료 되었습니다!", "주문 완료", JOptionPane.INFORMATION_MESSAGE);
				jt.setText("");
				dispose();
			}

		} catch (Exception e1) {
			System.out.println("SQL 문법에 문제가 있는것 같습니다.");
			e1.printStackTrace();
		} finally {
			this.dbclose.close(con, pstmt);
		}

		return count;

	}

	public static void main(String[] args) {

	}
}