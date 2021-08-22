package Beans;

public class ProductVO {
	
//  productno      NUMBER(10) NOT NULL PRIMARY KEY, 
//  admin_no       NUMBER(10,0) NOT NULL,
//  brandname VARCHAR(50) NOT NULL,
//  productname VARCHAR(100) NOT NULL,
//  productcontent CLOB NOT NULL,
//  productprice NUMBER(20) NOT NULL,
//  productrdate DATE NOT NULL,
//  FOREIGN KEY (admin_no) REFERENCES admin (admin_no)
	
	private int productno;
	private int admin_no;
	private int productprice;
	private String brandname;
	private String productname;
	private String productcontent;
	private String productrdate;
	public int getProductno() {
		return productno;
	}
	public void setProductno(int productno) {
		this.productno = productno;
	}
	public int getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductcontent() {
		return productcontent;
	}
	public void setProductcontent(String productcontent) {
		this.productcontent = productcontent;
	}
	public String getProductrdate() {
		return productrdate;
	}
	public void setProductrdate(String productrdate) {
		this.productrdate = productrdate;
	}

}
