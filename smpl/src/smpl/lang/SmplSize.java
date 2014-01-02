package smpl.lang;

/**
 * Abstract Syntax Tree node for finding the size of an expression
 *
 */
public class SmplSize extends ASTNode1 {

    public SmplSize(ASTNode e1) {
	super(e1);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdSize(this, arg);
    }

    public String toString() {
	return "size(" + getChild() + ")";
    }
}
