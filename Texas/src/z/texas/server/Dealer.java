package z.texas.server;

import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;

import z.texas.commons.CardBean;
import z.texas.commons.PlayerBean;
import z.texas.commons.TexasBean;

public class Dealer {
	private static final int MAX_NUM = 2;
	private ArrayList<CardBean> pile; // 牌堆
	private Gson gson;
	private TexasBean texasBean;
	private TexasBean curBean;
	private int[] position;
	private TaskManager taskManager;

	public Dealer(TaskManager taskManager) {
		this.taskManager = taskManager;
		gson = new Gson();
		texasBean = new TexasBean();
		curBean = new TexasBean();
		pile = new ArrayList<CardBean>();
		position = new int[MAX_NUM];
		for (int i = 0; i < MAX_NUM; i++) {
			curBean.getOthers().add(new PlayerBean());
			position[i] = 0;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				pile.add(new CardBean(j, i));
			}
		}
	}

	// 发牌
	private ArrayList<CardBean> dealCard(int num) {
		ArrayList<CardBean> cards = new ArrayList<CardBean>();
		for (int i = 0; i < num; i++) {
			int index = new Random().nextInt(pile.size());
			cards.add(pile.get(index));
			pile.remove(index);
		}
		return cards;
	}

	public void parse(String json, String address) {
		texasBean = gson.fromJson(json, TexasBean.class);
		texasBean.getPlayer().setAddress(address);
		int emptyPos = getEmptyPos();
		switch (texasBean.getPlayer().getState()) {
		case "connect":
			if (emptyPos == -1) {
				texasBean.getPlayer().setPos(-1);
				curBean.setPlayer(texasBean.getPlayer());
				send();
				break;
			}
			texasBean.getPlayer().setPos(emptyPos);
			curBean.setPlayer(texasBean.getPlayer());
			curBean.getOthers().set(emptyPos, texasBean.getPlayer());
			send();
			position[emptyPos] = 1;
			break;
		case "ready":
			curBean.getOthers().set(texasBean.getPlayer().getPos(), texasBean.getPlayer());
			if (allReady()) {
				allStart();
			}
			break;
		default:
			break;
		}
	}

	private void send() {
		taskManager.send(gson.toJson(curBean), curBean.getPlayer().getAddress());
	}

	private void allStart() {
		for(int i = 0; i<curBean.getOthers().size(); i++){
			PlayerBean playerBean = curBean.getOthers().get(i);
			if (playerBean.getState() != null) {
				playerBean.setState("start");
				playerBean.setHands(dealCard(2));
				curBean.setPlayer(playerBean);
				send();
			}
		}
	}

	private boolean allReady() {
		int playerNum = 0;
		for (PlayerBean playerBean : curBean.getOthers()) {
			String state = playerBean.getState();
			if (state != null && state.equals("ready"))
				playerNum++;
		}
		if(playerNum>1)
			return true;
		else {
			return false;
		}
	}

	private int getEmptyPos() {
		for (int i = 0; i < MAX_NUM; i++) {
			if (position[i] == 0) {
				return i;
			}
		}
		return -1;
	}
}
