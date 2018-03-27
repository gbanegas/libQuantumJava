package libQ.gates;

import exceptions.OperationNotPermitted;
import libQ.register.QReg;

public interface IGate {
	
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermitted;
	
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermitted;
	
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)throws OperationNotPermitted;

}
