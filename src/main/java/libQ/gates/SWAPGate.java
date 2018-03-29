package libQ.gates;

import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.register.QReg;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public class SWAPGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException, UnexpectedException {
		throw new OperationNotPermittedException("SWAPGate uses two qubits");
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		//TODO: implement using CNOTs or using matrix?
		return null;
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)
			throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SWAPGate uses two qubits");
	}

	@Override
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException {
		throw new OperationNotPermittedException("SWAPGate uses two qubits");
	}

}
