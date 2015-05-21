package utils;

public class Error<X> {
	private final X val;
	private final String message;
	
	private Error(X val, String message){
		this.val = val;
		this.message = message;
	}
	
	public static <X> Error<X> newMessage(X val, String errorMessage, Object... args){
		String formattedMessage = null;
		if( errorMessage != null){
			formattedMessage = String.format(errorMessage, args);
		}
		return new Error<>(val, formattedMessage);
	}
	
	public X getVal(){
		return this.val;
	}
	
	public String getError(){
		return this.message;
	}
	
	public boolean hasError(){
		return this.message != null;
	}
}
