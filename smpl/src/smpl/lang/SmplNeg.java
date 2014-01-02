package smpl.lang;

/**
 * Abstract Syntax Tree node for negative numbers.
 *
 */
public class SmplNeg extends ASTNode1 {

    public SmplNeg(ASTNode e1) {
	super(e1);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpNeg(this, arg);
    }

    public String toString() {
	return "-" + getChild();
    }
}
