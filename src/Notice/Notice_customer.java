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
    * @������ : serialVersionUID
    * @���� : ����ȭ�� �ʿ��� ���� ����
    */
   private static final long serialVersionUID = 1L;

    /**
    * @������ : data
    * @���� : ���̺� ǥ�õ� �����͸� �����ϴ� ���� ���� ����
    */
   @SuppressWarnings("rawtypes")
   private Vector data1 = null;

    /**
    * @������ : title
    * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����ϴ� ���� ���� ����
    */
   @SuppressWarnings("rawtypes")
   private Vector title1 = null;
   private JTable table1 = null;

    /**
    * @������ : model
    * @���� : ���̺� ǥ�õ� Ÿ��Ʋ�� �����͸� �����ϴ� �� ��ü ���� ����
    */
   private DefaultTableModel model1 = null;

   /**
   * @������ : btnAdd
   * @���� : �߰� ��ư ��ü ���� ����
   */
   private JButton btnAdd1 = null;

    /**
    * @������ : btnDel
    * @���� : ���� ��ư ��ü ���� ����
    */
   private JButton btnDel1 = null;

    /**
    * @������ : btnUpdate
    * @���� : ���� ��ư ��ü ���� ����
    */
   private JButton btnUpdate1 = null;

    /**
    * @������ : btnClear
    * @���� : �ʱ�ȭ ��ư ��ü ���� ����
    */
   private JButton btnClear1      = null;

   private JTextField TF_Notice_No   = null;
   private JTextField TF_Admin_No = null;
   private JTextField TF_Notice_Title   = null;
   private JTextField TF_Notice_Content   = null;
   private JTextField TF_Notice_Date   = null;

   // ���̺� ���� ����
   private JLabel LB_Notice_No = null;
   private JLabel LB_Admin_No = null;
   private JLabel LB_Notice_Title = null;
   private JLabel LB_Notice_Content = null;
   private JLabel LB_Notice_Date = null;
   private JLabel top_label1   = null;
   
   String Url = "jdbc:oracle:thin:@ 220.72.27.180:1521/xe"; // URL ���� ���� ����
   private String user = "sys as sysdba"; // user ���� ���� ���� -> hr
   private String password = "1234"; // password ���� ���� ���� -> hr

   private Connection conn = null;
   private Statement stmt = null;
   private PreparedStatement pstmtAdd1 = null;
   private PreparedStatement pstmtDel1 = null;
   private PreparedStatement pstmtUpdate1 = null;

    /**
    * @�����ڸ� : JdbcVectorTableEvnetSample
    * @���� : Frame �ʱ�ȭ, �г� ����, ���̺� ����, �� ����, ȭ�鿡 �ʿ��� ������Ʈ ���� �� 
    *        �ʱ�ȭ 
    */
   public  Notice_customer() {

      super("���� ����");
      setSize(1800, 500);

      preDbTreatment();
      data1 = new Vector<>();

      title1 = new Vector<>();

      title1.add("���� ��ȣ");
      title1.add("������");
      title1.add("����");
      title1.add("����");
      title1.add("�������");
 

      // ���̺� ǥ�õ� �� ��ü ����
      model1 = new DefaultTableModel();

      // selectAll() : �����ͺ��̽�  Member ���̺� �ִ� ��� �����͸� ������ ���� �޼ҵ�
      // ���� result�� ����
      Vector result = selectAll();

      // �𵨿� ����� ������(result)�� ���� ����
      model1.setDataVector(result, title1);

      // ���� ���� ���̺� ����
      table1 = new JTable(model1);
      
      // ���̺� ��ũ���� ���� 
      JScrollPane sp = new JScrollPane(table1);

      // ���̺� ���콺 Ŭ��(mouseClicked)�� ó���� �̺�Ʈ ���
      table1.addMouseListener(new MouseAdapter() {

         // ���콺 Ŭ���� ó���� ����ϴ� �޼ҵ� ������
         @Override
         public void mouseClicked(MouseEvent e) {
   
         // getSelectedRow() : ���̺��� ���õ� ���� ���� ������ ���� �޼ҵ�(0���� ���۵�) 
         int index = table1.getSelectedRow();
         
         // ���� ���̺� ǥ�õǰ� �ִ� data(��)���� index(���� ���õ� �ٰ�)�� 
         // 1���� ���ڵ�(��) ��ü�� ���ͷ� �����ؼ� in ���� ������ ����
         Vector in = (Vector) data1.get(index);
         
         //   in ���Ϳ� ����ִ� ���� ������ String ������ ���� 
         int notice_no = (int)in.get(0);
         int admin_no = (int)in.get(1);
         String notice_title = (String)in.get(2);
         String notice_content = (String)in.get(3);
         String notice_date = (String)in.get(4);
        
        
         
         
//         
//         ���̺� �� ���� ����
         table1.setRowHeight(30);
         table1.getColumn("���� ��ȣ").setPreferredWidth(100);
         table1.getColumn("������").setPreferredWidth(100);
         table1.getColumn("����").setPreferredWidth(200);
         table1.getColumn("����").setPreferredWidth(800);
         table1.getColumn("�������").setPreferredWidth(100);
         
         
         
 //�� ��� ����
         DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
         dtcr.setHorizontalAlignment(SwingConstants.CENTER);
         TableColumnModel tcm=table1.getColumnModel();
         for(int i=0;i<tcm.getColumnCount();i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

         
     
              
         
         
         
         //  ȭ�鿡 ǥ�õ� ������ TextField(��ȣ~�����ϱ���)�� 
         //   �� setting
         TF_Notice_No.setText(Integer.toString(notice_no));
         TF_Admin_No.setText(Integer.toString(admin_no));
         TF_Notice_Title.setText(notice_title);
         TF_Notice_Content.setText(notice_content);
         TF_Notice_Date.setText(notice_date);

         // ��ȣ�� setEditable(false)�� ���� ���� ó��
         TF_Notice_No.setEditable(false);
         TF_Admin_No.setEditable(false);
         TF_Notice_Date.setEditable(false);
       
         
        
         
         
         
         
         }

      });

      // ȭ�鿡 ǥ�õ� �г� ����
      JPanel panel = new JPanel();
      // panel.setBackground(Color.white);

      // ���� �Է¹ްų� ǥ���� �ؽ�Ʈ�ʵ�(��ȣ, �̸�, �ּ�) ���� <= �Է¹��� �ʾƵ� �� ���� �����Ұ� ���߿�..
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
      
      
      
      // ���̺� ����
      LB_Notice_No = new JLabel("���� ��ȣ");
      LB_Admin_No = new JLabel("������");
      LB_Notice_Title = new JLabel("����");
      LB_Notice_Content = new JLabel("����");
      LB_Notice_Date = new JLabel("�������");
      
      
      LB_Notice_No.setVisible(false);
      LB_Admin_No .setVisible(false);
      LB_Notice_Title .setVisible(false);
      LB_Notice_Content.setVisible(false);
      LB_Notice_Date.setVisible(false);
      
      
      
      
      
     
      LB_Notice_No.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Admin_No.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Notice_Title.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Notice_Content.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
      LB_Notice_Date.setFont(new Font("���� ��� Semilight",Font.PLAIN,13));
     

      // ��ư(�߰�, ����, ����, �ʱ�ȭ) ����
      btnAdd1 = new JButton("�߰�");
      btnDel1 = new JButton("����");
      btnUpdate1 = new JButton("����");
      btnClear1 = new JButton("�ʱ�ȭ");
      
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
     
      //   �߰���ư�� �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ�ʵ忡 �Էµ� ������ �����ͺ��̽��� 
      //   ����(Insert)�ϴ� ����
      btnAdd1.addActionListener(new ActionListener() {
               
      //   actionPerformed(ActionEvent e) : �߰� ��ư Ŭ���� ȣ��� �޼ҵ�
      //   �߰���ư�� Ŭ���ϸ� ó���� ���� �ۼ�

         @Override
         public void actionPerformed(ActionEvent e) {
            // ���� �ؽ�Ʈ �ʵ忡 �ִ� ���� ������ ������ ���� 
            
            int admin_no = Integer.parseInt(TF_Admin_No.getText());
            String notice_title= TF_Notice_Title.getText();
            String notice_content = TF_Notice_Content.getText(); 
            String notice_date= TF_Notice_Date.getText(); 
            
            // ������ ������ ����� ���� �����ͺ��̽��� Insert�ϴ� �޼ҵ�
            insert(admin_no, notice_title, notice_content);

            //   �ű� ����� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ���� 
            Vector result = selectAll();
           
            // ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ� 
            model1.setDataVector(result, title1);

         }

      });

      

      //   ���� ��ư�� �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ �ʵ忡 �Էµ� ��ȣ������ �����ͺ��̽��� 
      //   �ش� ��ȣ�� ���ڵ带 ����(Delete) �ϴ� ����

      btnDel1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         
         // �ؽ�Ʈ�ʵ忡 �ִ� ��ȣ�� ������ ����
         int notice_no = Integer.parseInt(TF_Notice_No.getText());
         
      //   ��ȣ������ �����ͺ��̽����� �ش� ��ũ�带 �����ϴ� �޼ҵ�
         delete(notice_no);
         
         //   ����ó�� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ���� 
         Vector result = selectAll();
         
         // ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ�

            model1.setDataVector(result, title1);

         }

      });

      

      // ������ư�� �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ �ʵ忡 �Էµ� ��ȣ������ �����ͺ��̽���
      // �ش� ��ȣ�� ��ũ�带 ����(Update) �ϴ� ���� 
      btnUpdate1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            
         // �ؽ�Ʈ�ʵ忡 �ִ� ������ ������ ����
            
            int notice_no = Integer.parseInt(TF_Notice_No.getText());
            String notice_title = TF_Notice_Title.getText();
            String notice_content = TF_Notice_Content.getText();


            //   ��ȣ�� �������� ������ �̸��� �ּҸ� �����ϴ� �޼ҵ� 
            update(notice_content, notice_title, notice_no);
            
            //   ����ó�� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ���� 
            Vector result = selectAll();
            
            // ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ�
            
            model1.setDataVector(result, title1);

         }

      });

      // �ʱ�ȭ ��ư �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ�ʵ� �ʱ�ȭ, ��ȣ�ؽ�Ʈ�ʵ忡 Ŀ�� ��ġ
      btnClear1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // �ʱ�ȭ
            TF_Notice_No.setText("");
            TF_Admin_No.setText("");
            TF_Notice_Title.setText("");
            TF_Notice_Content.setText("");
            TF_Notice_Date.setText("");
           
            // ���������ϰ� ����
            TF_Notice_No.setEditable(false);
            TF_Admin_No.setEditable(true);
            TF_Notice_Title.setEditable(true);
            TF_Notice_Content.setEditable(true);
            TF_Notice_Date.setEditable(false);
            
            // ��ȣ�ؽ�Ʈ�ʵ忡 Ŀ�� ��ġ
            TF_Notice_No.requestFocus();

         }

      });


      // �гο� ������ ���̺�� �ؽ�Ʈ�ʵ� �߰�
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
    
      // �гο� ��ư �߰�
      panel.add(btnAdd1);
      panel.add(btnDel1);
      panel.add(btnUpdate1);
      panel.add(btnClear1);
 
      // Frame�� ContentPane �����̳� ��������
      Container c = getContentPane();
      
      // �����̳ʿ� ���̺�, �г�(�ؽ�Ʈ�ʵ�, ��Ʈ�� ���Ե� �г�) �߰�
      top_label1=new JLabel("��������",top_label1.CENTER);
      top_label1.setForeground(Color.white);
      c.add(top_label1,"North");
      
      c.setBackground(Color.BLACK);
      top_label1.setFont(new Font("Nixie One",Font.BOLD,30));

      c.add(sp);
      c.add(panel, BorderLayout.SOUTH);
      // sp.setBackground(Color.white);
      sp.getViewport().setBackground(Color.WHITE);
      
      // ������ ����� ó���� �̺�Ʈ ó�� 
      addWindowListener(new WindowAdapter(){
         @Override

         public void windowClosing(WindowEvent w) {

            try{

               stmt.close(); // Statement ��ü �ݱ�
               conn.close(); // Connection ��ü �ݱ�

               setVisible(false); // ȭ�� �ݱ�

               dispose(); // �ڿ� �ݳ�

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

      data1.clear();

      try{

         ResultSet rs = stmt.executeQuery("select * from notice order by notice_no");

         while(rs.next()){

            Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

            int notice_no = Integer.parseInt(rs.getString(1)); 
            int admin_no= Integer.parseInt(rs.getString(2)); 
            String notice_title = rs.getString(3); 
            String notice_content = rs.getString(4); 
            String notice_date = rs.getString(5); 

            //���Ϳ� ������ �� �߰�
            in.add(notice_no);
            in.add(admin_no);
            in.add(notice_title);
            in.add(notice_content);
            in.add(notice_date);
        

            // ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�
            data1.add(in);

         }

      }catch(Exception e){

         e.printStackTrace();

      }

      return data1; // ��ü ������ �����ϴ� data ���� ����

   }

   /**
    * @Method Name : insert
    * @param num :��ȣ �ؽ�Ʈ�ʵ忡 �Է¹��� �� 
    * @param name : �̸� �ؽ�Ʈ�ʵ忡 �Է¹��� ��
    * @param address : �ּ� �ؽ�Ʈ�ʵ忡 �Է¹��� ��
    * @���� : ������ �ؽ�Ʈ�ʵ忡 �Էµ� ���� �Ķ���ͷ� �޾Ƽ� �����ͺ��̽��� 
    *        insert ó���ϴ� �޼ҵ�
    */
   private void insert(int admin_no, String notice_title, String notice_content){

      try{

         // PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

         pstmtAdd1 = conn.prepareStatement("insert into notice(notice_no, admin_no, notice_title, notice_content, notice_date) VALUES (notice_no_seq.nextval, ?, ?, ?, sysdate)");

         pstmtAdd1.setInt(1, admin_no);
         pstmtAdd1.setString(2, notice_title);
         pstmtAdd1.setString(3, notice_content);
        

         //   ���Թ��� ������ ���� -> �Է� (insert)

         pstmtAdd1.executeUpdate();

      }catch(Exception e){

         e.printStackTrace();

      }

   }

   /**
    * @Method Name : delete
    * @param member_no : ��ȣ �ؽ�Ʈ�ʵ忡 �Է¹��� ��
    * @���� : ��ȣ���� �Ķ���͸� �޾� �ش� ��ȣ�� �����͸� ��񿡼� ����(delete) �ϴ� �޼ҵ� 
    */
   private void delete(int notice_no){

      try{

         // PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����
         pstmtDel1 = conn.prepareStatement("delete from notice where notice_no = ?");

         // num ���� ���ؼ� ������
          pstmtDel1.setInt(1,notice_no);


          // ���Թ��� ������ ����-> ���� (delete)
         pstmtDel1.executeUpdate();

      } catch(Exception e){

         e.printStackTrace();

      }

   }

   // ȸ�� ���� ����
   private void update(String notice_title, String notice_content, int notice_no){

      try{

         //   PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

         pstmtUpdate1 = conn.prepareStatement("update notice set notice_title = ?, notice_content = ? where notice_no = ?");

         pstmtUpdate1.setString(1, notice_title);
         pstmtUpdate1.setString(2, notice_content);
         pstmtUpdate1.setInt(3, notice_no);

         // ���� ����
         pstmtUpdate1.executeUpdate();

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

	   Notice_customer frame = new  Notice_customer();

      //      ���� ������Ʈ�� ũ��� ���� ���¿� ���� �������� ũ�⸦ ����
      frame.pack();

      frame.setVisible(true);

   }
}

