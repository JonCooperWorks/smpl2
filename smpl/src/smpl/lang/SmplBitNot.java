package smpl.lang;

/**
 * Abstract Syntax Tree node for bitwise not.
 *
 */
public class SmplBitNot extends ASTNode1 {

    public SmplBitNot(ASTNode e1) {
	super(e1);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpBNot(this, arg);
    }

    public String toString() {
	return "~ " + getChild();
    }
}
