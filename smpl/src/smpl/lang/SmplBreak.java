package smpl.lang;

/**
 * CmdBreak class
 *
 * AST node representation of an SMPL implementation of the command used to break out of a block of running code.
 */
public class SmplBreak extends ASTNode0 {

    public SmplBreak() {
	super();
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitBreak(this, arg);
    }

    public String toString() {
	return "break";
    }
}
