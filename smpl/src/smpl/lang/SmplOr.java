package smpl.lang;

/**
 * Abstract Syntax Tree node for logic OR.
 *
 */
public class SmplOr extends ASTNode2 {

    public SmplOr(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpOr(this, arg);
    }

    public String toString() {
	return getChild(0) + " or " + getChild(1);
    }
}

