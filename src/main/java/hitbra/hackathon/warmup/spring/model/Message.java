package hitbra.hackathon.warmup.spring.model;

public class Message {

	private String message;

	public Message() {
		super();
	}

	public Message(String message) {
		this();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}

}
