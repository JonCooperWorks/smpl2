package smpl.lang;

/**
 * CmdLet class
 *
 * AST node representation of an SMPL let expression
 */
public class SmplLet extends ASTNode2 {

    public SmplLet(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitLet(this, arg);
    }

    public String toString() {
	return "let (" + getChild(0) + ")\n" + getChild(1);
    }
}
