package ivolapuma.judodatamining.exception;

public class JudoDataMiningException extends Exception {
	private static final long serialVersionUID = 1L;

	public JudoDataMiningException(String message, Exception e) {
		super(message, e);
	}

	public JudoDataMiningException(String message) {
		super(message);
	}

}
