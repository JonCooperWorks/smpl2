package smpl.lang;

/**
 * CmdSubstr class
 *
 * AST node representing an SMPL substring command
 */
public class SmplSubstr extends ASTNode3 {

    public SmplSubstr(ASTNode e1,  ASTNode e2, ASTNode e3) {
	super(e1, e2, e3);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdSubstr(this, arg);
    }

    public String toString() {
	return "substr(" + getChild(0) + ", "
	    + getChild(1) + ", "
	    + getChild(2) + ")";
    }
}
