package smpl.lang;

/**
 * Abstract Syntax Tree node for logic LESS THAN OR EQUAL.
 *
 */
public class SmplLEqual extends ASTNode2 {

    public SmplLEqual(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpLE(this, arg);
    }

    public String toString() {
	return getChild(0) + " <= " + getChild(1);
    }
}

