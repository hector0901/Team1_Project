package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import Beans.AdminVO;
import Beans.MemberVO;
import DB_Tool.DBClose;
import DB_Tool.DBOpen;


public class main extends JFrame{
	
	MemberVO memberVO=new MemberVO();
	AdminVO adminVO=new AdminVO();
	
	JFrame loginf = new JFrame();
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuffer sql=null;
	
	DBOpen dbopen = null;
	DBClose dbclose = null;
	
	
	JTextField id = new JTextField(200);
	JPasswordField pw = new JPasswordField(200);
	JTextField name = new JTextField(200);
	JTextField phonenum = new JTextField(200);
	JTextField address = new JTextField(200);

	
	//로그인 라벨  
	JLabel login;
	
	
	//공지사항 라벨
	JLabel notice; 
	
	//마이페이지 라벨
	JLabel mypage;
	
	
	//메인 컨테이너
	Container c;
	
	//메인화면 맨 위 패널
	JPanel up;
	
	
	//관리자 메뉴바
	JMenuBar menu=new JMenuBar();	
	JMenu menu1=new JMenu("회원관리");
	JMenu menu2=new JMenu("공지사항");	
	JMenuItem item=new JMenuItem("회원목록");
	
	//회원목록 조회
	MemberList memberlist=new MemberList();

	main() {
		
		dbopen = new DBOpen();
		dbclose = new DBClose();

	
		//메인 화면 구현
	    c=getContentPane();
		
		c.setLayout(null);
		
		this.setResizable(false);
		
		setLocationRelativeTo(null);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Image img = toolkit.getImage("");
		
		setIconImage(img);
		
		//메뉴바
		menu.add(menu1);
		menu.add(menu2);
		menu1.add(item);
	    setJMenuBar(menu);
	    menu.setVisible(false);
	    memberlist.setVisible(false);
		
		//가장 위에 위치할 패널 생성
		up=new JPanel();
		
		up.setBounds(0,0,1800,50);
		
		c.add(up);
		
		up.setLayout(null);
		
		up.setBackground(Color.black);
		
		JLabel label=new JLabel("TITLE");
		
		Font font=new Font("Nixie One",Font.BOLD,30);
		
		label.setFont(font);
		
		label.setForeground(Color.white);
		
		up.add(label);

		label.setBounds(820, 0, 120, 50);
	    
		
		//로그인 버튼 생성
	    login=new JLabel("LOGIN");
	    
	    login.setForeground(Color.white);
		
		up.add(login);
		
		login.setBounds(1660,10,85,30);
		
		
		//로그인창 생성
		login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Container c=loginf.getContentPane();
				
				c.setBackground(Color.white);
				
				loginf.setLayout(null);
				
				loginf.setLocationRelativeTo(null);
				
				loginf.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
				
				
				
				ImageIcon image=new ImageIcon(".//image//loginimage.png");
				
				JLabel imagelabel=new JLabel(image);	
				
				loginf.add(imagelabel);
				
				imagelabel.setBounds(200, 50, 1319, 222);
				
				
		       
				//로그인
				JPanel panel1=new JPanel();
				
				panel1.setLayout(null);
				
				panel1.setBackground(Color.white);
				
				panel1.setBounds(400, 300, 350, 600);
				
				JLabel label=new JLabel("로그인");
				
				Font font=new Font("맑은 고딕 Semilight",Font.BOLD,20);
				
				label.setFont(font);
				
				label.setBounds(140,25,70,70);
				
				panel1.add(label);
				
						
							
				JLabel idlabel=new JLabel("아이디");
				
				Font font1=new Font("맑은 고딕 Semilight",Font.PLAIN,15);
				
				idlabel.setFont(font1);
				
				idlabel.setBounds(25, 100, 60, 60);
				
				panel1.add(idlabel);
				
				
				JTextField idtxt=new JTextField(29);
				
				idtxt.setBounds(87,115,183,30);
				
				panel1.add(idtxt);
				
							
		        
				JLabel pwlabel=new JLabel("비밀번호");
				
				pwlabel.setFont(font1);
				
				pwlabel.setBounds(15, 165, 70, 60);
				
				panel1.add(pwlabel);
				
				
				JPasswordField pwtxt=new JPasswordField(28);
				
				pwtxt.setBounds(90,181,183,30);
				
				panel1.add(pwtxt);
				
				
				
				JLabel notice1=new JLabel("본 사이트는 reCAPTCHA에 의해 보호되며");
				
				Font font3=new Font("맑은 고딕 Semilight",Font.PLAIN,13);
				
				notice1.setFont(font3);
				
				notice1.setBounds(45,230,450,40);
				
				panel1.add(notice1);
				
				
				
		        JLabel notice2=new JLabel("Google 개인정보취급방침 및 서비스 약관이 적용됩니다.");
				
				notice2.setFont(font3);
				
				notice2.setBounds(8,250,450,40);
				
				panel1.add(notice2);
				
				
			    //로그인 버튼 생성
				ImageIcon nextI=new ImageIcon(".//image//loginb.png");
				
				Image nextI2=nextI.getImage();
				
				Image next1= nextI2.getScaledInstance(150,40,Image.SCALE_SMOOTH);
				
				ImageIcon nextI3=new ImageIcon(next1);
				
				JButton login= new JButton(nextI3);
				
				login.setBounds(93, 310, 150, 40);
		        
		        panel1.add(login);
		        
		        
		        //회원&관리자 로그인
		        login.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
						AdminVO adminVO=new AdminVO();
						
						MemberVO memberVO=new MemberVO();
						
						String check_id=idtxt.getText().trim();
						String check_pw=pwtxt.getText().trim();
						
						boolean member_login=Member_Login(check_id, check_pw);
						
						boolean admin_login=Admin_Login(check_id, check_pw);
						
						if(check_id.length()==0 || check_pw.length()==0) {
							JOptionPane.showMessageDialog(null, 
							"아이디 또는 비밀번호가 입력되지 않았습니다. 다시 입력해 주세요.","로그인 오류",JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						else if(member_login==true) {
							JOptionPane.showMessageDialog(null, 
									"회원으로 로그인 되었습니다.", "로그인 성공",
									JOptionPane.DEFAULT_OPTION);
							
							loginf.setVisible(false);

							
						}
						
						else if(admin_login == true){
							JOptionPane.showMessageDialog(null,
									"관리자로 로그인 되었습니다.","로그인 성공"
									,JOptionPane.DEFAULT_OPTION);
							
							loginf.setVisible(false);
							menu.setVisible(true);
							
							item.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									memberlist.setVisible(true);
								}
							});
						}
					
