package libQ.gates;

import java.math.BigInteger;
import java.util.List;

 class ThreadAddHash extends Thread {

	private List<BigInteger> list;
	private int hashw;

	public ThreadAddHash(List<BigInteger> listToAdd, int hashw) {
		this.list = listToAdd;
		this.hashw = hashw;

	}

	@Override
	public void run() {
		// System.out.println("Thread " + this.getId() + " starting....");
		BigInteger tmp = BigInteger.ONE.shiftLeft(hashw);

		for (BigInteger i = BigInteger.ZERO; i.compareTo(tmp) != 0; i = i.add(BigInteger.ONE)) {
			list.add(i);
		}
		// System.out.println("Thread " + this.getId() + " finished....");
	}

	public List<BigInteger> getList() {
		return this.list;
	}


}
