package libQ.gates;

import java.rmi.UnexpectedException;

import org.apache.commons.math3.complex.Complex;

import exceptions.OperationNotPermitted;
import libQ.register.QReg;
import libQ.utils.GateApplication;
import libQ.utils.QMatrix;

public class HadamardGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws UnexpectedException {
		QMatrix matrix = new QMatrix(2, 2);
		matrix.getT()[0] = new Complex(Math.sqrt(1.0 / 2));
		matrix.getT()[1] = new Complex(Math.sqrt(1.0 / 2));
		matrix.getT()[2] = new Complex(Math.sqrt(1.0 / 2));
		matrix.getT()[3] = new Complex(-Math.sqrt(1.0 / 2));
		GateApplication.applyQMatrix(targetQBit, matrix, reg);
		
		return true;
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermitted {
		throw new OperationNotPermitted("Hadamatrixard just need one bit");

	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit) throws OperationNotPermitted {
		throw new OperationNotPermitted("Hadamatrixard just need one control bit");
	}

}
