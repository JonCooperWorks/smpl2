package smpl.lang;

/**
 * Abstract Syntax Tree node for logic GREATER THAN OR EQUAL.
 *
 */
public class SmplGEqual extends ASTNode2 {

    public SmplGEqual(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpGE(this, arg);
    }

    public String toString() {
	return getChild(0) + " >= " + getChild(1);
    }
}

