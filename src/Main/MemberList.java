package Main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MemberList extends JFrame {

   JTable table;
   JScrollPane jsp;
   Vector<String> col;
   Vector<Vector<String>> rowData;
   JTextField textfield_search;
   JTextField textfield_no;
   JTextField textfield_id;
   JTextField textfield_name;
   JTextField textfield_tel;
   JTextField textfield_addr;
   JTextField textfield_rdate;   
   JButton button_searchB; 
   JButton button_add;
   JButton button_modify;
   JButton button_delete;
   
   
   
    MemberList()
    {
       Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


   
       //틀
        Container c= getContentPane();
       c.setSize(1200,800);
       c.setLayout(null);
       c.setBackground(Color.white);
           
       
       //상단패널
      
          JPanel panel1=new JPanel();
           c.add(panel1);
           panel1.setBounds(0,0,1200,50);
           panel1.setLayout(null);
           panel1.setBackground(Color.black);
           JLabel jl= new JLabel("회원 정보");
           jl.setFont(new Font("Nixie One",Font.BOLD,30));
           jl.setForeground(Color.white);
           panel1.add(jl);
           jl.setBounds(580, 0, 200, 50);
       
       
           //상단 검색란
           
           
           String []list= {"회원번호","아이디","이름"};
           JComboBox search= new JComboBox(list);
            c.add(search);
            search.setBounds(385,60,130,30);
         
           textfield_search=new JTextField(100);
            c.add(textfield_search);
            textfield_search.setBounds(520,60,260,30);
           
           button_searchB=new JButton("검색");
           c.add(button_searchB);
                      
       
           button_searchB.setBounds(785,60,130,30);
           button_searchB.setBackground(Color.black);
           button_searchB.setForeground(Color.white);
           button_searchB.setFont(new Font("Nixie One",Font.BOLD,15));
           
//           
//           search.addActionListener(new ActionListener() {
//          public void actionPerformed(ActionEvent e) {
//             
//             
//          }
//       });
             
           
   
         
       
       //테이블 생성
           
       col=new Vector<String>();
       rowData=new Vector<Vector<String>>();    
      
       col.add("회원번호");
       col.add("아이디");
       col.add("이름");
       col.add("연락처");
       col.add("추소");
       col.add("등록일");
       
       table=new JTable(rowData, col);
       c.add(table);
       
       
       
       //스크롤 부착
       jsp = new JScrollPane(table);
       jsp.getViewport().setBackground(Color.WHITE);
       jsp.setBounds(250,100,800,500);
       c.add(jsp);
       
       
       
       
       ////////////테이블모양/////////////
      
       
       
        
       
       //좌측 상단 정보 입출력란
       
       JPanel panel=new JPanel();
       c.add(panel);
       panel.setBounds(80,100,120,350);
       panel.setBackground(Color.white); 
       panel.setBorder(new LineBorder(Color.gray, 1));
       
      JLabel no=new JLabel("회원번호");
      JLabel id=new JLabel("아이디");
      JLabel name=new JLabel("이름");
      JLabel tel=new JLabel("연락처");
      JLabel addr=new JLabel("주소");
      JLabel rdate=new JLabel("등록일");
          
      no.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
        id.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
        name.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
        tel.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
        addr.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
        rdate.setFont(new Font("맑은 고딕 Semilight",Font.PLAIN,13));
        
       
      textfield_no=new JTextField(10);
      textfield_id=new JTextField(10);
      textfield_name=new JTextField(10);
      textfield_tel=new JTextField(10);
      textfield_addr=new JTextField(10);
      textfield_rdate=new JTextField(10);
      
      panel.add(no);
      panel.add(textfield_no);
      panel.add(id);
      panel.add(textfield_id);
      panel.add(name);
      panel.add(textfield_name);
      panel.add(tel);
      panel.add(textfield_tel);   
      panel.add(addr);
      panel.add(textfield_addr);
      panel.add(rdate);
      panel.add(textfield_rdate);
      
   
      
   //좌측 하단 버튼란   
      
      
      
      JPanel panel2=new JPanel();
      c.add(panel2);
      panel2.setBounds(80,480,120,120);
      panel2.setBorder(new LineBorder(Color.gray, 1));
      panel2.setBackground(Color.white);
         
      button_add=new JButton("추가");
      button_modify=new JButton("수정");
      button_delete=new JButton("삭제");
      panel2.add(button_add);
      panel2.add(button_modify);
      panel2.add(button_delete);
      
      button_add.setBackground(Color.black);
      button_add.setForeground(Color.white);
      button_add.setFont(new Font("Nixie One",Font.BOLD,15));
        
      button_modify.setBackground(Color.black);
      button_modify.setForeground(Color.white);
      button_modify.setFont(new Font("Nixie One",Font.BOLD,15));
        
      button_delete.setBackground(Color.black);
      button_delete.setForeground(Color.white);
      button_delete.setFont(new Font("Nixie One",Font.BOLD,15));
      
      
      
      
      setVisible(true);
      setSize(1200,800);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
       ////////////테이블모양/////////////
      
      
      
      //추가하기 버튼
      button_add.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            
            String no=textfield_no.getText();
            String id=textfield_id.getText();
            String name=textfield_name.getText();
            String tel=textfield_tel.getText();
             String addr=textfield_addr.getText();
             String rdate=textfield_rdate.getText();

            
            
            Vector<String> v =new Vector<String>();
            v.add(no);
            v.add(id);
            v.add(name);
            v.add(tel);
            v.add(addr);
            v.add(rdate);
            
            rowData.add(v);//텍스트 필드에 있는 값을 추가하기.
            
            table.updateUI();
            
            textfield_no.setText("");//추가후에, 텍스트필드에 남아있는 값 지워주기.
            textfield_id.setText("");
            textfield_name.setText("");
            textfield_tel.setText("");
            textfield_addr.setText("");
            textfield_rdate.setText("");            
         
         
         }
      });
           
      //수정하기 버튼
      button_modify.addActionListener(new ActionListener(){
         
         public void actionPerformed(ActionEvent e){
            
             
            
             String no=textfield_no.getText();
            String id=textfield_id.getText();
            String name=textfield_name.getText();
            String tel=textfield_tel.getText();
             String addr=textfield_addr.getText();
                String rdate=textfield_rdate.getText();
            
            int selection=table.getSelectedRow();//사용자가 선택한
            Vector<String> v =new Vector<String>();
            v.add(no);
            v.add(id);          
            v.add(name);
            v.add(tel);
            v.add(addr+"");
            v.add(rdate+"");
            
            
            rowData.setElementAt(v, selection);
            //Vector의 메소드
            //지정된 위치(v, selection)의 저장된 객체를 반환해주는 역할/
            
            
            table.updateUI();
            
            textfield_no.setText("");//수정후에, 텍스트필드에 남아있는 값 지워주기.
            textfield_id.setText("");
            textfield_name.setText("");
            textfield_tel.setText("");
            textfield_addr.setText("");
            textfield_rdate.setText("");
            
            
         }
            
      });
      //삭제하기 버튼
      button_delete.addActionListener(new ActionListener(){
         
         public void actionPerformed(ActionEvent e){
            
            
            int selection=table.getSelectedRow();
            rowData.remove(selection);
            table.updateUI();
            
         }   
         
      });
      
      
      table.addMouseListener(new MouseListener() {
         
         @Override
         public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void mousePressed(MouseEvent e) {
            
   
         }
         
      
         @Override
         public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void mouseClicked(MouseEvent e) {
         //마우스로 선택한 행의 정보를 각각의 텍스트필드에 출력하기   
            
            int selection=table.getSelectedRow();
            
            Vector<String> vc=rowData.get(selection);
            
            textfield_no.setText(vc.get(0));
            textfield_id.setText(vc.get(1));
            textfield_name.setText(vc.get(2));
            textfield_tel.setText(vc.get(3));
             textfield_addr.setText(vc.get(4));
             textfield_rdate.setText(vc.get(5));
         }
      });
      
      
      
    }//디폴트 생성자 괄호
   
   
   public static void main(String[] args) 
   {
       new MemberList();
   }

}
      
      
      