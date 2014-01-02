package smpl.lang;

/**
 * CmdProcCall class
 *
 * AST node representing a procedure call
 */
public class SmplProcCall extends ASTNode2{
    boolean call = false;

    public SmplProcCall(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public SmplProcCall(ASTNode e1,  ASTNode e2, boolean c) {
	super(e1, e2);
	call = c;
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitSmplProcCall(this, arg);
    }

    public String toString() {
	if (call)
	    return "call(" + getChild(0) + ", " + getChild(1) + ")";
	else
	    return ("" + getChild(0) + getChild(1));
    }
}
