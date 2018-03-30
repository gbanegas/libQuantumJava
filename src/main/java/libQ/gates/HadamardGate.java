package libQ.gates;

import java.rmi.UnexpectedException;

import org.apache.commons.math3.complex.Complex;

import libQ.exceptions.OperationNotPermittedException;
import libQ.exceptions.SizeHandleException;
import libQ.register.QReg;
import libQ.register.utils.GateApplication;
import libQ.register.utils.QMatrix;

/**
 * 
 * Hadamard Gate:
 * The gate put the register in a quantum superposition.
 * @author Gustavo Banegas
 *
 */
class HadamardGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws UnexpectedException, SizeHandleException {

		if (reg.getWidth() >= 17) {
			throw new SizeHandleException("Too many qubits for computing hadamard.");

		}
		ThreadAddHash threadHash = new ThreadAddHash(reg.getHash(), reg.getHashw());
		ThreadManager.getInstance().addThread(threadHash);
		while (threadHash.isAlive()) {
			;
		}

		reg.setHash(threadHash.getList());
		QMatrix matrix = new QMatrix(2, 2);
		matrix.getT()[0] = new Complex(Math.sqrt(1.0 / 2));
		matrix.getT()[1] = new Complex(Math.sqrt(1.0 / 2));
		matrix.getT()[2] = new Complex(Math.sqrt(1.0 / 2));
		matrix.getT()[3] = new Complex(-Math.sqrt(1.0 / 2));
		GateApplication.applyQMatrix(targetQBit, matrix, reg);

		return true;
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("HadamardGate just need one bit");

	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)
			throws OperationNotPermittedException {
		throw new OperationNotPermittedException("HadamardGate just need one control bit");
	}

}
