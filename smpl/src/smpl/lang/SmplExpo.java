package smpl.lang;

/**
 * Abstract Syntax Tree node for arithmetic exponentiation.
 *
 */
public class SmplExpo extends ASTNode2 {

    public SmplExpo(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpPow(this, arg);
    }

    public String toString() {
	return getChild(0) + " ^ " + getChild(1);
    }
}
