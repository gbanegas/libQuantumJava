package libQ.circuit;

public class OperationNode {

	private int qBitPos;
	private String uidOperation;

	public OperationNode(int qBitPos, String uidOperation) {
		this.qBitPos = qBitPos;
		this.uidOperation = uidOperation;

	}

	/**
	 * @return the qBitPos
	 */
	public int getqBitPos() {
		return qBitPos;
	}

	/**
	 * @param qBitPos
	 *            the qBitPos to set
	 */
	public void setqBitPos(int qBitPos) {
		this.qBitPos = qBitPos;
	}

	/**
	 * @return the uidOperation
	 */
	public String getUidOperation() {
		return uidOperation;
	}

	/**
	 * @param uidOperation
	 *            the uidOperation to set
	 */
	public void setUidOperation(String uidOperation) {
		this.uidOperation = uidOperation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(qBitPos);
		// builder.append(" op: " + uidOperation);
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!OperationNode.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final OperationNode other = (OperationNode) obj;
		if (qBitPos != other.qBitPos) {
			return false;
		}
		if (!this.uidOperation.equals(other.uidOperation)) {
			return false;
		}
		return true;
	}

}
