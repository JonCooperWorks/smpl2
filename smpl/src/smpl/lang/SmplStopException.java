package smpl.lang;

/** Class to signal an abnormal termination of computation (as when
    a brek command is executed). */
public class SmplStopException extends SmplException {
    
    public SmplStopException() {
	super();
    }
}
