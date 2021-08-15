package Beans;

public class MemberVO {

//  member_no           NUMBER(10)        NOT NULL PRIMARY KEY,                           -- ȸ���� �����ϰ� ���ִ� => ȸ����ȣ(��Ű)
//  member_id            VARCHAR2(50)      NOT NULL,                                      -- ID
//  member_passwd     VARCHAR2(50)      NOT NULL,                                         -- PW
//  member_name       VARCHAR2(50)      NOT NULL,                                         -- �̸�
//  member_tel            NUMBER(20)       NOT NULL,                                      -- ����ó
//  member_address      VARCHAR(100)      NOT NULL,                                       -- �ּ�
//  member_rdate         DATE                NOT NULL  

  private int member_no;
  private String member_id;
  private String member_passwd;
  private String member_name;
  private String member_tel;
  private String member_address;
  private String member_rdate;

  // �⺻������
  public MemberVO() {

  }

  // ȸ�����Խ� ���� Ÿ���� �ؾ��� �������� �ִ� ������
  public MemberVO(String member_id, String member_passwd, String member_name, String member_tel, String member_address) {

    this.member_id = member_id;
    this.member_passwd = member_passwd;
    this.member_name = member_name;
    this.member_tel = member_tel;
    this.member_address = member_address;

  }

  // Getter & Setter
  public int getMember_no() {
    return member_no;
  }

  public void setMember_no(int member_no) {
    this.member_no = member_no;
  }

  public String getMember_id() {
    return member_id;
  }

  public void setMember_id(String member_id) {
    this.member_id = member_id;
  }

  public String getMember_passwd() {
    return member_passwd;
  }

  public void setMember_passwd(String member_passwd) {
    this.member_passwd = member_passwd;
  }

  public String getMember_name() {
    return member_name;
  }

  public void setMember_name(String member_name) {
    this.member_name = member_name;
  }

  public String getMember_tel() {
    return member_tel;
  }

  public void setMember_tel(String member_tel) {
    this.member_tel = member_tel;
  }

  public String getMember_address() {
    return member_address;
  }

  public void setMember_address(String member_address) {
    this.member_address = member_address;
  }

  public String getMember_rdate() {
    return member_rdate;
  }

  public void setMember_rdate(String member_rdate) {
    this.member_rdate = member_rdate;
  }

}
