package Member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MemberList extends JFrame {

   /**
    * @변수명 : serialVersionUID
    * @설명 : 직렬화시 필요한 변수 선언
    */
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

   /**
   * @변수명 : btnAdd
  * @설명 : 추가 버튼 객체 변수 선언
   */
   private JButton btnAdd    = null;

   /**
    * @변수명 : btnDel
    * @설명 : 삭제 버튼 객체 변수 선언
    */
   private JButton btnDel = null;

    /**
    * @변수명 : btnUpdate
    * @설명 : 수정 버튼 객체 변수 선언
    */
   private JButton btnUpdate      = null;

    /**
    * @변수명 : btnClear
    * @설명 : 초기화 버튼 객체 변수 선언
    */
   private JButton btnClear      = null;

   private JTextField TF_Member_NO   = null;
   private JTextField TF_Member_ID = null;
   private JTextField TF_Member_PW   = null;
   private JTextField TF_Member_NAME   = null;
   private JTextField TF_Member_TEL   = null;
   private JTextField TF_Member_ADDRESS   = null;
   private JTextField TF_Member_RDATE   = null;

   // 레이블 변수 선언
   private JLabel   LB_Member_NO   = null;
   private JLabel   LB_Member_ID   = null;
   private JLabel   LB_Member_PW   = null;
   private JLabel   LB_Member_NAME   = null;
   private JLabel   LB_Member_TEL   = null;
   private JLabel   LB_Member_ADDRESS   = null;
   private JLabel   LB_Member_RDATE   = null;
   
   private JLabel   top_label   = null;
   
   String Url = "jdbc:oracle:thin:@220.72.27.180:1521/xe"; // URL 정보 저장 변수
   private String user = "sys as sysdba"; // user 정보 저장 변수 -> hr
   private String password = "1234"; // password 정보 저장 변수 -> hr

   private Connection conn       = null;
   private Statement stmt         = null;
   private PreparedStatement pstmtAdd    = null;
   private PreparedStatement pstmtDel    = null;
   private PreparedStatement pstmtUpdate = null;

   /**
    * @생성자명 : JdbcVectorTableEvnetSample
    * @설명 : Frame 초기화, 패널 생성, 테이블 생성, 모델 생성, 화면에 필요한 컴포넌트 생성 및 
    *        초기화 
    */
   public MemberList() {

      super("회원 목록");
      setSize(1400,800);
      setLocationRelativeTo(null);

      preDbTreatment();
      data = new Vector<>();

      title = new Vector<>();

      title.add("회원 번호");
      title.add("아이디");
      title.add("비밀번호");
      title.add("이름");
      title.add("연락처");
      title.add("주소");
      title.add("가입일");

    //   테이블에 표시될 모델 객체 생성
      model = new DefaultTableModel();

    // selectAll() : 데이터베이스  Member 테이블에 있는 모든 데이터를 가지고 오는 메소드
    // 벡터 result에 저장
      Vector result = selectAll();

      // 모델에 변경된 데이터(result)를 새로 적용
      model.setDataVector(result, title);

      //   모델을 통해 테이블 생성
      table = new JTable(model);
      
      //   테이블에 스크롤팬 생성 
      JScrollPane sp = new JScrollPane(table);

      //   테이블에 마우스 클릭(mouseClicked)시 처리될 이벤트 등록
      table.addMouseListener(new MouseAdapter() {

      // 마우스 클릭시 처리를 담당하는 메소드 재정의
         
         @Override
         public void mouseClicked(MouseEvent e) {
   
         //   getSelectedRow() : 테이블에서 선택된 줄의 값을 가지고 오는 메소드(0부터 시작됨) 
         int index = table.getSelectedRow();
         
         //   현재 테이블에 표시되고 있는 data(모델)에서 index(현재 선택된 줄값)로 
         //   1개의 레코드(줄) 전체를 벡터로 저장해서 in 벡터 변수에 대입
         Vector in = (Vector) data.get(index);
         
         //   in 벡터에 들어있는 값을 각각의 String 변수에 대입 
         int member_no = (int)in.get(0);
         String member_id = (String)in.get(1);
         String member_passwd = (String)in.get(2);
         String member_name = (String)in.get(3);
         String member_tel = (String)in.get(4);
         String member_address = (String)in.get(5);
         String member_rdate = (String)in.get(6);
         
         //  화면에 표시된 각각의 TextField(번호~가입일까지)에 
         //   값 setting
         
         TF_Member_NO.setText(Integer.toString(member_no));
         TF_Member_ID.setText(member_id);
         TF_Member_PW.setText(member_passwd);
         TF_Member_NAME.setText(member_name);
         TF_Member_TEL.setText(member_tel);
         TF_Member_ADDRESS.setText(member_address);
         TF_Member_RDATE.setText(member_rdate);

         // 번호는 setEditable(false)로 수정 방지 처리
         TF_Member_NO.setEditable(false);
         TF_Member_ID.setEditable(false);
         TF_Member_PW.setEditable(false);

         }

      });

     //   화면에 표시될 패널 생성
      JPanel panel = new JPanel();
//      panel.setBackground(Color.white);

      //   값을 입력받거나 표시할 텍스트필드(번호, 이름, 주소) 생성 <= 입력받지 않아도 될 변수 제거할것 나중에..
      TF_Member_NO = new JTextField(5);
      TF_Member_ID = new JTextField(8);
      TF_Member_PW = new JTextField(8);
      TF_Member_NAME = new JTextField(8);
      TF_Member_TEL = new JTextField(10);
      TF_Member_ADDRESS = new JTextField(20);
      TF_Member_RDATE = new JTextField(10);

    //      레이블 생성

      LB_Member_NO = new JLabel("회원 번호");
      LB_Member_ID = new JLabel("아이디");
      LB_Member_PW = new JLabel("비밀번호");
      LB_Member_NAME = new JLabel("이름");
      LB_Member_TEL = new JLabel("연락처");
      LB_Member_ADDRESS = new JLabel("주소");
      LB_Member_RDATE = new JLabel("등록일");

      LB_Member_NO.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Member_ID.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Member_PW.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Member_NAME.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Member_TEL.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Member_ADDRESS.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Member_RDATE.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));

      // 버튼(추가, 삭제, 수정, 초기화) 생성
      btnAdd = new JButton("추가");
      btnDel = new JButton("삭제");
      btnUpdate = new JButton("수정");
      btnClear = new JButton("초기화");
      
      
      btnAdd.setBackground(Color.black);
      btnAdd.setForeground(Color.white);
      btnAdd.setFont(new Font("Nixie One",Font.BOLD, 15));
      
      btnDel.setBackground(Color.black);
      btnDel.setForeground(Color.white);
      btnDel.setFont(new Font("Nixie One",Font.BOLD,15));
      
      btnUpdate.setBackground(Color.black);
      btnUpdate.setForeground(Color.white);
      btnUpdate.setFont(new Font("Nixie One",Font.BOLD,15));
      
      btnClear.setBackground(Color.black);
      btnClear.setForeground(Color.white);
      btnClear.setFont(new Font("Nixie One",Font.BOLD,15));

      //   추가버튼에 이벤트(클릭시) 처리 -> 텍스트필드에 입력된 정보를 데이터베이스에 
      //   저장(Insert)하는 영역
      btnAdd.addActionListener(new ActionListener() {
               
      //   actionPerformed(ActionEvent e) : 추가 버튼 클릭시 호출될 메소드
      //   추가버튼을 클릭하면 처리할 내용 작성

         @Override
         public void actionPerformed(ActionEvent e) {
            // 현재 텍스트 필드에 있는 값을 각각의 변수에 대입 
            
            String member_id = TF_Member_ID.getText(); 
            String member_passwd = TF_Member_PW.getText();
            String member_name = TF_Member_NAME.getText(); 
            String member_tel = TF_Member_TEL.getText(); 
            String member_address = TF_Member_ADDRESS.getText(); 

            // 각각의 변수에 저장된 값을 데이터베이스에 Insert하는 메소드
            insert(member_id, member_passwd, member_name, member_tel, member_address);

            //   신규 저장된 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장 
            Vector result = selectAll();
           
            // 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨 
            model.setDataVector(result, title);

         }

      });

      

      //   삭제 버튼에 이벤트(클릭시) 처리 -> 텍스트 필드에 입력된 번호값으로 데이터베이스에 
      //   해당 번호의 레코드를 삭제(Delete) 하는 영역

      btnDel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         
         // 텍스트필드에 있는 번호값 변수에 대입
         int member_no = Integer.parseInt(TF_Member_NO.getText());
         
      //   번호값으로 데이터베이스에서 해당 레크드를 삭제하는 메소드
         delete(member_no);
         
         //   삭제처리 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장 
         Vector result = selectAll();
         
         // 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨

            model.setDataVector(result, title);

         }

      });

      

      //      수정버튼에 이벤트(클릭시) 처리 -> 텍스트 필드에 입력된 번호값으로 데이터베이스에
      //      해당 번호의 레크드를 수정(Update) 하는 영역 
      btnUpdate.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
         // 텍스트필드에 있는 값들을 변수에 저장
            
            int member_no = Integer.parseInt(TF_Member_NO.getText());
            String member_tel = TF_Member_TEL.getText();
            String member_address = TF_Member_ADDRESS.getText();

            //   번호를 기준으로 수정된 이름과 주소를 수정하는 메소드 
            update(member_tel, member_address, member_no);
            
            //   삭제처리 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장 
            Vector result = selectAll();
            
            // 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨
            
            model.setDataVector(result, title);

         }

      });

      

      //   초기화 버튼 이벤트(클릭시) 처리 -> 텍스트필드 초기화, 번호텍스트필드에 커서 위치
      btnClear.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            //   초기화

            TF_Member_NO.setText("");
            TF_Member_ID.setText("");
            TF_Member_PW.setText("");
            TF_Member_NAME.setText("");
            TF_Member_TEL.setText("");
            TF_Member_ADDRESS.setText("");
            TF_Member_RDATE.setText("");
            
            //   수정가능하게 변경
            // TF_Member_NO.setEditable(true);
            TF_Member_TEL.setEditable(true);
            TF_Member_ADDRESS.setEditable(true);

            // 번호텍스트필드에 커서 위치

            TF_Member_NO.requestFocus();

         }

      });

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

