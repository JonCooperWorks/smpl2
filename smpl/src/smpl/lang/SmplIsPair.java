package smpl.lang;

/**
 * Abstract Syntax Tree node for finding the size of an expression
 *
 */
public class SmplIsPair extends ASTNode1 {

    public SmplIsPair(ASTNode e1) {
	super(e1);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitIsPair(this, arg);
    }

    public String toString() {
	return "pair?(" + getChild() + ")";
    }
}
