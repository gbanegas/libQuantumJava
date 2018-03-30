package libQ.exceptions;

/**
 * 
 * Exception for when something that should work is not working properly.
 * 
 * @author Gustavo Banegas
 *
 */
public class InexpectedErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InexpectedErrorException(String msg) {
		super(msg);
	}

}
