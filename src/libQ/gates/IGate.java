package libQ.gates;

import java.rmi.UnexpectedException;

import exceptions.OperationNotPermittedException;
import libQ.register.QReg;

public interface IGate {
	
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException, UnexpectedException;
	
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException;
	
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)throws OperationNotPermittedException;

}
