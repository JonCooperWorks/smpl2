package smpl.lang;

/**
 * SmplEvalException class
 *
 * try to catch errors in evaluation as soon as possible, and give the
 * user as much assistance as possible in finding the error
 */
public class SmplEvalException extends SmplException {

    ASTNode problem;

    public SmplEvalException() {
	super();
    }

    public SmplEvalException(String msg) {
	super(msg);
    }

    public SmplEvalException(String msg, ASTNode exp) {
	super(msg);
	problem = exp;
    }

    public ASTNode getExp() {
	return problem;
    }
}
