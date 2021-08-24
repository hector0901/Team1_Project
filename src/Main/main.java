package Main;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Beans.AdminVO;
import Beans.MemberVO;
import DB_Tool.DBClose;
import DB_Tool.DBOpen;
import Member.MemberList;
import Member.My;
import Notice.Notice;
import Notice.Notice_customer;
import Product.GucciProduct;
import Product.GucciProduct2;
import Product.GucciProduct3;
import Product.PradaProduct;
import Product.PradaProduct2;
import Product.PradaProduct3;

public class main extends JFrame {

  MemberVO memberVO = new MemberVO();
  AdminVO adminVO = new AdminVO();

  JFrame loginf = new JFrame();

  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  StringBuffer sql = null;

  DBOpen dbopen = null;
  DBClose dbclose = null;

  JTextField id = new JTextField(200);
  JPasswordField pw = new JPasswordField(200);
  JTextField name = new JTextField(200);
  JTextField phonenum = new JTextField(200);
  JTextField address = new JTextField(200);

  // 로그인 라벨
  JLabel login = new JLabel();

  // 공지사항 라벨
  JLabel notice;

  // 마이페이지 라벨
  JLabel mypage;

  // 메인 컨테이너
  Container c;

  // 메인화면 맨 위 패널
  JPanel up;

  // 관리자 메뉴바
  JMenuBar menu = new JMenuBar();
  JMenu menu1 = new JMenu("회원관리");
  JMenu menu2 = new JMenu("공지사항");
  JMenuItem item = new JMenuItem("회원목록");
  JMenuItem item2 = new JMenuItem("공지사항");

  // 회원목록 조회
  MemberList memberlist = new MemberList();

  // 아이디 패스워드 찾기
  JTextField namefield = new JTextField();
  JTextField telfield = new JTextField();

  // 구찌 상품 페이지
  GucciProduct gucciproduct1 = new GucciProduct();
  GucciProduct2 gucciproduct2 = new GucciProduct2();
  GucciProduct3 gucciproduct3 = new GucciProduct3();

  // 프라다 상품 페이지
  PradaProduct pradaproduct1 = new PradaProduct();
  PradaProduct2 pradaproduct2 = new PradaProduct2();
  PradaProduct3 pradaproduct3 = new PradaProduct3();

  // 마이페이지
  My my = new My();

  // 공지사항
  Notice_customer notice_customer = new Notice_customer();
  Notice notice_function = new Notice();
  