						else {
							JOptionPane.showMessageDialog(null, 
									"아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.",
									"로그인 오류",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
		        
		        
		        
		        
			    //아이디 찾기 레이블
		        JLabel findid=new JLabel("아이디 찾기 /");
		        
		        Font font2=new Font("맑은 고딕 Semilight",Font.BOLD,15);
			    
			    findid.setFont(font2);
			    
			    findid.setBounds(68, 395, 100, 30);
			    
			    panel1.add(findid);
			    
			    
		        //비밀번호 찾기 레이블
		        JLabel findpw=new JLabel("비밀번호 찾기");
			    
			    findpw.setFont(font2);
			    
			    findpw.setBounds(168, 395, 100, 30);
			    
			    panel1.add(findpw);
			   
		        
		        c.add(panel1);
		        
		        
		        
		        //회원 가입
		        JPanel panel2=new JPanel();
				
				panel2.setLayout(null);
				
				panel2.setBackground(Color.white);
				
				panel2.setBounds(900, 300, 500, 1000);
				
				JLabel label2=new JLabel("가입하기");
				
				label2.setFont(font);
				
				label2.setBounds(203,23,120,70);
				
				panel2.add(label2);   
				
				JLabel label3=new JLabel("모든 항목은 필수 입력사항입니다");
				
				Font font4=new Font("맑은 고딕 Semilight",Font.PLAIN,12);
				
				label3.setFont(font4);
				
				label3.setBounds(300,50,200,70);
				
				panel2.add(label3);
				
				JLabel label4=new JLabel("아이디");
				
				label4.setFont(font1);
				
				label4.setBounds(50,80,100,70);
				
				panel2.add(label4);
				
				id.setBounds(52,140,380,30);
				
				panel2.add(id);
				
				JLabel label5=new JLabel("비밀번호");
				
				label5.setFont(font1);
				
				label5.setBounds(50,160,200,70);
				
				panel2.add(label5);
				
				pw.setBounds(52,220,380,30);
				
				panel2.add(pw);
				
		        JLabel label6=new JLabel("이름");
				
				label6.setFont(font1);
				
				label6.setBounds(50,240,130,70);
				
				panel2.add(label6);
				
				name.setBounds(52,302,380,30);
				
				panel2.add(name);
				
				JLabel phone=new JLabel("전화번호");
				
				phone.setFont(font1);
				
				phone.setBounds(50,320,130,70);
				
				panel2.add(phone);
				
				phonenum.setBounds(52, 380, 380, 30);
				
				panel2.add(phonenum);
				
				JLabel add=new JLabel("주소");
				
				add.setFont(font1);
				
				add.setBounds(50,400,130,70);

				panel2.add(add);
				
				address.setBounds(52,460,380,30);
				
				panel2.add(address);
		        
				//가입하기 버튼
		        ImageIcon enterI=new ImageIcon(".//image//enter.png");
				
				Image enterI2=enterI.getImage();
				
				Image enter1= enterI2.getScaledInstance(130,40,Image.SCALE_SMOOTH);
				
				ImageIcon enterI3=new ImageIcon(enter1);
				
				JButton enter= new JButton(enterI3);
				
				enter.setBounds(178, 520, 130, 40);
		        
		        panel2.add(enter);
				
		        c.add(panel2);
		        
		        enter.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		              create(memberVO);
		              
		            }
		            
		          });

		        loginf.setVisible(true);
			}
		});
		
		
		
		//공지사항 버튼 생성
        notice= new JLabel("NOTICE");   
        
        notice.setForeground(Color.white);
		
		up.add(notice);
		
		notice.setBounds(1590,10,90,30);
		
		
		//마이페이지 버튼 생성
        mypage= new JLabel("MY PAGE");   
        
        mypage.setForeground(Color.white);
		
		up.add(mypage);
		
		mypage.setBounds(1510,10,250,30);
		
		
		
		//브랜드 이미지
		//중앙에 들어갈 패널 생성
        JPanel center=new JPanel();
		
		center.setBounds(0,50,1800,1000);
		
		c.add(center);
		
		center.setLayout(null);
		
		center.setBackground(Color.white);
		
		
		//브랜드 1(Gucci)		
		ImageIcon gucciI=new ImageIcon(".//image//구찌.png");
	    
		Image gucciImage=gucciI.getImage();
				    
		Image gucciImage2=gucciImage.getScaledInstance(700,500,Image.SCALE_SMOOTH);
				    
		ImageIcon gucciicon=new ImageIcon(gucciImage2);
		
		JLabel gucci=new JLabel(gucciicon);
	    
		center.add(gucci);
		
		gucci.setBounds(520,150,700,500);
		
		
		//"다음" 버튼
		ImageIcon next=new ImageIcon(".//image//다음.png");
		
		Image nextImage=next.getImage();
	    
		Image nextImage2=nextImage.getScaledInstance(80,70,Image.SCALE_SMOOTH);
				    
		ImageIcon nexticon=new ImageIcon(nextImage2);
		
		JButton nextbutton=new JButton(nexticon);
		
		nextbutton.setBorderPainted(false);   
		nextbutton.setContentAreaFilled(false);   
		nextbutton.setFocusPainted(false);  
		
		center.add(nextbutton);
		
		nextbutton.setBounds(1300, 350, 80, 70);
		
		
		nextbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				center.setVisible(false);
				
				JPanel center2=new JPanel();
				
				center2.setBounds(0,50,1800,1000);
				
				c.add(center2);
				
				center2.setLayout(null);
				
				center2.setBackground(Color.white);
				
				ImageIcon pradaI=new ImageIcon(".//image//프라다.png");
			    
				Image pradaImage=pradaI.getImage();
						    
				Image pradaImage2=pradaImage.getScaledInstance(700,500,Image.SCALE_SMOOTH);
						    
				ImageIcon pradaicon=new ImageIcon(pradaImage2);
				
				JLabel prada=new JLabel(pradaicon);
			    
				center2.add(prada);
				
				prada.setBounds(520,150,700,500);
				
                ImageIcon backI=new ImageIcon(".//image//이전.png");
			    
				Image backImage=backI.getImage();
						    
				Image backImage2=backImage.getScaledInstance(80,70,Image.SCALE_SMOOTH);
						    
				ImageIcon backicon=new ImageIcon(backImage2);
				
				JButton backbutton=new JButton(backicon);
				
				backbutton.setBorderPainted(false);   
				backbutton.setContentAreaFilled(false);   
				backbutton.setFocusPainted(false);  
				
				center2.add(backbutton);
				backbutton.setBounds(355,350,80,70);
				
				
				backbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						center2.setVisible(false);
						
						center.setVisible(true);
						
					}
				});
				
			}
		});
		
		setVisible(true);
		
		   
	}
		  
	//2. 회원 로그인
	  public boolean Member_Login(String member_id, String member_passwd) {
	    boolean sw = false;
	    int member_login_cnt = 0;

	    try {
	      con = this.dbopen.getConnection();
	      
	      sql = new StringBuffer();
	      sql.append(" SELECT COUNT(*) as member_login_cnt");
	      sql.append(" FROM member");  
	      sql.append(" WHERE member_id=? AND member_passwd=?"); // ?는 변수의 값으로 대체됨
	      
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, member_id);
	      pstmt.setString(2, member_passwd);
	      rs = pstmt.executeQuery();
	      rs.next();
	      member_login_cnt = rs.getInt("member_login_cnt");

	      if (member_login_cnt == 1) { // 패스워드 일치
	        sw = true;
	      } else {
	        sw = false;
	      }

	    } catch (SQLException e) {
	      System.out.println("SQL 문법에 문제가 있는것 같습니다.");
	      e.printStackTrace();
	    } finally {
	      this.dbclose.close(con, pstmt, rs);      
	    }
	        
	    return sw; 
	  }
		  
	  
     //3. 관리자 로그인
	  public boolean Admin_Login(String admin_id, String admin_passwd) {
	    boolean sw = false;
	    int admin_login_cnt = 0;

	    try {
	      con = this.dbopen.getConnection();
	      
	      sql = new StringBuffer();
	      sql.append(" SELECT COUNT(*) as admin_login_cnt");
	      sql.append(" FROM admin");  
	      sql.append(" WHERE admin_id=? AND admin_passwd=?"); // ?는 변수의 값으로 대체됨
	      
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, admin_id);
	      pstmt.setString(2, admin_passwd);
	      rs = pstmt.executeQuery();
	      rs.next();
	      admin_login_cnt = rs.getInt("admin_login_cnt");

	      if (admin_login_cnt == 1) { // 패스워드 일치
	        sw = true;
	      } else {
	        sw = false;
	      }

	    } catch (SQLException e) {
	      System.out.println("SQL 문법에 문제가 있는것 같습니다.");
	      e.printStackTrace();
	    } finally {
	      this.dbclose.close(con, pstmt, rs);      
	    }
	        
	    return sw; 
	  }
		  
	  
	 //회원 가입
	  public int create(MemberVO memberVO) {
		    int count = 0; // 등록된 레코드 갯수

		    try {
		      con = this.dbopen.getConnection();

		      sql = new StringBuffer();
		      sql.append(
		          "INSERT INTO member(member_no, member_id, member_passwd, member_name, member_tel, member_address, member_rdate)");
		      sql.append("VALUES (member_no_seq.nextval, ?, ?, ?, ?, ?, sysdate)"); // ?는 변수의 값으로 대체됨

		      // 각 텍스트필드에 입력된 값을 변수에 저장
		      String ID = id.getText().trim();
		      String PW = pw.getText().trim();
		      String NAME = name.getText().trim();
		      String PHONE = phonenum.getText().trim();
		      String ADDRESS = address.getText().trim();

		      // 변수별 유효성 검사
		      String regExp = "^[0-9]+$";

		      if (ID.length() < 6) {
		        JOptionPane.showMessageDialog(null, "아이디는 6글자 이상 입력해주세요");
		        id.setText("");
		      } else if (ID.length() >= 16) {
		        JOptionPane.showMessageDialog(null, "아이디는 16글자 이하로 입력해주세요");
		        id.setText("");
		      }

		      if (PW.length() < 8) {
		        JOptionPane.showMessageDialog(null, "비밀번호는 8글자 이상 입력해주세요");
		        pw.setText("");
		      } else if (PW.length() >= 16) {
		        JOptionPane.showMessageDialog(null, "비밀번호는 16글자 이하로 입력해주세요");
		        pw.setText("");
		      }

		      if (NAME.length() == 0) {
		        JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
		        name.setText("");
		      }

		      if (!(PHONE.length() == 10 || PHONE.length() == 11)) {
		        JOptionPane.showMessageDialog(null, "전화번호는 10-11자리만 가능합니다");
		        phonenum.setText("");
		      } else if (!(PHONE.matches(regExp))) {
		        JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력할 수 있습니다");
		        phonenum.setText("");
		      }

		      if (ADDRESS.length() == 0) {
		        JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
		        address.setText("");
		      }

		      pstmt = con.prepareStatement(sql.toString());
		      pstmt.setString(1, ID);
		      pstmt.setString(2, PW);
		      pstmt.setString(3, NAME);
		      pstmt.setString(4, PHONE);
		      pstmt.setString(5, ADDRESS);

		      if (ID.length() < 6 || ID.length() >= 16 || PW.length() >= 16 || PW.length() < 8 || NAME.length() == 0
		          || !(PHONE.length() == 10 || PHONE.length() == 11) || ADDRESS.length() == 0) {
		        JOptionPane.showMessageDialog(null, "회원가입 실패, 다시 시도해 주세요.");
		      } else {
		        count = pstmt.executeUpdate(); // SQL 실행

		        JOptionPane.showMessageDialog(null, "회원가입 완료");

		        id.setText("");
		        pw.setText("");
		        name.setText("");
		        phonenum.setText("");
		        address.setText("");
		        id.setText("");

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
		// TODO Auto-generated method stub

		
		new main();
	}

}
