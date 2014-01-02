package smpl.lang;

/**
 * Abstract Syntax Tree node for logic GREATER THAN.
 *
 */
public class SmplGThan extends ASTNode2 {

    public SmplGThan(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpGT(this, arg);
    }

    public String toString() {
	return getChild(0) + " > " + getChild(1);
    }
}

