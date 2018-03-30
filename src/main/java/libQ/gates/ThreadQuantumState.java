package libQ.gates;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import libQ.register.QReg;
import libQ.register.utils.QuantumUtils;

public class ThreadQuantumState extends Thread {

	private QReg reg;
	private BigInteger factor;
	private List<BigInteger> list;

	public ThreadQuantumState(QReg reg, BigInteger factor) {
		this.reg = reg;
		this.factor = factor;
		this.list = new ArrayList<>();
	}

	@Override
	public void run() {
		for (int i = 0; i < reg.getSize(); i++) {
			BigInteger tmp_2 = reg.getState().get(i).xor(factor);
			BigInteger j = QuantumUtils.getQState(tmp_2, reg);
			list.add(j);
		}
	}

	public List<BigInteger> getList() {
		return this.list;
	}

}
