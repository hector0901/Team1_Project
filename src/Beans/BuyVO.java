package Beans;

public class BuyVO {
	
//  buy_no number(10) not null,              --주문 번호
//  productno      NUMBER(10) NOT NULL, -- 상품 번호
//  member_no           NUMBER(10) not null, -- 회원 번호
//  buy_cnt   number(10) not null,                -- 수량
//  buy_date  date not null,                        --주문일
//  primary key(buy_no),
//  FOREIGN KEY (member_no) REFERENCES member (member_no),
//  FOREIGN KEY (productno) REFERENCES product (productno)
	
  private int buy_no;
  private int productno;
  private int member_no;
  private int buy_cnt;
  private String buy_date;
  
	public int getBuy_no() {
		return buy_no;
	}
	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}
	public int getProductno() {
		return productno;
	}
	public void setProductno(int productno) {
		this.productno = productno;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getBuy_cnt() {
		return buy_cnt;
	}
	public void setBuy_cnt(int buy_cnt) {
		this.buy_cnt = buy_cnt;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

}
