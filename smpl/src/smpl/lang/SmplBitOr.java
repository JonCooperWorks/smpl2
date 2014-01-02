package smpl.lang;

/**
 * Abstract Syntax Tree node for bitwise or.
 *
 */
public class SmplBitOr extends ASTNode2 {

    public SmplBitOr(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpBOr(this, arg);
    }

    public String toString() {
	return getChild(0) + " | " + getChild(1);
    }
}
