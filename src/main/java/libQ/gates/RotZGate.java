/**
 * 
 */
package libQ.gates;

import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.register.QReg;

/**
 * @author Gustavo Banegas
 *
 */
class RotZGate implements IGate {
	@Override
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see libQ.gates.IGate#apply(libQ.register.QReg, int)
	 */
	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException, UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see libQ.gates.IGate#apply(libQ.register.QReg, int, int)
	 */
	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see libQ.gates.IGate#apply(libQ.register.QReg, int, int, int)
	 */
	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)
			throws OperationNotPermittedException {
		// TODO Auto-generated method stub
		return null;
	}

}
