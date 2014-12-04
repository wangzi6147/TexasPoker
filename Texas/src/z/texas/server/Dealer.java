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
			curBean.getPlayers().add(new PlayerBean());
			position[i] = 0;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				pile.add(new CardBean(j, i));
			}
		}
	}

	// 发牌
	public ArrayList<CardBean> dealCard(int num) {
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
		texasBean.getPlayers().get(texasBean.getPos()).setAddress(address);
		int emptyPos = getEmptyPos();
		switch (texasBean.getPlayers().get(texasBean.getPos()).getState()) {
		case "connect":
			if (emptyPos == -1) {
				curBean.setPos(-1);
				taskManager.send(gson.toJson(curBean), texasBean.getPlayers()
						.get(texasBean.getPos()).getAddress());
				break;
			}
			curBean.getPlayers().set(emptyPos,
					texasBean.getPlayers().get(texasBean.getPos()));
			curBean.setPos(emptyPos);
			taskManager
					.send(gson.toJson(curBean),
							texasBean.getPlayers().get(texasBean.getPos())
									.getAddress());
			position[emptyPos] = 1;
			break;
		case "ready":
			curBean.getPlayers().set(texasBean.getPos(),
					texasBean.getPlayers().get(texasBean.getPos()));
			if (allReady()) {
				allStart();
			}
			break;
		default:
			break;
		}
	}

	private void allStart() {
		for (PlayerBean playerBean : curBean.getPlayers()) {
			if (playerBean.getState() != null) {
				playerBean.setState("start");
				playerBean.setCards(dealCard(2));
				taskManager.send(gson.toJson(curBean), texasBean.getPlayers()
						.get(texasBean.getPos()).getAddress());
			}
		}
	}

	private boolean allReady() {
		int playerNum = 0;
		for (PlayerBean playerBean : curBean.getPlayers()) {
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
