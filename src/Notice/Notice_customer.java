package Notice;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class Notice_customer extends JFrame {

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
   private Vector data1 = null;

    /**
    * @변수명 : title
    * @설명 : 테이블에 표시될 타이틀을 저장하는 벡터 변수 선언
    */
   @SuppressWarnings("rawtypes")
   private Vector title1 = null;
   private JTable table1 = null;

    /**
    * @변수명 : model
    * @설명 : 테이블에 표시될 타이틀과 데이터를 저장하는 모델 객체 변수 선언
    */
   private DefaultTableModel model1 = null;

   /**
   * @변수명 : btnAdd
   * @설명 : 추가 버튼 객체 변수 선언
   */
   private JButton btnAdd1 = null;

    /**
    * @변수명 : btnDel
    * @설명 : 삭제 버튼 객체 변수 선언
    */
   private JButton btnDel1 = null;

    /**
    * @변수명 : btnUpdate
    * @설명 : 수정 버튼 객체 변수 선언
    */
   private JButton btnUpdate1 = null;

    /**
    * @변수명 : btnClear
    * @설명 : 초기화 버튼 객체 변수 선언
    */
   private JButton btnClear1      = null;

   private JTextField TF_Notice_No   = null;
   private JTextField TF_Admin_No = null;
   private JTextField TF_Notice_Title   = null;
   private JTextField TF_Notice_Content   = null;
   private JTextField TF_Notice_Date   = null;

   // 레이블 변수 선언
   private JLabel LB_Notice_No = null;
   private JLabel LB_Admin_No = null;
   private JLabel LB_Notice_Title = null;
   private JLabel LB_Notice_Content = null;
   private JLabel LB_Notice_Date = null;
   private JLabel top_label1   = null;
   
   String Url = "jdbc:oracle:thin:@ 220.72.27.180:1521/xe"; // URL 정보 저장 변수
   private String user = "sys as sysdba"; // user 정보 저장 변수 -> hr
   private String password = "1234"; // password 정보 저장 변수 -> hr

   private Connection conn = null;
   private Statement stmt = null;
   private PreparedStatement pstmtAdd1 = null;
   private PreparedStatement pstmtDel1 = null;
   private PreparedStatement pstmtUpdate1 = null;

    /**
    * @생성자명 : JdbcVectorTableEvnetSample
    * @설명 : Frame 초기화, 패널 생성, 테이블 생성, 모델 생성, 화면에 필요한 컴포넌트 생성 및 
    *        초기화 
    */
   public  Notice_customer() {

      super("공지 사항");
      setSize(1800, 500);

      preDbTreatment();
      data1 = new Vector<>();

      title1 = new Vector<>();

      title1.add("공지 번호");
      title1.add("관리자");
      title1.add("제목");
      title1.add("내용");
      title1.add("등록일자");
 

      // 테이블에 표시될 모델 객체 생성
      model1 = new DefaultTableModel();

      // selectAll() : 데이터베이스  Member 테이블에 있는 모든 데이터를 가지고 오는 메소드
      // 벡터 result에 저장
      Vector result = selectAll();

      // 모델에 변경된 데이터(result)를 새로 적용
      model1.setDataVector(result, title1);

      // 모델을 통해 테이블 생성
      table1 = new JTable(model1);
      
      // 테이블에 스크롤팬 생성 
      JScrollPane sp = new JScrollPane(table1);

      // 테이블에 마우스 클릭(mouseClicked)시 처리될 이벤트 등록
      table1.addMouseListener(new MouseAdapter() {

         // 마우스 클릭시 처리를 담당하는 메소드 재정의
         @Override
         public void mouseClicked(MouseEvent e) {
   
         // getSelectedRow() : 테이블에서 선택된 줄의 값을 가지고 오는 메소드(0부터 시작됨) 
         int index = table1.getSelectedRow();
         
         // 현재 테이블에 표시되고 있는 data(모델)에서 index(현재 선택된 줄값)로 
         // 1개의 레코드(줄) 전체를 벡터로 저장해서 in 벡터 변수에 대입
         Vector in = (Vector) data1.get(index);
         
         //   in 벡터에 들어있는 값을 각각의 String 변수에 대입 
         int notice_no = (int)in.get(0);
         int admin_no = (int)in.get(1);
         String notice_title = (String)in.get(2);
         String notice_content = (String)in.get(3);
         String notice_date = (String)in.get(4);
        
        
         
         
//         
//         테이블 셀 간격 조절
         table1.setRowHeight(30);
         table1.getColumn("공지 번호").setPreferredWidth(100);
         table1.getColumn("관리자").setPreferredWidth(100);
         table1.getColumn("제목").setPreferredWidth(200);
         table1.getColumn("내용").setPreferredWidth(800);
         table1.getColumn("등록일자").setPreferredWidth(100);
         
         
         
 //셀 가운데 정렬
         DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
         dtcr.setHorizontalAlignment(SwingConstants.CENTER);
         TableColumnModel tcm=table1.getColumnModel();
         for(int i=0;i<tcm.getColumnCount();i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

         
     
              
         
         
         
         //  화면에 표시된 각각의 TextField(번호~가입일까지)에 
         //   값 setting
         TF_Notice_No.setText(Integer.toString(notice_no));
         TF_Admin_No.setText(Integer.toString(admin_no));
         TF_Notice_Title.setText(notice_title);
         TF_Notice_Content.setText(notice_content);
         TF_Notice_Date.setText(notice_date);

         // 번호는 setEditable(false)로 수정 방지 처리
         TF_Notice_No.setEditable(false);
         TF_Admin_No.setEditable(false);
         TF_Notice_Date.setEditable(false);
       
         
        
         
         
         
         
         }

      });

      // 화면에 표시될 패널 생성
      JPanel panel = new JPanel();
      // panel.setBackground(Color.white);

      // 값을 입력받거나 표시할 텍스트필드(번호, 이름, 주소) 생성 <= 입력받지 않아도 될 변수 제거할것 나중에..
      TF_Notice_No = new JTextField(5);
      TF_Admin_No = new JTextField(8);
      TF_Notice_Title = new JTextField(20);
      TF_Notice_Content = new JTextField(20);
      TF_Notice_Date = new JTextField(10);
      
      
      
      
      TF_Notice_No.setVisible(false);
      TF_Admin_No.setVisible(false);
      TF_Notice_Title.setVisible(false);
      TF_Notice_Content.setVisible(false);
      TF_Notice_Date.setVisible(false);
      
      
      
      // 레이블 생성
      LB_Notice_No = new JLabel("공지 번호");
      LB_Admin_No = new JLabel("관리자");
      LB_Notice_Title = new JLabel("제목");
      LB_Notice_Content = new JLabel("내용");
      LB_Notice_Date = new JLabel("등록일자");
      
      
      LB_Notice_No.setVisible(false);
      LB_Admin_No .setVisible(false);
      LB_Notice_Title .setVisible(false);
      LB_Notice_Content.setVisible(false);
      LB_Notice_Date.setVisible(false);
      
      
      
      
      
     
      LB_Notice_No.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Admin_No.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Notice_Title.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Notice_Content.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
      LB_Notice_Date.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
     

      // 버튼(추가, 삭제, 수정, 초기화) 생성
      btnAdd1 = new JButton("추가");
      btnDel1 = new JButton("삭제");
      btnUpdate1 = new JButton("수정");
      btnClear1 = new JButton("초기화");
      
      btnAdd1.setVisible(false);////////////////////////////////////////////
      btnDel1.setVisible(false);////////////////////////////////////////////
      btnUpdate1.setVisible(false);/////////////////////////////////////////
      btnClear1.setVisible(false);//////////////////////////////////////////
      
      btnAdd1.setBackground(Color.black);
      btnAdd1.setForeground(Color.white);
      btnAdd1.setFont(new Font("Nixie One",Font.BOLD, 15));
      
      btnDel1.setBackground(Color.black);
      btnDel1.setForeground(Color.white);
      btnDel1.setFont(new Font("Nixie One",Font.BOLD,15));
      
      btnUpdate1.setBackground(Color.black);
      btnUpdate1.setForeground(Color.white);
      btnUpdate1.setFont(new Font("Nixie One",Font.BOLD,15));
      
      btnClear1.setBackground(Color.black);
      btnClear1.setForeground(Color.white);
      btnClear1.setFont(new Font("Nixie One",Font.BOLD,15));
      
      TF_Notice_No.setEditable(false);
      TF_Notice_Date.setEditable(false);
     
      //   추가버튼에 이벤트(클릭시) 처리 -> 텍스트필드에 입력된 정보를 데이터베이스에 
      //   저장(Insert)하는 영역
      btnAdd1.addActionListener(new ActionListener() {
               
      //   actionPerformed(ActionEvent e) : 추가 버튼 클릭시 호출될 메소드
      //   추가버튼을 클릭하면 처리할 내용 작성

         @Override
         public void actionPerformed(ActionEvent e) {
            // 현재 텍스트 필드에 있는 값을 각각의 변수에 대입 
            
            int admin_no = Integer.parseInt(TF_Admin_No.getText());
            String notice_title= TF_Notice_Title.getText();
            String notice_content = TF_Notice_Content.getText(); 
            String notice_date= TF_Notice_Date.getText(); 
            
            // 각각의 변수에 저장된 값을 데이터베이스에 Insert하는 메소드
            insert(admin_no, notice_title, notice_content);

            //   신규 저장된 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장 
            Vector result = selectAll();
           
            // 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨 
            model1.setDataVector(result, title1);

         }

      });

      

      //   삭제 버튼에 이벤트(클릭시) 처리 -> 텍스트 필드에 입력된 번호값으로 데이터베이스에 
      //   해당 번호의 레코드를 삭제(Delete) 하는 영역

      btnDel1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         
         // 텍스트필드에 있는 번호값 변수에 대입
         int notice_no = Integer.parseInt(TF_Notice_No.getText());
         
      //   번호값으로 데이터베이스에서 해당 레크드를 삭제하는 메소드
         delete(notice_no);
         
         //   삭제처리 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장 
         Vector result = selectAll();
         
         // 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨

            model1.setDataVector(result, title1);

         }

      });

      

      // 수정버튼에 이벤트(클릭시) 처리 -> 텍스트 필드에 입력된 번호값으로 데이터베이스에
      // 해당 번호의 레크드를 수정(Update) 하는 영역 
      btnUpdate1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
         // 텍스트필드에 있는 값들을 변수에 저장
            
            int notice_no = Integer.parseInt(TF_Notice_No.getText());
            String notice_title = TF_Notice_Title.getText();
            String notice_content = TF_Notice_Content.getText();


            //   번호를 기준으로 수정된 이름과 주소를 수정하는 메소드 
            update(notice_content, notice_title, notice_no);
            
            //   삭제처리 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장 
            Vector result = selectAll();
            
            // 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨
            
            model1.setDataVector(result, title1);

         }

      });

      // 초기화 버튼 이벤트(클릭시) 처리 -> 텍스트필드 초기화, 번호텍스트필드에 커서 위치
      btnClear1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // 초기화
            TF_Notice_No.setText("");
            TF_Admin_No.setText("");
            TF_Notice_Title.setText("");
            TF_Notice_Content.setText("");
            TF_Notice_Date.setText("");
           
            // 수정가능하게 변경
            TF_Notice_No.setEditable(false);
            TF_Admin_No.setEditable(true);
            TF_Notice_Title.setEditable(true);
            TF_Notice_Content.setEditable(true);
            TF_Notice_Date.setEditable(false);
            
            // 번호텍스트필드에 커서 위치
            TF_Notice_No.requestFocus();

         }

      });


      // 패널에 각각의 레이블과 텍스트필드 추가
      panel.add(LB_Notice_No);
      panel.add(TF_Notice_No);
      panel.add(LB_Admin_No);
      panel.add(TF_Admin_No);
      panel.add(LB_Notice_Title);
      panel.add(TF_Notice_Title);
      panel.add(LB_Notice_Content);
      panel.add(TF_Notice_Content);
      panel.add(LB_Notice_Date);
      panel.add(TF_Notice_Date);
    
      // 패널에 버튼 추가
      panel.add(btnAdd1);
      panel.add(btnDel1);
      panel.add(btnUpdate1);
      panel.add(btnClear1);
 
      // Frame의 ContentPane 컨테이너 가지오기
      Container c = getContentPane();
      
      // 컨테이너에 테이블, 패널(텍스트필드, 번트이 포함된 패널) 추가
      top_label1=new JLabel("공지사항",top_label1.CENTER);
      top_label1.setForeground(Color.white);
      c.add(top_label1,"North");
      
      c.setBackground(Color.BLACK);
      top_label1.setFont(new Font("Nixie One",Font.BOLD,30));

      c.add(sp);
      c.add(panel, BorderLayout.SOUTH);
      // sp.setBackground(Color.white);
      sp.getViewport().setBackground(Color.WHITE);
      
      // 프레임 종료시 처리될 이벤트 처리 
      addWindowListener(new WindowAdapter(){
         @Override

         public void windowClosing(WindowEvent w) {

            try{

               stmt.close(); // Statement 객체 닫기
               conn.close(); // Connection 객체 닫기

               setVisible(false); // 화면 닫기

               dispose(); // 자원 반납

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

      data1.clear();

      try{

         ResultSet rs = stmt.executeQuery("select * from notice order by notice_no");

         while(rs.next()){

            Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

            int notice_no = Integer.parseInt(rs.getString(1)); 
            int admin_no= Integer.parseInt(rs.getString(2)); 
            String notice_title = rs.getString(3); 
            String notice_content = rs.getString(4); 
            String notice_date = rs.getString(5); 

            //벡터에 각각의 값 추가
            in.add(notice_no);
            in.add(admin_no);
            in.add(notice_title);
            in.add(notice_content);
            in.add(notice_date);
        

            // 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
            data1.add(in);

         }

      }catch(Exception e){

         e.printStackTrace();

      }

      return data1; // 전체 데이터 저장하는 data 벡터 리턴

   }

   /**
    * @Method Name : insert
    * @param num :번호 텍스트필드에 입력받은 값 
    * @param name : 이름 텍스트필드에 입력받은 값
    * @param address : 주소 텍스트필드에 입력받은 값
    * @설명 : 각각의 텍스트필드에 입력된 값을 파라미터로 받아서 데이터베이스에 
    *        insert 처리하는 메소드
    */
   private void insert(int admin_no, String notice_title, String notice_content){

      try{

         // PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음

         pstmtAdd1 = conn.prepareStatement("insert into notice(notice_no, admin_no, notice_title, notice_content, notice_date) VALUES (notice_no_seq.nextval, ?, ?, ?, sysdate)");

         pstmtAdd1.setInt(1, admin_no);
         pstmtAdd1.setString(2, notice_title);
         pstmtAdd1.setString(3, notice_content);
        

         //   대입받은 쿼리를 실행 -> 입력 (insert)

         pstmtAdd1.executeUpdate();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   /**
    * @Method Name : delete
    * @param member_no : 번호 텍스트필드에 입력받은 값
    * @설명 : 번호값을 파라미터를 받아 해당 번호의 데이터를 디비에서 삭제(delete) 하는 메소드 
    */
   private void delete(int notice_no){

      try{

         // PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음
         pstmtDel1 = conn.prepareStatement("delete from notice where notice_no = ?");

         // num 값을 비교해서 삭제함
          pstmtDel1.setInt(1,notice_no);


          // 대입받은 쿼리를 실행-> 삭제 (delete)
         pstmtDel1.executeUpdate();

      } catch(Exception e){

         e.printStackTrace();

      }

   }

   // 회원 정보 수정
   private void update(String notice_title, String notice_content, int notice_no){

      try{

         //   PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음

         pstmtUpdate1 = conn.prepareStatement("update notice set notice_title = ?, notice_content = ? where notice_no = ?");

         pstmtUpdate1.setString(1, notice_title);
         pstmtUpdate1.setString(2, notice_content);
         pstmtUpdate1.setInt(3, notice_no);

         // 쿼리 실행
         pstmtUpdate1.executeUpdate();

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

	   Notice_customer frame = new  Notice_customer();

      //      내부 컴포넌트의 크기와 정렬 상태에 따라 프레임의 크기를 정함
      frame.pack();

      frame.setVisible(true);

   }
}

