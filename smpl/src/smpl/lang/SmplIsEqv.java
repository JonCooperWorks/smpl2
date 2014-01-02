package smpl.lang;

/**
 * Abstract Syntax Tree node for finding the size of an expression
 * there is an extra variable, which is used to determine whether eq?
 * or eqv? was called. (no sense in making two classes which did
 * more-or-less the same thing.
 *
 */
public class SmplIsEqv extends  ASTNode2{
    boolean exact;

    public SmplIsEqv(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public SmplIsEqv(ASTNode e1, ASTNode e2, boolean exct) {
	super(e1, e2);
	exact = exct; 
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitIsEqv(this, arg);
    }

    public String toString() {
	return "eq" + (exact ? "" : "v" ) + "?(" +
	    getChild(0) + ", " + getChild(1) + ")";
    }
}
