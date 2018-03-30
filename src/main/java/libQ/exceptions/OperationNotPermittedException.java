package libQ.exceptions;

/**
 * 
 * There is something wrong with the parameters.
 * @author Gustavo Banegas
 *
 */
public class OperationNotPermittedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationNotPermittedException(String msg) {
		super(msg);
	}

}
