package smpl.lang;

// parent class for all smpl runtime exceptions

public class SmplException extends Exception {
    public SmplException() {
	super();
    }

    public SmplException(String msg) {
	super(msg);
    }

    public SmplException(String msg, Exception cause) {
	super(msg, cause);
    }
}
