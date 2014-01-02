package smpl.lang;

/**
 * Abstract Syntax Tree node for arithmetic subtraction.
 *
 */
public class SmplMinus extends ASTNode2 {

    public SmplMinus(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpSub(this, arg);
    }

    public String toString() {
	return getChild(0) + " - " + getChild(1);
    }
}
