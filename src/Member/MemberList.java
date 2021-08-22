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
    * @������ : serialVersionUID
    * @���� : ����ȭ�� �ʿ��� ���� ����
    */
   private static final long serialVersionUID = 1L;

   /**
    * @������ : data
    * @���� : ���̺� ǥ�õ� �����͸� �����ϴ� ���� ���� ����
    */
   @SuppressWarnings("rawtypes")
   private Vector data = null;

    /**
    * @������ : title
    * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����ϴ� ���� ���� ����
    */
   @SuppressWarnings("rawtypes")
   private Vector title = null;
   private JTable table = null;

   /**
    * @������ : model
    * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����͸� �����ϴ� �� ��ü ���� ����
    */
   private DefaultTableModel model = null;

   /**
   * @������ : btnAdd
  * @���� : �߰� ��ư ��ü ���� ����
   */
   private JButton btnAdd    = null;

   /**
    * @������ : btnDel
    * @���� : ���� ��ư ��ü ���� ����
    */
   private JButton btnDel = null;

    /**
    * @������ : btnUpdate
    * @���� : ���� ��ư ��ü ���� ����
    */
   private JButton btnUpdate      = null;

    /**
    * @������ : btnClear
    * @���� : �ʱ�ȭ ��ư ��ü ���� ����
    */
   private JButton btnClear      = null;

   private JTextField TF_Member_NO   = null;
   private JTextField TF_Member_ID = null;
   private JTextField TF_Member_PW   = null;
   private JTextField TF_Member_NAME   = null;
   private JTextField TF_Member_TEL   = null;
   private JTextField TF_Member_ADDRESS   = null;
   private JTextField TF_Member_RDATE   = null;

   // ���̺� ���� ����
   private JLabel   LB_Member_NO   = null;
   private JLabel   LB_Member_ID   = null;
   private JLabel   LB_Member_PW   = null;
   private JLabel   LB_Member_NAME   = null;
   private JLabel   LB_Member_TEL   = null;
   private JLabel   LB_Member_ADDRESS   = null;
   private JLabel   LB_Member_RDATE   = null;
   
   private JLabel   top_label   = null;
   
   String Url = "jdbc:oracle:thin:@220.72.27.180:1521/xe"; // URL ���� ���� ����
   private String user = "sys as sysdba"; // user ���� ���� ���� -> hr
   private String password = "1234"; // password ���� ���� ���� -> hr

   private Connection conn       = null;
   private Statement stmt         = null;
   private PreparedStatement pstmtAdd    = null;
   private PreparedStatement pstmtDel    = null;
   private PreparedStatement pstmtUpdate = null;

   /**
    * @�����ڸ� : JdbcVectorTableEvnetSample
    * @���� : Frame �ʱ�ȭ, �г� ����, ���̺� ����, �� ����, ȭ�鿡 �ʿ��� ������Ʈ ���� �� 
    *        �ʱ�ȭ 
    */
   public MemberList() {

      super("ȸ�� ���");
      setSize(1400,800);
      setLocationRelativeTo(null);

      preDbTreatment();
      data = new Vector<>();

      title = new Vector<>();

      title.add("ȸ�� ��ȣ");
      title.add("���̵�");
      title.add("��й�ȣ");
      title.add("�̸�");
      title.add("����ó");
      title.add("�ּ�");
      title.add("������");

    //   ���̺� ǥ�õ� �� ��ü ����
      model = new DefaultTableModel();

    // selectAll() : �����ͺ��̽�  Member ���̺� �ִ� ��� �����͸� ������ ���� �޼ҵ�
    // ���� result�� ����
      Vector result = selectAll();

      // �𵨿� ����� ������(result)�� ���� ����
      model.setDataVector(result, title);

      //   ���� ���� ���̺� ����
      table = new JTable(model);
      
      //   ���̺� ��ũ���� ���� 
      JScrollPane sp = new JScrollPane(table);

      //   ���̺� ���콺 Ŭ��(mouseClicked)�� ó���� �̺�Ʈ ���
      table.addMouseListener(new MouseAdapter() {

      // ���콺 Ŭ���� ó���� ����ϴ� �޼ҵ� ������
         
         @Override
         public void mouseClicked(MouseEvent e) {
   
         //   getSelectedRow() : ���̺��� ���õ� ���� ���� ������ ���� �޼ҵ�(0���� ���۵�) 
         int index = table.getSelectedRow();
         
         //   ���� ���̺� ǥ�õǰ� �ִ� data(��)���� index(���� ���õ� �ٰ�)�� 
         //   1���� ���ڵ�(��) ��ü�� ���ͷ� �����ؼ� in ���� ������ ����
         Vector in = (Vector) data.get(index);
         
         //   in ���Ϳ� ����ִ� ���� ������ String ������ ���� 
         int member_no = (int)in.get(0);
         String member_id = (String)in.get(1);
         String member_passwd = (String)in.get(2);
         String member_name = (String)in.get(3);
         String member_tel = (String)in.get(4);
         String member_address = (String)in.get(5);
         String member_rdate = (String)in.get(6);
         
         //  ȭ�鿡 ǥ�õ� ������ TextField(��ȣ~�����ϱ���)�� 
         //   �� setting
         
         TF_Member_NO.setText(Integer.toString(member_no));
         TF_Member_ID.setText(member_id);
         TF_Member_PW.setText(member_passwd);
         TF_Member_NAME.setText(member_name);
         TF_Member_TEL.setText(member_tel);
         TF_Member_ADDRESS.setText(member_address);
         TF_Member_RDATE.setText(member_rdate);

         // ��ȣ�� setEditable(false)�� ���� ���� ó��
         TF_Member_NO.setEditable(false);
         TF_Member_ID.setEditable(false);
         TF_Member_PW.setEditable(false);

         }

      });

     //   ȭ�鿡 ǥ�õ� �г� ����
      JPanel panel = new JPanel();
//      panel.setBackground(Color.white);

      //   ���� �Է¹ްų� ǥ���� �ؽ�Ʈ�ʵ�(��ȣ, �̸�, �ּ�) ���� <= �Է¹��� �ʾƵ� �� ���� �����Ұ� ���߿�..
      TF_Member_NO = new JTextField(5);
      TF_Member_ID = new JTextField(8);
      TF_Member_PW = new JTextField(8);
      TF_Member_NAME = new JTextField(8);
      TF_Member_TEL = new JTextField(10);
      TF_Member_ADDRESS = new JTextField(20);
      TF_Member_RDATE = new JTextField(10);

    //      ���̺� ����

      LB_Member_NO = new JLabel("ȸ�� ��ȣ");
      LB_Member_ID = new JLabel("���̵�");
      LB_Member_PW = new JLabel("��й�ȣ");
      LB_Member_NAME = new JLabel("�̸�");
      LB_Member_TEL = new JLabel("����ó");
      LB_Member_ADDRESS = new JLabel("�ּ�");
      LB_Member_RDATE = new JLabel("�����");

      LB_Member_NO.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Member_ID.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Member_PW.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Member_NAME.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Member_TEL.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Member_ADDRESS.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Member_RDATE.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));

      // ��ư(�߰�, ����, ����, �ʱ�ȭ) ����
      btnAdd = new JButton("�߰�");
      btnDel = new JButton("����");
      btnUpdate = new JButton("����");
      btnClear = new JButton("�ʱ�ȭ");
      
      
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

      //   �߰���ư�� �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ�ʵ忡 �Էµ� ������ �����ͺ��̽��� 
      //   ����(Insert)�ϴ� ����
      btnAdd.addActionListener(new ActionListener() {
               
      //   actionPerformed(ActionEvent e) : �߰� ��ư Ŭ���� ȣ��� �޼ҵ�
      //   �߰���ư�� Ŭ���ϸ� ó���� ���� �ۼ�

         @Override
         public void actionPerformed(ActionEvent e) {
            // ���� �ؽ�Ʈ �ʵ忡 �ִ� ���� ������ ������ ���� 
            
            String member_id = TF_Member_ID.getText(); 
            String member_passwd = TF_Member_PW.getText();
            String member_name = TF_Member_NAME.getText(); 
            String member_tel = TF_Member_TEL.getText(); 
            String member_address = TF_Member_ADDRESS.getText(); 

            // ������ ������ ����� ���� �����ͺ��̽��� Insert�ϴ� �޼ҵ�
            insert(member_id, member_passwd, member_name, member_tel, member_address);

            //   �ű� ����� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ���� 
            Vector result = selectAll();
           
            // ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ� 
            model.setDataVector(result, title);

         }

      });

      

      //   ���� ��ư�� �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ �ʵ忡 �Էµ� ��ȣ������ �����ͺ��̽��� 
      //   �ش� ��ȣ�� ���ڵ带 ����(Delete) �ϴ� ����

      btnDel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         
         // �ؽ�Ʈ�ʵ忡 �ִ� ��ȣ�� ������ ����
         int member_no = Integer.parseInt(TF_Member_NO.getText());
         
      //   ��ȣ������ �����ͺ��̽����� �ش� ��ũ�带 �����ϴ� �޼ҵ�
         delete(member_no);
         
         //   ����ó�� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ���� 
         Vector result = selectAll();
         
         // ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ�

            model.setDataVector(result, title);

         }

      });

      

      //      ������ư�� �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ �ʵ忡 �Էµ� ��ȣ������ �����ͺ��̽���
      //      �ش� ��ȣ�� ��ũ�带 ����(Update) �ϴ� ���� 
      btnUpdate.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
         // �ؽ�Ʈ�ʵ忡 �ִ� ������ ������ ����
            
            int member_no = Integer.parseInt(TF_Member_NO.getText());
            String member_tel = TF_Member_TEL.getText();
            String member_address = TF_Member_ADDRESS.getText();

            //   ��ȣ�� �������� ������ �̸��� �ּҸ� �����ϴ� �޼ҵ� 
            update(member_tel, member_address, member_no);
            
            //   ����ó�� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ���� 
            Vector result = selectAll();
            
            // ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ�
            
            model.setDataVector(result, title);

         }

      });

      

      //   �ʱ�ȭ ��ư �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ�ʵ� �ʱ�ȭ, ��ȣ�ؽ�Ʈ�ʵ忡 Ŀ�� ��ġ
      btnClear.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            //   �ʱ�ȭ

            TF_Member_NO.setText("");
            TF_Member_ID.setText("");
            TF_Member_PW.setText("");
            TF_Member_NAME.setText("");
            TF_Member_TEL.setText("");
            TF_Member_ADDRESS.setText("");
            TF_Member_RDATE.setText("");
            
            //   ���������ϰ� ����
            // TF_Member_NO.setEditable(true);
            TF_Member_TEL.setEditable(true);
            TF_Member_ADDRESS.setEditable(true);

            // ��ȣ�ؽ�Ʈ�ʵ忡 Ŀ�� ��ġ

            TF_Member_NO.requestFocus();

         }

      });

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

