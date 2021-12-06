package manager.dto;

import java.sql.Timestamp;

public class ManagerVO {
	private int clothes_id;			//옷의 등록번호
	private String clothes_kind;	//옷의 분류
	private String clothes_title;	//옷 이름
	private int clothes_price;		//옷 가격
	private short clothes_count;	//옷 재고수량
	private String clothes_size;		//옷 사이즈
	private String clothes_image;	//옷 이미지명
	private String clothes_content;	//옷 설명
	private Timestamp reg_date;		//옷의 등록날짜
	
	public int getClothes_id() {
		return clothes_id;
	}
	public void setClothes_id(int clothes_id) {
		this.clothes_id = clothes_id;
	}
	public String getClothes_kind() {
		return clothes_kind;
	}
	public void setClothes_kind(String clothes_kind) {
		this.clothes_kind = clothes_kind;
	}
	public String getClothes_title() {
		return clothes_title;
	}
	public void setClothes_title(String clothes_title) {
		this.clothes_title = clothes_title;
	}
	public int getClothes_price() {
		return clothes_price;
	}
	public void setClothes_price(int clothes_price) {
		this.clothes_price = clothes_price;
	}
	public short getClothes_count() {
		return clothes_count;
	}
	public void setClothes_count(short clothes_count) {
		this.clothes_count = clothes_count;
	}
	
	public String getClothes_size() {
		return clothes_size;
	}
	public void setClothes_size(String clothes_size) {
		this.clothes_size = clothes_size;
	}
	public String getClothes_image() {
		return clothes_image;
	}
	public void setClothes_image(String clothes_image) {
		this.clothes_image = clothes_image;
	}
	public String getClothes_content() {
		return clothes_content;
	}
	public void setClothes_content(String clothes_content) {
		this.clothes_content = clothes_content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
}
