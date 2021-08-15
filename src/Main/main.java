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

	
	//�α��� ��  
	JLabel login;
	
	
	//�������� ��
	JLabel notice; 
	
	//���������� ��
	JLabel mypage;
	
	
	//���� �����̳�
	Container c;
	
	//����ȭ�� �� �� �г�
	JPanel up;
	
	
	//������ �޴���
	JMenuBar menu=new JMenuBar();	
	JMenu menu1=new JMenu("ȸ������");
	JMenu menu2=new JMenu("��������");	
	JMenuItem item=new JMenuItem("ȸ�����");
	
	//ȸ����� ��ȸ
	MemberList memberlist=new MemberList();

	main() {
		
		dbopen = new DBOpen();
		dbclose = new DBClose();

	
		//���� ȭ�� ����
	    c=getContentPane();
		
		c.setLayout(null);
		
		this.setResizable(false);
		
		setLocationRelativeTo(null);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Image img = toolkit.getImage("");
		
		setIconImage(img);
		
		//�޴���
		menu.add(menu1);
		menu.add(menu2);
		menu1.add(item);
	    setJMenuBar(menu);
	    menu.setVisible(false);
	    memberlist.setVisible(false);
		
		//���� ���� ��ġ�� �г� ����
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
	    
		
		//�α��� ��ư ����
	    login=new JLabel("LOGIN");
	    
	    login.setForeground(Color.white);
		
		up.add(login);
		
		login.setBounds(1660,10,85,30);
		
		
		//�α���â ����
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
				
				
		       
				//�α���
				JPanel panel1=new JPanel();
				
				panel1.setLayout(null);
				
				panel1.setBackground(Color.white);
				
				panel1.setBounds(400, 300, 350, 600);
				
				JLabel label=new JLabel("�α���");
				
				Font font=new Font("���� ��� Semilight",Font.BOLD,20);
				
				label.setFont(font);
				
				label.setBounds(140,25,70,70);
				
				panel1.add(label);
				
						
							
				JLabel idlabel=new JLabel("���̵�");
				
				Font font1=new Font("���� ��� Semilight",Font.PLAIN,15);
				
				idlabel.setFont(font1);
				
				idlabel.setBounds(25, 100, 60, 60);
				
				panel1.add(idlabel);
				
				
				JTextField idtxt=new JTextField(29);
				
				idtxt.setBounds(87,115,183,30);
				
				panel1.add(idtxt);
				
							
		        
				JLabel pwlabel=new JLabel("��й�ȣ");
				
				pwlabel.setFont(font1);
				
				pwlabel.setBounds(15, 165, 70, 60);
				
				panel1.add(pwlabel);
				
				
				JPasswordField pwtxt=new JPasswordField(28);
				
				pwtxt.setBounds(90,181,183,30);
				
				panel1.add(pwtxt);
				
				
				
				JLabel notice1=new JLabel("�� ����Ʈ�� reCAPTCHA�� ���� ��ȣ�Ǹ�");
				
				Font font3=new Font("���� ��� Semilight",Font.PLAIN,13);
				
				notice1.setFont(font3);
				
				notice1.setBounds(45,230,450,40);
				
				panel1.add(notice1);
				
				
				
		        JLabel notice2=new JLabel("Google ����������޹�ħ �� ���� ����� ����˴ϴ�.");
				
				notice2.setFont(font3);
				
				notice2.setBounds(8,250,450,40);
				
				panel1.add(notice2);
				
				
			    //�α��� ��ư ����
				ImageIcon nextI=new ImageIcon(".//image//loginb.png");
				
				Image nextI2=nextI.getImage();
				
				Image next1= nextI2.getScaledInstance(150,40,Image.SCALE_SMOOTH);
				
				ImageIcon nextI3=new ImageIcon(next1);
				
				JButton login= new JButton(nextI3);
				
				login.setBounds(93, 310, 150, 40);
		        
		        panel1.add(login);
		        
		        
		        //ȸ��&������ �α���
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
							"���̵� �Ǵ� ��й�ȣ�� �Էµ��� �ʾҽ��ϴ�. �ٽ� �Է��� �ּ���.","�α��� ����",JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						else if(member_login==true) {
							JOptionPane.showMessageDialog(null, 
									"ȸ������ �α��� �Ǿ����ϴ�.", "�α��� ����",
									JOptionPane.DEFAULT_OPTION);
							
							loginf.setVisible(false);

							
						}
						
						else if(admin_login == true){
							JOptionPane.showMessageDialog(null,
									"�����ڷ� �α��� �Ǿ����ϴ�.","�α��� ����"
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
									"���̵� �������� �ʰų� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.",
									"�α��� ����",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
		        
		        
		        
		        
			    //���̵� ã�� ���̺�
		        JLabel findid=new JLabel("���̵� ã�� /");
		        
		        Font font2=new Font("���� ��� Semilight",Font.BOLD,15);
			    
			    findid.setFont(font2);
			    
			    findid.setBounds(68, 395, 100, 30);
			    
			    panel1.add(findid);
			    
			    
		        //��й�ȣ ã�� ���̺�
		        JLabel findpw=new JLabel("��й�ȣ ã��");
			    
			    findpw.setFont(font2);
			    
			    findpw.setBounds(168, 395, 100, 30);
			    
			    panel1.add(findpw);
			   
		        
		        c.add(panel1);
		        
		        
		        
		        //ȸ�� ����
		        JPanel panel2=new JPanel();
				
				panel2.setLayout(null);
				
				panel2.setBackground(Color.white);
				
				panel2.setBounds(900, 300, 500, 1000);
				
				JLabel label2=new JLabel("�����ϱ�");
				
				label2.setFont(font);
				
				label2.setBounds(203,23,120,70);
				
				panel2.add(label2);   
				
				JLabel label3=new JLabel("��� �׸��� �ʼ� �Է»����Դϴ�");
				
				Font font4=new Font("���� ��� Semilight",Font.PLAIN,12);
				
				label3.setFont(font4);
				
				label3.setBounds(300,50,200,70);
				
				panel2.add(label3);
				
				JLabel label4=new JLabel("���̵�");
				
				label4.setFont(font1);
				
				label4.setBounds(50,80,100,70);
				
				panel2.add(label4);
				
				id.setBounds(52,140,380,30);
				
				panel2.add(id);
				
				JLabel label5=new JLabel("��й�ȣ");
				
				label5.setFont(font1);
				
				label5.setBounds(50,160,200,70);
				
				panel2.add(label5);
				
				pw.setBounds(52,220,380,30);
				
				panel2.add(pw);
				
		        JLabel label6=new JLabel("�̸�");
				
				label6.setFont(font1);
				
				label6.setBounds(50,240,130,70);
				
				panel2.add(label6);
				
				name.setBounds(52,302,380,30);
				
				panel2.add(name);
				
				JLabel phone=new JLabel("��ȭ��ȣ");
				
				phone.setFont(font1);
				
				phone.setBounds(50,320,130,70);
				
				panel2.add(phone);
				
				phonenum.setBounds(52, 380, 380, 30);
				
				panel2.add(phonenum);
				
				JLabel add=new JLabel("�ּ�");
				
				add.setFont(font1);
				
				add.setBounds(50,400,130,70);

				panel2.add(add);
				
				address.setBounds(52,460,380,30);
				
				panel2.add(address);
		        
				//�����ϱ� ��ư
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
		
		
		
		//�������� ��ư ����
        notice= new JLabel("NOTICE");   
        
        notice.setForeground(Color.white);
		
		up.add(notice);
		
		notice.setBounds(1590,10,90,30);
		
		
		//���������� ��ư ����
        mypage= new JLabel("MY PAGE");   
        
        mypage.setForeground(Color.white);
		
		up.add(mypage);
		
		mypage.setBounds(1510,10,250,30);
		
		
		
		//�귣�� �̹���
		//�߾ӿ� �� �г� ����
        JPanel center=new JPanel();
		
		center.setBounds(0,50,1800,1000);
		
		c.add(center);
		
		center.setLayout(null);
		
		center.setBackground(Color.white);
		
		
		//�귣�� 1(Gucci)		
		ImageIcon gucciI=new ImageIcon(".//image//����.png");
	    
		Image gucciImage=gucciI.getImage();
				    
		Image gucciImage2=gucciImage.getScaledInstance(700,500,Image.SCALE_SMOOTH);
				    
		ImageIcon gucciicon=new ImageIcon(gucciImage2);
		
		JLabel gucci=new JLabel(gucciicon);
	    
		center.add(gucci);
		
		gucci.setBounds(520,150,700,500);
		
		
		//"����" ��ư
		ImageIcon next=new ImageIcon(".//image//����.png");
		
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
				
				ImageIcon pradaI=new ImageIcon(".//image//�����.png");
			    
				Image pradaImage=pradaI.getImage();
						    
				Image pradaImage2=pradaImage.getScaledInstance(700,500,Image.SCALE_SMOOTH);
						    
				ImageIcon pradaicon=new ImageIcon(pradaImage2);
				
				JLabel prada=new JLabel(pradaicon);
			    
				center2.add(prada);
				
				prada.setBounds(520,150,700,500);
				
                ImageIcon backI=new ImageIcon(".//image//����.png");
			    
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
		  
	//2. ȸ�� �α���
	  public boolean Member_Login(String member_id, String member_passwd) {
	    boolean sw = false;
	    int member_login_cnt = 0;

	    try {
	      con = this.dbopen.getConnection();
	      
	      sql = new StringBuffer();
	      sql.append(" SELECT COUNT(*) as member_login_cnt");
	      sql.append(" FROM member");  
	      sql.append(" WHERE member_id=? AND member_passwd=?"); // ?�� ������ ������ ��ü��
	      
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, member_id);
	      pstmt.setString(2, member_passwd);
	      rs = pstmt.executeQuery();
	      rs.next();
	      member_login_cnt = rs.getInt("member_login_cnt");

	      if (member_login_cnt == 1) { // �н����� ��ġ
	        sw = true;
	      } else {
	        sw = false;
	      }

	    } catch (SQLException e) {
	      System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
	      e.printStackTrace();
	    } finally {
	      this.dbclose.close(con, pstmt, rs);      
	    }
	        
	    return sw; 
	  }
		  
	  
     //3. ������ �α���
	  public boolean Admin_Login(String admin_id, String admin_passwd) {
	    boolean sw = false;
	    int admin_login_cnt = 0;

	    try {
	      con = this.dbopen.getConnection();
	      
	      sql = new StringBuffer();
	      sql.append(" SELECT COUNT(*) as admin_login_cnt");
	      sql.append(" FROM admin");  
	      sql.append(" WHERE admin_id=? AND admin_passwd=?"); // ?�� ������ ������ ��ü��
	      
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, admin_id);
	      pstmt.setString(2, admin_passwd);
	      rs = pstmt.executeQuery();
	      rs.next();
	      admin_login_cnt = rs.getInt("admin_login_cnt");

	      if (admin_login_cnt == 1) { // �н����� ��ġ
	        sw = true;
	      } else {
	        sw = false;
	      }

	    } catch (SQLException e) {
	      System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
	      e.printStackTrace();
	    } finally {
	      this.dbclose.close(con, pstmt, rs);      
	    }
	        
	    return sw; 
	  }
		  
	  
	 //ȸ�� ����
	  public int create(MemberVO memberVO) {
		    int count = 0; // ��ϵ� ���ڵ� ����

		    try {
		      con = this.dbopen.getConnection();

		      sql = new StringBuffer();
		      sql.append(
		          "INSERT INTO member(member_no, member_id, member_passwd, member_name, member_tel, member_address, member_rdate)");
		      sql.append("VALUES (member_no_seq.nextval, ?, ?, ?, ?, ?, sysdate)"); // ?�� ������ ������ ��ü��

		      // �� �ؽ�Ʈ�ʵ忡 �Էµ� ���� ������ ����
		      String ID = id.getText().trim();
		      String PW = pw.getText().trim();
		      String NAME = name.getText().trim();
		      String PHONE = phonenum.getText().trim();
		      String ADDRESS = address.getText().trim();

		      // ������ ��ȿ�� �˻�
		      String regExp = "^[0-9]+$";

		      if (ID.length() < 6) {
		        JOptionPane.showMessageDialog(null, "���̵�� 6���� �̻� �Է����ּ���");
		        id.setText("");
		      } else if (ID.length() >= 16) {
		        JOptionPane.showMessageDialog(null, "���̵�� 16���� ���Ϸ� �Է����ּ���");
		        id.setText("");
		      }

		      if (PW.length() < 8) {
		        JOptionPane.showMessageDialog(null, "��й�ȣ�� 8���� �̻� �Է����ּ���");
		        pw.setText("");
		      } else if (PW.length() >= 16) {
		        JOptionPane.showMessageDialog(null, "��й�ȣ�� 16���� ���Ϸ� �Է����ּ���");
		        pw.setText("");
		      }

		      if (NAME.length() == 0) {
		        JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���");
		        name.setText("");
		      }

		      if (!(PHONE.length() == 10 || PHONE.length() == 11)) {
		        JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� 10-11�ڸ��� �����մϴ�");
		        phonenum.setText("");
		      } else if (!(PHONE.matches(regExp))) {
		        JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڸ� �Է��� �� �ֽ��ϴ�");
		        phonenum.setText("");
		      }

		      if (ADDRESS.length() == 0) {
		        JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���");
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
		        JOptionPane.showMessageDialog(null, "ȸ������ ����, �ٽ� �õ��� �ּ���.");
		      } else {
		        count = pstmt.executeUpdate(); // SQL ����

		        JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�");

		        id.setText("");
		        pw.setText("");
		        name.setText("");
		        phonenum.setText("");
		        address.setText("");
		        id.setText("");

		      }

		    } catch (Exception e1) {
		      System.out.println("SQL ������ ������ �ִ°� �����ϴ�.");
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
