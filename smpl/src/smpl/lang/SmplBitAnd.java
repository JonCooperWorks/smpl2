package smpl.lang;

/**
 * Abstract Syntax Tree node for bitwise and.
 *
 */
public class SmplBitAnd extends ASTNode2 {

    public SmplBitAnd(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpBAnd(this, arg);
    }

    public String toString() {
	return getChild(0) + " & " + getChild(1);
    }
}
