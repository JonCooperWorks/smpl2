package smpl.lang;

/**
 * Abstract Syntax Tree node for arithmetic multiplication.
 *
 */
public class SmplMul extends ASTNode2 {

    public SmplMul(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpMul(this, arg);
    }

    public String toString() {
	return getChild(0) + " * " + getChild(1);
    }
}