//      패널에 각각의 레이블과 텍스트필드 추가
      panel.add(LB_Member_NO);
      panel.add(TF_Member_NO);
      panel.add(LB_Member_ID);
      panel.add(TF_Member_ID);
      panel.add(LB_Member_PW);
      panel.add(TF_Member_PW);
      panel.add(LB_Member_NAME);
      panel.add(TF_Member_NAME);
      panel.add(LB_Member_TEL);
      panel.add(TF_Member_TEL);
      panel.add(LB_Member_ADDRESS);
      panel.add(TF_Member_ADDRESS);
      panel.add(LB_Member_RDATE);
      panel.add(TF_Member_RDATE);
      
      
      
   
      
      

      // 패널에 버튼 추가
      panel.add(btnAdd);
      panel.add(btnDel);
      panel.add(btnUpdate);
      panel.add(btnClear);
      
      
      
      
      
      
      

      // Frame의 ContentPane 컨테이너 가지오기
      Container c = getContentPane();
      
      // 컨테이너에 테이블, 패널(텍스트필드, 번트이 포함된 패널) 추가

      
      
      
      
      top_label=new JLabel("회원 목록",top_label.CENTER);
      top_label.setForeground(Color.white);
      c.add(top_label,"North");
      
      c.setBackground(Color.BLACK);
      top_label.setFont(new Font("Nixie One",Font.BOLD,30));

      c.add(sp);
      c.add(panel, BorderLayout.SOUTH);
