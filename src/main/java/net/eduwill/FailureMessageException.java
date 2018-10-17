package net.eduwill;

public class FailureMessageException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message; 
	
	public FailureMessageException(String message)
    {
        super(message);
        this.message = message;
    }

	public String getMessage() {
		return message;
	}
}

