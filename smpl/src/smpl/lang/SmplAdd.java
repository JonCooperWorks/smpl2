package smpl.lang;

/**
 * Abstract Syntax Tree node for arithmetic addition.
 *
 */
public class SmplAdd extends ASTNode2 {

    public SmplAdd(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpAdd(this, arg);
    }

    public String toString() {
	return getChild(0) + " + " + getChild(1);
    }
}