//      sp.setBackground(Color.white);
      sp.getViewport().setBackground(Color.WHITE);
      //   프레임 종료시 처리될 이벤트 처리 

      addWindowListener(new WindowAdapter(){
         @Override

         public void windowClosing(WindowEvent w) {

            try{

               stmt.close(); // Statement 객체 닫기
               conn.close(); // Connection 객체 닫기

               setVisible(false); // 화면 닫기

               dispose(); // 자원 반납

               //System.exit(0); // 종료 처리

            }catch(Exception e){

            }

         }

      });

   }

   
   
   
   
   
   
   

   /**

    * @Method Name : selectAll

    * @return : data  

    * @설명 : 데이터베이스에서 검색된 데이터를 data Vector에 담아 리턴

    */

   private Vector selectAll() {

      data.clear();

      

      try{

         ResultSet rs = stmt.executeQuery("select * from member order by member_no");

         while(rs.next()){

            Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

            int member_no = Integer.parseInt(rs.getString(1)); 
            String member_id = rs.getString(2); 
            String member_passwd = rs.getString(3); 
            String member_name = rs.getString(4); 
            String member_tel = rs.getString(5); 
            String member_address = rs.getString(6); 
            String member_rdate = rs.getString(7); 

            //벡터에 각각의 값 추가
            in.add(member_no);
            in.add(member_id);
            in.add(member_passwd);
            in.add(member_name);
            in.add(member_tel);
            in.add(member_address);
            in.add(member_rdate);

            //   전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가

            data.add(in);

         }

      }catch(Exception e){

         e.printStackTrace();

      }

      return data; // 전체 데이터 저장하는 data 벡터 리턴

   }



   /**
    * @Method Name : insert
    * @param num :번호 텍스트필드에 입력받은 값 
    * @param name : 이름 텍스트필드에 입력받은 값
    * @param address : 주소 텍스트필드에 입력받은 값
    * @설명 : 각각의 텍스트필드에 입력된 값을 파라미터로 받아서 데이터베이스에 
    *        insert 처리하는 메소드
    */
   private void insert(String member_id, String member_passwd, String member_name, String member_tel, String member_address){

      try{

         // PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음

         pstmtAdd = conn.prepareStatement("insert into member(member_no, member_id, member_passwd, member_name, member_tel, member_address, member_rdate) VALUES (member_no_seq.nextval, ?, ?, ?, ?, ?, sysdate)");

         pstmtAdd.setString(1, member_id);
         pstmtAdd.setString(2, member_passwd);
         pstmtAdd.setString(3, member_name);
         pstmtAdd.setString(4, member_tel);
         pstmtAdd.setString(5, member_address);

         //   대입받은 쿼리를 실행 -> 입력 (insert)

         pstmtAdd.executeUpdate();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   /**
    * @Method Name : delete
    * @param member_no : 번호 텍스트필드에 입력받은 값
    * @설명 : 번호값을 파라미터를 받아 해당 번호의 데이터를 디비에서 삭제(delete) 하는 메소드 
    */
   private void delete(int member_no){

      try{

         // PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음
         pstmtDel = conn.prepareStatement("delete from member where member_no = ?");

         // num 값을 비교해서 삭제함
          pstmtDel.setInt(1, member_no);


          // 대입받은 쿼리를 실행-> 삭제 (delete)
         pstmtDel.executeUpdate();

      } catch(Exception e){

         e.printStackTrace();

      }

   }

   // 회원 정보 수정
   private void update(String member_tel, String member_address, int member_no){

      try{

         //   PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음

         pstmtUpdate = conn.prepareStatement("update member set member_tel = ?, member_address = ? where member_no = ?");

         pstmtUpdate.setString(1, member_tel);
         pstmtUpdate.setString(2, member_address);
         pstmtUpdate.setInt(3, member_no);

         // 쿼리 실행
         pstmtUpdate.executeUpdate();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   

   /**
    * @Method Name : preDbTreatment
    * @설명 : 데이터베이스 연동 및 Connection, Statement 생성
    */
   private void preDbTreatment() {

      try{

         Class.forName("oracle.jdbc.driver.OracleDriver");

         conn = DriverManager.getConnection(Url,user,password);
         stmt = conn.createStatement();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   

   public static void main(String[] args) {

      MemberList frame = new MemberList();

      //      내부 컴포넌트의 크기와 정렬 상태에 따라 프레임의 크기를 정함
      frame.pack();

      frame.setVisible(true);

   }

}