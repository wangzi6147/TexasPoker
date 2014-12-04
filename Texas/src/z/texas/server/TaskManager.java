package z.texas.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TaskManager {
	private HashMap<String, Task> tasks;

	public TaskManager() {
		tasks = new HashMap<String, Task>();
	}

	public void add(Task task, String address) {
		tasks.put(address, task);
	}

	public void closeAll() {
		Iterator<Entry<String, Task>> iter = tasks.entrySet().iterator();
		while(iter.hasNext()){
		        Entry<String, Task> next = iter.next();
		        next.getValue().setStop(true);
		}
	}
	
	public void send(String json, String address) {
		tasks.get(address).send(json);
	}
}