  public main() {

    dbopen = new DBOpen();
    dbclose = new DBClose();

    // 메인 화면 구현
    c = getContentPane();

    c.setLayout(null);

    this.setResizable(false);

    setLocationRelativeTo(null);

    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    Image img = toolkit.getImage("");

    setIconImage(img);

    // 메뉴바
    menu.add(menu1);
    menu.add(menu2);
    menu1.add(item);
    menu2.add(item2);
    setJMenuBar(menu);
    menu.setVisible(false);
    memberlist.setVisible(false);

    // 구찌 상품 페이지
    gucciproduct1.setVisible(false);
    gucciproduct2.setVisible(false);
    gucciproduct3.setVisible(false);

    // 프라다 상품 페이지
    pradaproduct1.setVisible(false);
    pradaproduct2.setVisible(false);
    pradaproduct3.setVisible(false);

    // 마이페이지&공지사항
    my.setVisible(false);
    notice_customer.setVisible(false);

    // 가장 위에 위치할 패널 생성
    up = new JPanel();

    up.setBounds(0, 0, 1800, 50);

    c.add(up);

    up.setLayout(null);

    up.setBackground(Color.black);

    JLabel label = new JLabel("");

    Font font = new Font("Nixie One", Font.BOLD, 30);

    label.setFont(font);

    label.setForeground(Color.white);

    up.add(label);

    label.setBounds(820, 0, 120, 50);

    // 로그인 버튼 생성
    // login=new JLabel("LOGIN");
    login = new JLabel("LOGIN");

    login.setForeground(Color.white);

    up.add(login);

    login.setBounds(1660, 10, 85, 30);

    // 로그인창 생성
    login.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        Container c = loginf.getContentPane();

        c.setBackground(Color.white);

        loginf.setLayout(null);

        loginf.setLocationRelativeTo(null);

        loginf.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        ImageIcon image = new ImageIcon(".//image//loginimage.png");

        JLabel imagelabel = new JLabel(image);

        loginf.add(imagelabel);

        imagelabel.setBounds(200, 50, 1319, 222);

        // 로그인
        JPanel panel1 = new JPanel();

        panel1.setLayout(null);

        panel1.setBackground(Color.white);

        panel1.setBounds(400, 300, 350, 600);

        JLabel label = new JLabel("로그인");

        Font font = new Font("맑은 고딕 Semilight", Font.BOLD, 20);

        label.setFont(font);

        label.setBounds(140, 25, 70, 70);

        panel1.add(label);

        JLabel idlabel = new JLabel("아이디");

        Font font1 = new Font("맑은 고딕 Semilight", Font.PLAIN, 15);

        idlabel.setFont(font1);

        idlabel.setBounds(25, 100, 60, 60);

        panel1.add(idlabel);

        JTextField idtxt = new JTextField(29);

        idtxt.setBounds(87, 115, 183, 30);

        panel1.add(idtxt);

        JLabel pwlabel = new JLabel("비밀번호");

        pwlabel.setFont(font1);

        pwlabel.setBounds(15, 165, 70, 60);

        panel1.add(pwlabel);

        JPasswordField pwtxt = new JPasswordField(28);

        pwtxt.setBounds(90, 181, 183, 30);

        panel1.add(pwtxt);

        JLabel notice1 = new JLabel("본 사이트는 reCAPTCHA에 의해 보호되며");

        Font font3 = new Font("맑은 고딕 Semilight", Font.PLAIN, 13);

        notice1.setFont(font3);

        notice1.setBounds(45, 230, 450, 40);

        panel1.add(notice1);

        JLabel notice2 = new JLabel("Google 개인정보취급방침 및 서비스 약관이 적용됩니다.");

        notice2.setFont(font3);

        notice2.setBounds(8, 250, 450, 40);

        panel1.add(notice2);

        // 로그인 버튼 생성
        ImageIcon nextI = new ImageIcon(".//image//loginb.png");

        Image nextI2 = nextI.getImage();

        Image next1 = nextI2.getScaledInstance(150, 40, Image.SCALE_SMOOTH);

        ImageIcon nextI3 = new ImageIcon(next1);

        JButton loginbutton = new JButton(nextI3);

        loginbutton.setBounds(93, 310, 150, 40);

        panel1.add(loginbutton);

        // 회원&관리자 로그인
        loginbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            AdminVO adminVO = new AdminVO();

            MemberVO memberVO = new MemberVO();

            String check_id = idtxt.getText().trim();
            String check_pw = pwtxt.getText().trim();

            boolean member_login = Member_Login(check_id, check_pw);
            boolean admin_login = Admin_Login(check_id, check_pw);

            if (check_id.length() == 0 || check_pw.length() == 0) {
              JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 입력되지 않았습니다. 다시 입력해 주세요.", "로그인 오류",
                  JOptionPane.WARNING_MESSAGE);
              return;
            }

