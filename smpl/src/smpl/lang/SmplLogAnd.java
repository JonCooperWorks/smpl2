package smpl.lang;

/**
 * Abstract Syntax Tree node for logic AND.
 *
 */
public class SmplLogAnd extends ASTNode2 {

    public SmplLogAnd(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpAnd(this, arg);
    }

    public String toString() {
	return getChild(0) + " and " + getChild(1);
    }
}

