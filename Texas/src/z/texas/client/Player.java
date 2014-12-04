package z.texas.client;

import java.util.ArrayList;

import com.google.gson.Gson;

import z.texas.TexasUI;
import z.texas.commons.CardBean;
import z.texas.commons.PlayerBean;
import z.texas.commons.TexasBean;

public class Player {
	private Client client;
	private ArrayList<CardBean> hands;
	private Gson gson;
	private TexasBean texasBean;
	private TexasUI texasUI;

	public Player(TexasUI texasUI) {
		this.texasUI = texasUI;
		texasBean = new TexasBean();
		gson = new Gson();
		client = new Client(this);
		hands = new ArrayList<CardBean>();
	}

	public ArrayList<CardBean> getHands() {
		return hands;
	}

	public boolean connect(String host, String name, int port) {
		texasBean.setPos(0);
		PlayerBean playerBean = new PlayerBean();
		playerBean.setName(name);
		playerBean.setState("connect");
		ArrayList<PlayerBean> playerBeans = new ArrayList<PlayerBean>();
		playerBeans.add(playerBean);
		texasBean.setPlayers(playerBeans);
		if(client.connect(host, port)){
			client.send(gson.toJson(texasBean));
			return true;
		}
		return false;
	}

	public void ready() {
		texasBean.getPlayers().get(texasBean.getPos()).setState("ready");
		String json = gson.toJson(texasBean);
		client.send(json);
	}

	public void parse(String str) {
		texasBean = gson.fromJson(str, TexasBean.class);
		if(texasBean.getPos()==-1){
			texasUI.print("旁观");
			return;
		}
		PlayerBean playerBean = texasBean.getPlayers().get(texasBean.getPos());
		switch (playerBean.getState()) {
		case "start":
			texasUI.showCards(playerBean);
			break;

		default:
			break;
		}
	}

	public void quit() {
		client.setStop(true);
	}

}
