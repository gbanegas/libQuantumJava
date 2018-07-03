package libQ.circuit;

import libQ.gates.EGateTypes;

public class QuantumOperation {

	private EGateTypes operationType;
	private int target;
	private int control1;
	private int control2;

	public QuantumOperation(EGateTypes operationType, int control1, int target) {
		this.operationType = operationType;
		this.control1 = control1;
		this.target = target;
		this.control2 = 0;
	}

	public QuantumOperation(EGateTypes operationType, int control1, int control2, int target) {
		this.operationType = operationType;
		this.control1 = control1;
		this.control2 = control2;
		this.target = target;
	}

	/**
	 * @return the operationType
	 */
	public EGateTypes getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType
	 *            the operationType to set
	 */
	public void setOperationType(EGateTypes operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(int target) {
		this.target = target;
	}

	/**
	 * @return the control1
	 */
	public int getControl1() {
		return control1;
	}

	/**
	 * @param control1
	 *            the control1 to set
	 */
	public void setControl1(int control1) {
		this.control1 = control1;
	}

	/**
	 * @return the control2
	 */
	public int getControl2() {
		return control2;
	}

	/**
	 * @param control2
	 *            the control2 to set
	 */
	public void setControl2(int control2) {
		this.control2 = control2;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Operation: " + this.operationType);
		strBuilder.append("\nControlBit1 : " + this.control1);
		if (control2 > 0)
			strBuilder.append("\nControlBit2: " + this.control2);

		strBuilder.append("\ntarget : " + this.target);

		return strBuilder.toString();
	}

}
