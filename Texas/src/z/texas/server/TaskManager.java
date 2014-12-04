package z.texas.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TaskManager {
	private HashMap<String, ArrayList<Task>> tasks;

	public TaskManager() {
		tasks = new HashMap<String, ArrayList<Task>>();
	}

	public void add(Task task, String address) {
		if(!tasks.containsKey(address))
			tasks.put(address, new ArrayList<Task>());
		tasks.get(address).add(task);
	}

	public void closeAll() {
		Iterator<Entry<String, ArrayList<Task>>> iter = tasks.entrySet().iterator();
		while(iter.hasNext()){
		        Entry<String, ArrayList<Task>> next = iter.next();
		        for(int i = 0;i<next.getValue().size();i++){
		        	next.getValue().get(i).setStop(true);	        	
		        }
		}
	}
	
	public void send(String json, String address) {
		for(Task task : tasks.get(address)){
			task.send(json);
		}
	}
}
