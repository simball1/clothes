package buy.dto;

import java.sql.Timestamp;

public class BuyVO {
	
	private Long buy_id;//구매아이디
	private String buyer;//구매자
	private int clothes_id;//구매된 옷 아이디
	private String clothes_title;//구매된 옷 명
	private int buy_price;//판매가
	private byte buy_count;//판매수량
	private String clothes_size;//구매된 옷 사이즈
	private String clothes_image;//옷 이미지
	private Timestamp buy_date;//구매일자
	private String deliveryName;//배송지
	private String deliveryTel ;//배송지 전화번호
	private String deliveryAddress;//배송지 주소

	
	public String getClothes_size() {
		return clothes_size;
	}
	public void setClothes_size(String clothes_size) {
		this.clothes_size = clothes_size;
	}
	public Long getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
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
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public byte getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(byte buy_count) {
		this.buy_count = buy_count;
	}
	public String getClothes_image() {
		return clothes_image;
	}
	public void setClothes_image(String clothes_image) {
		this.clothes_image = clothes_image;
	}
	public Timestamp getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}

	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}