package dev.exception;

public class PlatException extends RuntimeException {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 5287545431076389727L;

	public PlatException() {
	}

	public PlatException(String message) {
		super(message);
	}

	public PlatException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlatException(Throwable cause) {
		super(cause);
	}

	public PlatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
