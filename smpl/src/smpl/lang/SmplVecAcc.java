package smpl.lang;

/**
 * Abstract Syntax Tree node for accessing an item in a vector, given
 * and expression that will evaluate to a valid index
 *
 */
public class SmplVecAcc extends ASTNode2 {

    public SmplVecAcc(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdVecAcc(this, arg);
    }

    public String toString() {
	try {
	    SmplVar v = (SmplVar) getChild(0);
	    return "" + v + "[" + getChild(1) + "]";
	} catch (ClassCastException cce) {
	    return "(" + getChild(0) + ")[" + getChild(1) + "]";
	}
    }
}
