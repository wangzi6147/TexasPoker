package z.texas.commons;

import java.util.ArrayList;

public class PlayerBean {
	private String address;
	private String state;
	private String name;
	private int money;
	private int bet;
	private ArrayList<CardBean> hands;
	private int pos;
	
	public PlayerBean(){
		setHands(new ArrayList<CardBean>());
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public ArrayList<CardBean> getHands() {
		return hands;
	}
	public void setHands(ArrayList<CardBean> hands) {
		this.hands = hands;
	}
}
