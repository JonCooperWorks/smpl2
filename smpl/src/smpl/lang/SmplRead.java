package smpl.lang;

/**
 * CmdRead class
 *
 * AST node representation of a read command, which reads in user
 * input
 */
public class SmplRead extends ASTNode0 {
    boolean rdInt = false;

    public SmplRead() {
	super();
    }

    public SmplRead(boolean i) {
	super(new SmplLit(i));
	rdInt = i;
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdRead(this, arg);
    }

    public String toString() {
	return "read" + (rdInt ? "int" : "") + "()";
    }
}
