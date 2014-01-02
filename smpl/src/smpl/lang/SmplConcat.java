package smpl.lang;

/**
 * Abstract Syntax Tree node for list concatenation.
 *
 */
public class SmplConcat extends ASTNode2 {

    public SmplConcat(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpCat(this, arg);
    }

    public String toString() {
	return getChild(0) + " @ " + getChild(1);
    }
}
