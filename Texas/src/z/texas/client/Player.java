package z.texas.client;

import java.util.ArrayList;

import com.google.gson.Gson;

import z.texas.TexasFrame;
import z.texas.TexasUI;
import z.texas.commons.CardBean;
import z.texas.commons.PlayerBean;
import z.texas.commons.TexasBean;

public class Player {
	private Client client;
	private ArrayList<CardBean> hands;
	private Gson gson;
	private TexasBean texasBean;
	private TexasFrame texasFrame;

	public Player(TexasFrame texasFrame) {
		this.texasFrame = texasFrame;
		texasBean = new TexasBean();
		gson = new Gson();
		client = new Client(this);
		hands = new ArrayList<CardBean>();
	}

	public ArrayList<CardBean> getHands() {
		return hands;
	}

	public boolean connect(String host, String name, int port) {
		PlayerBean playerBean = new PlayerBean();
		playerBean.setName(name);
		playerBean.setState("connect");
		texasBean.setPlayer(playerBean);
		if (client.connect(host, port)) {
			client.send(gson.toJson(texasBean));
			return true;
		}
		return false;
	}

	public void ready() {
		texasBean.getPlayer().setState("ready");
		String json = gson.toJson(texasBean);
		client.send(json);
	}

	public void parse(String str) {
		texasBean = gson.fromJson(str, TexasBean.class);
		PlayerBean playerBean = texasBean.getPlayer();
		switch (playerBean.getState()) {
		case "watch":
			texasFrame.print("旁观");
		case "start":
			texasFrame.print("开始");
			texasFrame.showPlayers(texasBean);
			break;
		case "choose":
			int beg = texasBean.getMaxBet() - playerBean.getBet();
			if (beg == 0) {
				texasFrame.print("check or raise(at least "
						+ texasBean.getBigBlind() + ") or fold");
			} else {
				texasFrame.print("call "
						+ (texasBean.getMaxBet() - playerBean.getBet())
						+ "or raise(at least "
						+ (texasBean.getMaxBet() - playerBean.getBet() + texasBean
								.getBigBlind()) + ") or fold");
			}
			texasFrame.showPlayers(texasBean);
			break;
		default:
			break;
		}
	}

	public void quit() {
		client.setStop(true);
	}

	public void call() {
		PlayerBean playerBean = texasBean.getPlayer();
		playerBean.setMoney(playerBean.getMoney()
				- (texasBean.getMaxBet() - playerBean.getBet()));
		playerBean.setBet(texasBean.getMaxBet());
		playerBean.setState("call");
		texasFrame.showPlayers(texasBean);
		client.send(gson.toJson(texasBean));
	}

	public void check() {
		texasBean.getPlayer().setState("check");
		client.send(gson.toJson(texasBean));
	}

}
