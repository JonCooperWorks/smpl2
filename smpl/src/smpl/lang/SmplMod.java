package smpl.lang;

/**
 * Abstract Syntax Tree node for arithmetic modulo operation.
 *
 */
public class SmplMod extends ASTNode2 {

    public SmplMod(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpMod(this, arg);
    }

    public String toString() {
	return getChild(0) + " % " + getChild(1);
    }
}
