package smpl.lang;

/** Class to signal that the condition for a case is false; when the
 * signal is caught, the evaluator will move to the next available
 * case; */
public class SmplBadCaseException extends SmplException {
    
    public SmplBadCaseException() {
	super();
    }
}
