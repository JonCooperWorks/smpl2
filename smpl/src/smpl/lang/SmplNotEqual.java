package smpl.lang;

/**
 * Abstract Syntax Tree node for logic NOT EQUAL.
 *
 */
public class SmplNotEqual extends ASTNode2 {

    public SmplNotEqual(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpNE(this, arg);
    }

    public String toString() {
	return getChild(0) + " != " + getChild(1);
    }
}

