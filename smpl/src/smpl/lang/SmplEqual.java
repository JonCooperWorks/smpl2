package smpl.lang;

/**
 * Abstract Syntax Tree node for logic EQUAL.
 *
 */
public class SmplEqual extends ASTNode2 {

    public SmplEqual(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpEQ(this, arg);
    }

    public String toString() {
	return getChild(0) + " = " + getChild(1);
    }
}

