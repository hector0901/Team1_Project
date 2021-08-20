package Beans;

public class NoticeVO {

  // admin_no NUMBER(10,0) NOT NULL, PRIMARY KEY,
  // admin_id VARCHAR2(100) NOT NULL,
  // admin_passwd VARCHAR2(100) NOT NULL,
  // admin_name VARCHAR2(100) NOT NULL.

  private int notice_no;
  private int admin_no;
  private String notice_title;
  private String notice_content;
  private String notice_date;

  public NoticeVO() {

  }

  // 회원가입시 직접 타이핑 해야할 변수들이 있는 생성자
  public NoticeVO(int notice_no, int admin_no, String notice_title, String notice_content, String notice_date) {
    this.notice_no = notice_no;
    this.admin_no = admin_no;
    this.notice_title = notice_title;
    this.notice_content = notice_content;
    this.notice_date = notice_date;
  }

  public int getNotice_no() {
    return notice_no;
  }

  public void setNotice_no(int notice_no) {
    this.notice_no = notice_no;
  }

  public int getAdmin_no() {
    return admin_no;
  }

  public void setAdmin_no(int admin_no) {
    this.admin_no = admin_no;
  }

  public String getNotice_title() {
    return notice_title;
  }

  public void setNotice_title(String notice_title) {
    this.notice_title = notice_title;
  }

  public String getNotice_content() {
    return notice_content;
  }

  public void setNotice_content(String notice_content) {
    this.notice_content = notice_content;
  }

  public String getNotice_date() {
    return notice_date;
  }

  public void setNotice_date(String notice_date) {
    this.notice_date = notice_date;
  }

}