            else if (member_login == true) {
              JOptionPane.showMessageDialog(null, "회원으로 로그인 되었습니다.", "로그인 성공", JOptionPane.DEFAULT_OPTION);

              System.out.println("회원번호: " + find_member_no(check_id, check_pw).getMember_no());
              
              loginf.setVisible(false);
              mypage.setForeground(Color.white);

              // 로그아웃
              login.setText("EXIT");

              login.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                  System.exit(0);
                }
              });
            }

            else if (admin_login == true) {
              JOptionPane.showMessageDialog(null, "관리자로 로그인 되었습니다.", "로그인 성공", JOptionPane.DEFAULT_OPTION);

              loginf.setVisible(false);
              menu.setVisible(true);

              // 로그아웃
              login.setText("EXIT");

              login.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                  System.exit(0);
                }
              });

              item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  memberlist.setVisible(true);
                }
              });

              // 관리자용 공지사항
              item2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  notice_function.setVisible(true);
                }
              });
            }

            else {
              JOptionPane.showMessageDialog(null, "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.", "로그인 오류",
                  JOptionPane.WARNING_MESSAGE);
            }

          }
        });

        // 아이디/비밀번호 찾기 레이블
        JLabel findid = new JLabel("아이디 / 비밀번호 찾기");

        Font font2 = new Font("맑은 고딕 Semilight", Font.BOLD, 15);

        findid.setFont(font2);

        findid.setBounds(82, 395, 180, 30);

        panel1.add(findid);

        c.add(panel1);

        findid.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            JFrame findidframe = new JFrame();

            findidframe.setTitle("아이디&비밀번호 찾기");

            Container con = findidframe.getContentPane();

            con.setBackground(Color.white);

            con.setLayout(null);

            findidframe.setResizable(false);

            findidframe.setSize(500, 500);

            findidframe.setVisible(true);

            JPanel title = new JPanel();

            title.setLayout(null);

            title.setBounds(0, 0, 500, 50);

            title.setBackground(Color.black);

            JLabel label = new JLabel("아이디 & 비밀번호 찾기");

            Font font = new Font("맑은 고딕 Semilight", Font.BOLD, 14);

            label.setForeground(Color.white);

            label.setFont(font);

            label.setBounds(170, 0, 160, 50);

            title.add(label);

            findidframe.add(title);

            JLabel name = new JLabel("이름");
            Font font2 = new Font("맑은 고딕 Semilight", Font.PLAIN, 18);
            name.setFont(font2);
            con.add(name);
            name.setBounds(140, 100, 80, 50);

            con.add(namefield);
            namefield.setBounds(135, 155, 220, 30);

            JLabel tel = new JLabel("연락처");
            tel.setFont(font2);
            con.add(tel);
            tel.setBounds(140, 200, 80, 50);

            con.add(telfield);
            telfield.setBounds(135, 255, 220, 30);

            ImageIcon findI = new ImageIcon(".//image//찾기.png");
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
                String check_name = namefield.getText().trim();
                String check_tel = telfield.getText().trim();
                find_id_pw(check_name, check_tel);

              }
            });

          }
        });

        // 회원 가입
        JPanel panel2 = new JPanel();

        panel2.setLayout(null);

        panel2.setBackground(Color.white);

        panel2.setBounds(900, 300, 500, 1000);

        JLabel label2 = new JLabel("가입하기");

        label2.setFont(font);

        label2.setBounds(203, 23, 120, 70);

        panel2.add(label2);

        JLabel label3 = new JLabel("모든 항목은 필수 입력사항입니다");

        Font font4 = new Font("맑은 고딕 Semilight", Font.PLAIN, 12);

        label3.setFont(font4);

        label3.setBounds(300, 50, 200, 70);

        panel2.add(label3);

        JLabel label4 = new JLabel("아이디");

        label4.setFont(font1);

        label4.setBounds(50, 80, 100, 70);

        panel2.add(label4);

        id.setBounds(52, 140, 380, 30);

        panel2.add(id);

        JLabel label5 = new JLabel("비밀번호");

        label5.setFont(font1);

        label5.setBounds(50, 160, 200, 70);

        panel2.add(label5);

        pw.setBounds(52, 220, 380, 30);

        panel2.add(pw);

        JLabel label6 = new JLabel("이름");

        label6.setFont(font1);

        label6.setBounds(50, 240, 130, 70);

        panel2.add(label6);

        name.setBounds(52, 302, 380, 30);

        panel2.add(name);

        JLabel phone = new JLabel("전화번호");

        phone.setFont(font1);

        phone.setBounds(50, 320, 130, 70);

        panel2.add(phone);

        phonenum.setBounds(52, 380, 380, 30);

        panel2.add(phonenum);

        JLabel add = new JLabel("주소");

        add.setFont(font1);

        add.setBounds(50, 400, 130, 70);

        panel2.add(add);

        address.setBounds(52, 460, 380, 30);

        panel2.add(address);

        // 가입하기 버튼
        ImageIcon enterI = new ImageIcon(".//image//enter.png");

        Image enterI2 = enterI.getImage();

        Image enter1 = enterI2.getScaledInstance(130, 40, Image.SCALE_SMOOTH);

        ImageIcon enterI3 = new ImageIcon(enter1);

        JButton enter = new JButton(enterI3);

        enter.setBounds(178, 520, 130, 40);

        panel2.add(enter);

        c.add(panel2);

        enter.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             String ID = id.getText().trim();
            id_check(ID);
            
            create(memberVO);
          }

        });

        loginf.setVisible(true);
      }
    });

    // 공지사항 버튼 생성
    notice = new JLabel("NOTICE");

    notice.setForeground(Color.white);

    up.add(notice);

    notice.setBounds(1590, 10, 90, 30);

    notice.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        notice_customer.setVisible(true);
      }
    });

    // 마이페이지 버튼 생성
    mypage = new JLabel("MY PAGE");

    mypage.setForeground(Color.BLACK);

    up.add(mypage);

    mypage.setBounds(1510, 10, 250, 30);

    // 마이페이지창
    mypage.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        my.setVisible(true);
      }
    });

    // 브랜드 이미지
    // 중앙에 들어갈 패널 생성
    JPanel center = new JPanel();

    center.setBounds(0, 50, 1800, 1000);

    c.add(center);

    center.setLayout(null);

    center.setBackground(Color.white);

    // 브랜드 1(Gucci)
    ImageIcon gucciI = new ImageIcon(".//image//구찌.png");

    Image gucciImage = gucciI.getImage();

    Image gucciImage2 = gucciImage.getScaledInstance(700, 500, Image.SCALE_SMOOTH);

    ImageIcon gucciicon = new ImageIcon(gucciImage2);

    JLabel gucci = new JLabel(gucciicon);

    center.add(gucci);

    gucci.setBounds(520, 150, 700, 500);

    // 브랜드 전체 상품 창
    gucci.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {

        JFrame gucciframe = new JFrame();

        gucciframe.setLayout(null);

        gucciframe.setResizable(false);

        gucciframe.setLocationRelativeTo(null);

        gucciframe.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        Container guccicon = gucciframe.getContentPane();

        /*
         * ImageIcon [] Gucci= { new ImageIcon(".//image//구찌 라벨 디테일의 스웨트셔츠.jpeg"), new
         * ImageIcon(".//image//저지 조깅 팬츠.jpg"), new
         * ImageIcon(".//image//구찌 로고 롸이톤 레더 스니커즈.jpeg")};
         * 
         * JLabel GucciImage[]= { new JLabel(Gucci[0]), new JLabel(Gucci[1]), new
         * JLabel(Gucci[2])};
         */

        JPanel titlep = new JPanel();

        guccicon.add(titlep);

        titlep.setBounds(0, 0, 1800, 50);

        titlep.setLayout(null);

        titlep.setBackground(Color.black);

        JLabel GucciLabel = new JLabel("GUCCI");

        Font font = new Font("Nixie One", Font.BOLD, 30);

        GucciLabel.setFont(font);

        GucciLabel.setForeground(Color.white);

        titlep.add(GucciLabel);

        GucciLabel.setBounds(820, 0, 120, 50);

        JPanel gcenter = new JPanel();

        gcenter.setBounds(0, 50, 1800, 1000);

        gcenter.setLayout(null);

        gcenter.setBackground(Color.white);

        guccicon.add(gcenter);

        ImageIcon gucciI1 = new ImageIcon(".//image//오버사이즈 케이블 니트 가디건.png");

        ImageIcon gucciI2 = new ImageIcon(".//image//스퀘어 G 체크 트위드 드레스.png");

        ImageIcon gucciI3 = new ImageIcon(".//image//구찌 스몰 마틀라세 숄더백.png");

        // 상품 1
        JLabel gucci1 = new JLabel(gucciI1);

        JLabel gucci1name = new JLabel("오버사이즈 케이블 니트 가디건");

        Font font5 = new Font("맑은 고딕 Semilight", Font.BOLD, 18);

        gucci1name.setFont(font5);

        gcenter.add(gucci1name);

        gucci1name.setBounds(190, 750, 260, 30);

        gcenter.add(gucci1);

        gucci1.setBounds(120, 180, 400, 400);

        // 구찌 첫번째 상품 상세페이지
        gucci1.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            gucciproduct1.setVisible(true);
          }
        });

        // 상품 2
        JLabel gucci2 = new JLabel(gucciI2);

        JLabel gucci2name = new JLabel("스퀘어 G 체크 트위드 드레스");

        gucci2name.setFont(font5);

        gcenter.add(gucci2name);

        gcenter.add(gucci2);

        gucci2name.setBounds(738, 750, 260, 30);

        gucci2.setBounds(670, 180, 400, 400);

        // 구찌 두번째 상품 상세페이지
        gucci2.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            gucciproduct2.setVisible(true);
          }
        });

        // 상품 3
        JLabel gucci3 = new JLabel(gucciI3);

        JLabel gucci3name = new JLabel("구찌 스몰 마틀라세 숄더백");

        gucci3name.setFont(font5);

        gcenter.add(gucci3name);

        gucci3name.setBounds(1310, 750, 260, 30);

        gcenter.add(gucci3);

        gucci3.setBounds(1200, 230, 477, 329);

        // 구찌 세번째 상품 상세페이지
        gucci3.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            gucciproduct3.setVisible(true);
          }
        });

        guccicon.add(gcenter);

        gucciframe.setVisible(true);

      }
    });

    // "다음" 버튼
    ImageIcon next = new ImageIcon(".//image//다음.png");

    Image nextImage = next.getImage();

    Image nextImage2 = nextImage.getScaledInstance(80, 70, Image.SCALE_SMOOTH);

    ImageIcon nexticon = new ImageIcon(nextImage2);

    JButton nextbutton = new JButton(nexticon);

    nextbutton.setBorderPainted(false);
    nextbutton.setContentAreaFilled(false);
    nextbutton.setFocusPainted(false);

    center.add(nextbutton);

    nextbutton.setBounds(1300, 350, 80, 70);

    nextbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        center.setVisible(false);

        JPanel center2 = new JPanel();

        center2.setBounds(0, 50, 1800, 1000);

        c.add(center2);

        center2.setLayout(null);

        center2.setBackground(Color.white);

        // "프라다" 브랜드
        ImageIcon pradaI = new ImageIcon(".//image//프라다.png");

        Image pradaImage = pradaI.getImage();

        Image pradaImage2 = pradaImage.getScaledInstance(700, 500, Image.SCALE_SMOOTH);

        ImageIcon pradaicon = new ImageIcon(pradaImage2);

        JLabel prada = new JLabel(pradaicon);

        center2.add(prada);

        prada.setBounds(520, 150, 700, 500);

        // "프라다" 상품 페이지
        prada.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {

            JFrame pradaframe = new JFrame();

            pradaframe.setLayout(null);

            pradaframe.setResizable(false);

            pradaframe.setLocationRelativeTo(null);

            pradaframe.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

            Container pradacon = pradaframe.getContentPane();

            JPanel titlep2 = new JPanel();

            pradacon.add(titlep2);

            titlep2.setBounds(0, 0, 1800, 50);

            titlep2.setLayout(null);

            titlep2.setBackground(Color.black);

            JLabel pradaLabel = new JLabel("PRADA");

            Font font = new Font("Nixie One", Font.BOLD, 30);

            pradaLabel.setFont(font);

            pradaLabel.setForeground(Color.white);

            titlep2.add(pradaLabel);

            pradaLabel.setBounds(820, 0, 120, 50);

            JPanel pcenter = new JPanel();

            pcenter.setBounds(0, 50, 1800, 1000);

            pcenter.setLayout(null);

            pcenter.setBackground(Color.white);

            pradacon.add(pcenter);

            ImageIcon pradaI1 = new ImageIcon(".//image//프라다 플라쥬 위커 및 캔버스 버킷백.png");

            ImageIcon pradaI2 = new ImageIcon(".//image//클레오 브러시드 가죽 숄더.png");

            ImageIcon pradaI3 = new ImageIcon(".//image//크롭 셰틀랜드 울 카디건.png");

            // 상품 1
            JLabel prada1 = new JLabel(pradaI1);

            JLabel prada1name = new JLabel("프라다 플라쥬 위커 및 캔버스 버킷백");

            Font font5 = new Font("맑은 고딕 Semilight", Font.BOLD, 18);

            prada1name.setFont(font5);

            pcenter.add(prada1name);

            prada1name.setBounds(170, 750, 320, 30);

            pcenter.add(prada1);

            prada1.setBounds(120, 180, 400, 400);

            // 프라다 첫번째 상품 상세페이지
            prada1.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                pradaproduct1.setVisible(true);
              }
            });

            // 상품 2
            JLabel prada2 = new JLabel(pradaI2);

            JLabel prada2name = new JLabel("클레오 브러시드 가죽 숄더");

            prada2name.setFont(font5);

            pcenter.add(prada2name);

            pcenter.add(prada2);

            prada2name.setBounds(763, 750, 260, 30);

            prada2.setBounds(670, 180, 400, 400);

            // 프라다 두번째 상품 상세페이지
            prada2.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                pradaproduct2.setVisible(true);
              }
            });

            // 상품 3
            JLabel prada3 = new JLabel(pradaI3);

            JLabel prada3name = new JLabel("크롭 셰틀랜드 울 카디건");

            prada3name.setFont(font5);

            pcenter.add(prada3name);

            prada3name.setBounds(1350, 750, 260, 30);

            pcenter.add(prada3);

            prada3.setBounds(1250, 225, 400, 400);

            // 구찌 세번째 상품 상세페이지
            prada3.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                pradaproduct3.setVisible(true);
              }
            });

            pradacon.add(pcenter);

            pradaframe.setVisible(true);

          }
        });

        ImageIcon backI = new ImageIcon(".//image//이전.png");

        Image backImage = backI.getImage();

        Image backImage2 = backImage.getScaledInstance(80, 70, Image.SCALE_SMOOTH);

        ImageIcon backicon = new ImageIcon(backImage2);

        JButton backbutton = new JButton(backicon);

        backbutton.setBorderPainted(false);
        backbutton.setContentAreaFilled(false);
        backbutton.setFocusPainted(false);

        center2.add(backbutton);
        backbutton.setBounds(355, 350, 80, 70);

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

  // 2. 회원 로그인
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

  // 3. 관리자 로그인
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

  // 회원 가입
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

      }

    } catch (Exception e1) {
      System.out.println("SQL 문법에 문제가 있는것 같습니다!");
      e1.printStackTrace();
    } finally {
      this.dbclose.close(con, pstmt);
    }

    return count;

  }

  /**
   * 회원의 이름과 전화번호를 입력하면 ID 와 PW 출력
   * @param member_name
   * @param member_address
   * @return
   */
  public MemberVO find_id_pw(String member_name, String member_tel) {

    MemberVO memberVO = null;

    try {
      con = this.dbopen.getConnection();

      sql = new StringBuffer();
      sql.append(" SELECT member_id, member_passwd");
      sql.append(" FROM member");
      sql.append(" WHERE member_name = ? AND member_tel = ?");

      String NAME = namefield.getText().trim();
      String PHONE = telfield.getText().trim();

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, NAME);
      pstmt.setString(2, PHONE);
      rs = pstmt.executeQuery(); // SQL 실행

      if (rs.next() == true) { // 첫번째 레코드 -> 마지막 레코드로 이동    
         memberVO = new MemberVO();
        memberVO.setMember_id(rs.getString("member_id"));
        memberVO.setMember_passwd(rs.getString("member_passwd"));
        JOptionPane.showMessageDialog(null, "ID: " + memberVO.getMember_id() + "\nPW: " + memberVO.getMember_passwd());
      } else {
        JOptionPane.showMessageDialog(null, "이름이나 전화번호가 일치하지 않습니다. \n 다시 시도해주세요");
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
   * ID 중복 체크
   * @param member_id
   * @return
   */
  public boolean id_check(String member_id) {
     boolean result = false;
     int count = 0;
     
      try {
       con = this.dbopen.getConnection();

       sql = new StringBuffer();
       sql.append(" SELECT count(*) as double_id ");
       sql.append(" FROM member");
       sql.append(" WHERE member_id = ?");
       
       pstmt = con.prepareStatement(sql.toString());
       pstmt.setString(1, member_id);
       rs = pstmt.executeQuery(); // SQL 실행
       rs.next();
       count = rs.getInt("double_id");
       
       if(count > 0) {
          result = true;
          JOptionPane.showMessageDialog(null, "중복된 ID가 있습니다.");
       } else {
          result = false;
       }
       
       System.out.println("중복된 ID 갯수: " + count);
       System.out.println("아이디 중복: " + result);
       
     } catch (SQLException e) {
       System.out.println("SQL 문법에 문제가 있는것 같습니다.");
       e.printStackTrace();
     } finally {
       this.dbclose.close(con, pstmt, rs);
     }
      
     return result;
  }
  
  
  /**
   * 회원번호 불러오기
   * @param member_id
   * @param member_passwd
   * @return
   */
  public MemberVO find_member_no(String member_id, String member_passwd) {
    MemberVO memberVO = new MemberVO();
    try {
      con = this.dbopen.getConnection(); 
      
      sql = new StringBuffer();
      sql.append(" SELECT member_no");
      sql.append(" FROM member");
      sql.append(" WHERE member_id = ? AND member_passwd = ?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, member_id);
      pstmt.setString(2, member_passwd);
      rs = pstmt.executeQuery(); // SQL 실행
      
      if (rs.next() == true) { // 첫번째 레코드 -> 마지막 레코드로 이동
        memberVO.setMember_no(rs.getInt("member_no"));
      }
    } catch (SQLException e) {
      System.out.println("SQL 문법에 문제가 있는것 같습니다.");
      e.printStackTrace();
    } finally {
      this.dbclose.close(con, pstmt, rs);      
    }
    
    return memberVO;    
  }

  

  public static void main(String[] args) {
    new main();
  }

}