package z.texas.commons;

import java.util.ArrayList;

public class TexasBean {
	private PlayerBean player;
	private ArrayList<PlayerBean> others;
	private ArrayList<CardBean> flops;
	private CardBean turn;
	private CardBean river;

	public TexasBean(){
		others = new ArrayList<PlayerBean>();
		flops = new ArrayList<CardBean>();
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

	public ArrayList<PlayerBean> getOthers() {
		return others;
	}

	public void setOthers(ArrayList<PlayerBean> others) {
		this.others = others;
	}

	public PlayerBean getPlayer() {
		return player;
	}

	public void setPlayer(PlayerBean player) {
		this.player = player;
	}

}
