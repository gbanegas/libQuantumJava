package libQ.parallel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import libQ.register.QReg;
import libQ.register.utils.QuantumUtils;

public class ThreadApplyQMatrix extends Thread {

	private QReg reg;
	private int target;
	private List<BigInteger> list;

	public ThreadApplyQMatrix(QReg reg, int targetQBit) {
		this.reg = reg;
		this.target = targetQBit;
		list = new ArrayList<>();
	}

	@Override
	public void run() {
		//System.out.println("Thread " + this.getId() + " starting....");
		for (int i = 0; i < reg.getSize(); i++) {
			/* determine whether XORed basis state already exists */
			BigInteger tmp = reg.getState().get(i).xor(BigInteger.ONE.shiftLeft(target));
			BigInteger value;
			value = QuantumUtils.getQState(tmp, reg);
			list.add(value);
		}
		//System.out.println("Thread " + this.getId() + " finished....");
	}

	public List<BigInteger> getList() {
		return this.list;
	}

}
