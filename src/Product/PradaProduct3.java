package Product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class PradaProduct3 extends JFrame {
   int ea;
   ImageIcon gg[]= {new ImageIcon(".//image//프라다 플라쥬 위커 및 캔버스 버킷백.png"),
         new ImageIcon(".//image//클레오 브러시드 가죽 숄더.png"),
         new ImageIcon(".//image//크롭 셰틀랜드 울 카디건.png")
   };
   JTable table;
     JScrollPane jsp;
     Vector<String> col;
     Vector<Vector<String>> rowData;
  public PradaProduct3(){
      super("상품 창");
      Container c=getContentPane();
      setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
      
      //위쪽 사진 패널
      JPanel panel1=new JPanel();
      panel1.setBackground(Color.white);
      panel1.setPreferredSize(new Dimension(1200,195));
      JLabel my=new JLabel(new ImageIcon(".//image//상품.png"));
      panel1.add(my);
      
      c.add(panel1,BorderLayout.NORTH);
      
      
      JPanel panel2=new JPanel();
      panel2.setBackground(Color.white);
      panel2.setLayout(null);
      //브랜드이름
      JLabel j1=new JLabel("PRADA");
      j1.setBounds(1200, 50, 400, 90);
      j1.setFont(new Font("맑은 고딕",Font.BOLD,60));
      
      //상품이름
      JLabel j2=new JLabel("크롭 셰틀랜드 울 카디건");
      j2.setFont(new Font("맑은 고딕",Font.BOLD,30));
      j2.setBounds(1200, 160, 700, 50);
      
      //가격
      JLabel j3=new JLabel("$1,990");
      j3.setFont(new Font("맑은 고딕",Font.BOLD,30));
      j3.setBounds(1200, 200, 300, 100);
      
      //상품구매
      JButton jb1=new JButton("상품구매");
      jb1.setForeground(Color.WHITE);
      jb1.setBackground(Color.BLACK);
      jb1.setFont(new Font("맑은 고딕",Font.BOLD,30));
      jb1.setBounds(1300, 600, 300, 100);
      
      
      
      //상품설명
      /*JTextArea ja1=new JTextArea("상품설명");
      ja1.setFont(new Font("맑은 고딕",Font.BOLD,20));
      ja1.setBounds(1200, 350, 600, 350);*/
      
      JLabel text=new JLabel("크롭 셰틀랜드 울 카디건으로, 세련된 매력과 슬림한 디자인");
      JLabel text2=new JLabel("V 네크라인이 보석 장식 버튼으로 반짝이는 클로저");
      text.setFont(new Font("맑은 고딕",Font.BOLD,18));
      text2.setFont(new Font("맑은 고딕",Font.BOLD,18));
      text.setBounds(1200, 320,600,50);
      text2.setBounds(1200, 350,600,50);
      
      //상품번호
      JLabel j4=new JLabel("no.3");
      j4.setFont(new Font("맑은 고딕",Font.BOLD,20));
      j4.setBounds(250, 20, 100, 40);  
      
       //이미지
      JLabel pro1=new JLabel(new ImageIcon(".//image//프라다 플라쥬 위커 및 캔버스 버킷백.png"));
      JLabel pro2=new JLabel(new ImageIcon(".//image//클레오 브러시드 가죽 숄더.png"));
      JLabel pro3=new JLabel(new ImageIcon(".//image//크롭 셰틀랜드 울 카디건.png"));
      
      pro1.setBounds(400,65,500,500);
      pro2.setBounds(400,90,500,500);
      pro3.setBounds(400,110,500,500);
      
      
      //panel2.add(pro1);
      //panel2.add(pro2);
      panel2.add(pro3);
      
      JButton plus=new JButton("+");
      JButton minus=new JButton("-");
     
      JTextField jt=new JTextField(10);//수량 입력필드
      jt.setFont(new Font("맑은 고딕",Font.BOLD,20));
       
      minus.setBackground(Color.BLACK);
      minus.setForeground(Color.WHITE);
      minus.setFont(new Font("맑은 고딕",Font.BOLD,20));
      plus.setBackground(Color.BLACK);
      plus.setForeground(Color.WHITE);
      plus.setFont(new Font("맑은 고딕",Font.BOLD,20));
    

      minus.setBounds(1310,500,70,50);//-
      jt.setBounds(1393,500,105,50);//jt필드
      plus.setBounds(1513,500,70,50);//+
      panel2.add(plus);
      panel2.add(minus);
      panel2.add(jt);
   
   
      plus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         //+
         ea++;
         jt.setText(String.valueOf(ea));
      }
      });
      minus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         //-
         ea--;
         jt.setText(String.valueOf(ea));
      }
      });
      
      
      
      //상품구매
      jb1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //jt.setText("");
            String regExp = "^[0-9]+$";     
          String CNT = jt.getText().trim();
          int cnt=Integer.parseInt(CNT);
          
          if (CNT==null ) {
               JOptionPane.showMessageDialog(null, "수량를 입력해주세요");
               jt.setText("");
            } 
          if(cnt <= 0) {
             JOptionPane.showMessageDialog(null, "주문은 1개이상부터 가능합니다");
               jt.setText("0");
          }
            if (cnt <= 0||CNT==null||!(CNT.matches(regExp))) {
                 JOptionPane.showMessageDialog(null, "구매 실패, 다시 시도해 주세요.");
               } else {                 
               JOptionPane.showMessageDialog(null, 
               "주문이 완료 되었습니다!","주문 완료",JOptionPane.INFORMATION_MESSAGE);  
               dispose();
               } 
            
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
   public static void main(String[] args) {
      
   }
}