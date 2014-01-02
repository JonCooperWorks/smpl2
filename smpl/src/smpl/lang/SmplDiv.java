package smpl.lang;

/**
 * Abstract Syntax Tree node for arithmetic division.
 *
 */
public class SmplDiv extends ASTNode2 {

    public SmplDiv(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpDiv(this, arg);
    }

    public String toString() {
	return getChild(0) + " / " + getChild(1);
    }
}
