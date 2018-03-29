package libQ.gates;

import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.exceptions.SizeHandleException;
import libQ.register.QReg;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public interface IGate {
	
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException, UnexpectedException, SizeHandleException;
	
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException;
	
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)throws OperationNotPermittedException;
	
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException;;

}
