package smpl.lang;

/**
 * Abstract Syntax Tree node for logic LESS THAN.
 *
 */
public class SmplLThan extends ASTNode2 {

    public SmplLThan(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpLT(this, arg);
    }

    public String toString() {
	return getChild(0) + " < " + getChild(1);
    }
}