//      �гο� ������ ���̺�� �ؽ�Ʈ�ʵ� �߰�
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
      
      
      
   
      
      

      // �гο� ��ư �߰�
      panel.add(btnAdd);
      panel.add(btnDel);
      panel.add(btnUpdate);
      panel.add(btnClear);
      
      
      
      
      
      
      

      // Frame�� ContentPane �����̳� ��������
      Container c = getContentPane();
      
      // �����̳ʿ� ���̺�, �г�(�ؽ�Ʈ�ʵ�, ��Ʈ�� ���Ե� �г�) �߰�

      
      
      
      
      top_label=new JLabel("ȸ�� ���",top_label.CENTER);
      top_label.setForeground(Color.white);
      c.add(top_label,"North");
      
      c.setBackground(Color.BLACK);
      top_label.setFont(new Font("Nixie One",Font.BOLD,30));

      c.add(sp);
      c.add(panel, BorderLayout.SOUTH);
//      sp.setBackground(Color.white);
      sp.getViewport().setBackground(Color.WHITE);
      //   ������ ����� ó���� �̺�Ʈ ó�� 

      addWindowListener(new WindowAdapter(){
         @Override

         public void windowClosing(WindowEvent w) {

            try{

               stmt.close(); // Statement ��ü �ݱ�
               conn.close(); // Connection ��ü �ݱ�

               setVisible(false); // ȭ�� �ݱ�

               dispose(); // �ڿ� �ݳ�

               //System.exit(0); // ���� ó��

            }catch(Exception e){

            }

         }

      });

   }

   
   
   
   
   
   
   

   /**

    * @Method Name : selectAll

    * @return : data  

    * @���� : �����ͺ��̽����� �˻��� �����͸� data Vector�� ��� ����

    */

   private Vector selectAll() {

      data.clear();

      

      try{

         ResultSet rs = stmt.executeQuery("select * from member order by member_no");

         while(rs.next()){

            Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

            int member_no = Integer.parseInt(rs.getString(1)); 
            String member_id = rs.getString(2); 
            String member_passwd = rs.getString(3); 
            String member_name = rs.getString(4); 
            String member_tel = rs.getString(5); 
            String member_address = rs.getString(6); 
            String member_rdate = rs.getString(7); 

            //���Ϳ� ������ �� �߰�
            in.add(member_no);
            in.add(member_id);
            in.add(member_passwd);
            in.add(member_name);
            in.add(member_tel);
            in.add(member_address);
            in.add(member_rdate);

            //   ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�

            data.add(in);

         }

      }catch(Exception e){

         e.printStackTrace();

      }

      return data; // ��ü ������ �����ϴ� data ���� ����

   }



   /**
    * @Method Name : insert
    * @param num :��ȣ �ؽ�Ʈ�ʵ忡 �Է¹��� �� 
    * @param name : �̸� �ؽ�Ʈ�ʵ忡 �Է¹��� ��
    * @param address : �ּ� �ؽ�Ʈ�ʵ忡 �Է¹��� ��
    * @���� : ������ �ؽ�Ʈ�ʵ忡 �Էµ� ���� �Ķ���ͷ� �޾Ƽ� �����ͺ��̽��� 
    *        insert ó���ϴ� �޼ҵ�
    */
   private void insert(String member_id, String member_passwd, String member_name, String member_tel, String member_address){

      try{

         // PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

         pstmtAdd = conn.prepareStatement("insert into member(member_no, member_id, member_passwd, member_name, member_tel, member_address, member_rdate) VALUES (member_no_seq.nextval, ?, ?, ?, ?, ?, sysdate)");

         pstmtAdd.setString(1, member_id);
         pstmtAdd.setString(2, member_passwd);
         pstmtAdd.setString(3, member_name);
         pstmtAdd.setString(4, member_tel);
         pstmtAdd.setString(5, member_address);

         //   ���Թ��� ������ ���� -> �Է� (insert)

         pstmtAdd.executeUpdate();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   /**
    * @Method Name : delete
    * @param member_no : ��ȣ �ؽ�Ʈ�ʵ忡 �Է¹��� ��
    * @���� : ��ȣ���� �Ķ���͸� �޾� �ش� ��ȣ�� �����͸� ��񿡼� ����(delete) �ϴ� �޼ҵ� 
    */
   private void delete(int member_no){

      try{

         // PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����
         pstmtDel = conn.prepareStatement("delete from member where member_no = ?");

         // num ���� ���ؼ� ������
          pstmtDel.setInt(1, member_no);


          // ���Թ��� ������ ����-> ���� (delete)
         pstmtDel.executeUpdate();

      } catch(Exception e){

         e.printStackTrace();

      }

   }

   // ȸ�� ���� ����
   private void update(String member_tel, String member_address, int member_no){

      try{

         //   PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

         pstmtUpdate = conn.prepareStatement("update member set member_tel = ?, member_address = ? where member_no = ?");

         pstmtUpdate.setString(1, member_tel);
         pstmtUpdate.setString(2, member_address);
         pstmtUpdate.setInt(3, member_no);

         // ���� ����
         pstmtUpdate.executeUpdate();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   

   /**
    * @Method Name : preDbTreatment
    * @���� : �����ͺ��̽� ���� �� Connection, Statement ����
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

      //      ���� ������Ʈ�� ũ��� ���� ���¿� ���� �������� ũ�⸦ ����
      frame.pack();

      frame.setVisible(true);

   }

}