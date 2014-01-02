package smpl.lang;

/**
 * Abstract Syntax Tree node for accessing the tail of an SMPL list
 *
 */
public class SmplCdr extends ASTNode1 {

    public SmplCdr(ASTNode e1) {
	super(e1);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdCdr(this, arg);
    }

    public String toString() {
	return "cdr(" + getChild() + ")";
    }
}
