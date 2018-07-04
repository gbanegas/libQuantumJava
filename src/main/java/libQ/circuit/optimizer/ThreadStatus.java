package libQ.circuit.optimizer;

public class ThreadStatus extends Thread {

	boolean waiting = true;
	boolean ready = false;

	ThreadStatus() {
	}

	public void run() {
		String thrdName = Thread.currentThread().getName();
		System.out.println("Starting optimizer...");
		System.out.print("waiting");
		try {

			while (waiting) {
				System.out.print(".");
				Thread.sleep(100);
			}
			System.out.println("");
			// System.out.println("waiting...");
			startWait();
			Thread.sleep(1000);
		} catch (Exception exc) {
			System.out.println(thrdName + " interrupted.");
		}
		System.out.println("\nFinishing optimizer...");
	}

	synchronized void startWait() {
		try {
			while (!ready) {
				System.out.print(".");
				wait();
				Thread.sleep(1000);
			}
		} catch (InterruptedException exc) {
			System.out.println("wait() interrupted");
		}
	}

	synchronized void notice() {
		ready = true;
		waiting = false;
		notify();
	}

}
