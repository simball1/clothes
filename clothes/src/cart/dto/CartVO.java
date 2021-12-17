package cart.dto;

public class CartVO {
	
	private int cart_id; //장바구니의 아이디
	private String buyer; //구매자
	private int clothes_id; //구매된 옷의 아이디
	private String clothes_title;//구매된 옷 이름
	private int  buy_price;//판매가
	private byte buy_count; //판매수량
	private String clothes_size; // 옷 사이즈
	private String clothes_image;//옷 이미지
	
	
	
	public String getClothes_size() {
		return clothes_size;
	}
	public void setClothes_size(String clothes_size) {
		this.clothes_size = clothes_size;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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

	}

