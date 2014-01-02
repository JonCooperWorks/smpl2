package smpl.lang;

/**
 * CmdPrint class
 *
 * AST node representation of a print command
 */
public class SmplPrint extends ASTNode1 {
    boolean nl = false;

    public SmplPrint(ASTNode e1) {
	super(e1);
    }

    public SmplPrint(ASTNode e1, boolean n) {
	super(e1);
	nl = n;
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdPrint(this, arg);
    }

    public String toString() {
	return "print" + (nl ? "ln" : "") + "(" + getChild() + ")";
    }
}
