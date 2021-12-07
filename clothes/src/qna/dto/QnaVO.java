package qna.dto;

import java.sql.Timestamp;

public class QnaVO {
	private int qna_id;//qna글번호
	private int clothes_id;//옷의 등록번호
	private String clothes_title;//옷 이름
	private String qna_writer;//qna작성자
	private String qna_content;//qna내용
	private int group_id;//qna 그룹아이디
	private byte qora;//qna 그룹내의 순서
	private byte reply;//답변여부
	private Timestamp reg_date;//qna 작성일
	
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public int getClothes_id() {
		return clothes_id;
	}
	public void setClothes_id(int clothes_id) {
		this.clothes_id = clothes_id;
	}
	public String getClothes_title() {
		return clothes_title;
	}
	public void setClothes_title(String clothes_title) {
		this.clothes_title = clothes_title;
	}
	public String getQna_writer() {
		return qna_writer;
	}
	public void setQna_writer(String qna_writer) {
		this.qna_writer = qna_writer;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public byte getQora() {
		return qora;
	}
	public void setQora(byte qora) {
		this.qora = qora;
	}
	public byte getReply() {
		return reply;
	}
	public void setReply(byte reply) {
		this.reply = reply;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
}
