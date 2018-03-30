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

	/**
	 * 
	 * @param reg
	 * @param targetQBit
	 * @return
	 * @throws OperationNotPermittedException
	 * @throws UnexpectedException
	 * @throws SizeHandleException
	 */
	public Boolean apply(QReg reg, int targetQBit)
			throws OperationNotPermittedException, UnexpectedException, SizeHandleException;

	/**
	 * 
	 * @param reg
	 * @param controlQBit
	 * @param targetQBit
	 * @return
	 * @throws OperationNotPermittedException
	 */
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException;

	/**
	 * 
	 * @param reg
	 * @param controlQBit1
	 * @param controlQBit2
	 * @param targetQBit
	 * @return
	 * @throws OperationNotPermittedException
	 */
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)
			throws OperationNotPermittedException;

	/**
	 * 
	 * @param reg
	 * @param target
	 * @param gamma
	 * @return
	 * @throws OperationNotPermittedException
	 * @throws UnexpectedException
	 */
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException;;

}
