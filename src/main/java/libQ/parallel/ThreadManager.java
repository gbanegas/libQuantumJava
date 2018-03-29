package libQ.parallel;

import java.util.HashMap;
import java.util.Map;

public class ThreadManager {

	private Map<Long, Thread> threadMap;

	private static ThreadManager instance;

	public ThreadManager() {
		threadMap = new HashMap<Long, Thread>();

	}

	public static ThreadManager getInstance() {
		if (instance == null)
			instance = new ThreadManager();

		return instance;
	}

	public Long addThread(Thread thread) {
		if (!thread.isAlive())
			thread.start();
		this.threadMap.put(thread.getId(), thread);
		return thread.getId();

	}

	public Thread getThread(Long id) {
		return threadMap.get(id);
	}

}
