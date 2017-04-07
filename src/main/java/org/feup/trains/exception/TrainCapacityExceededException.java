package org.feup.trains.exception;

public class TrainCapacityExceededException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 994135550479600889L;

	/**
	 * 
	 */

	public TrainCapacityExceededException() {
		super();
	}

	public TrainCapacityExceededException(String message) {
		super(message);
	}
	
}
