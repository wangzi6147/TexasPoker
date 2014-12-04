package z.texas.commons;

import java.util.ArrayList;

public class TexasBean {
	private ArrayList<PlayerBean> players;
	private ArrayList<CardBean> flops;
	private CardBean turn;
	private CardBean river;
	private int pos;

	public TexasBean(){
		players = new ArrayList<PlayerBean>();
		flops = new ArrayList<CardBean>();
	}
	
	public ArrayList<PlayerBean> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerBean> players) {
		this.players = players;
	}
	
	public ArrayList<CardBean> getFlops() {
		return flops;
	}

	public void setFlops(ArrayList<CardBean> flops) {
		this.flops = flops;
	}

	public CardBean getTurn() {
		return turn;
	}

	public void setTurn(CardBean turn) {
		this.turn = turn;
	}

	public CardBean getRiver() {
		return river;
	}

	public void setRiver(CardBean river) {
		this.river = river;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

}
