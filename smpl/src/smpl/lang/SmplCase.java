package smpl.lang;

/**
 * CmdCase class
 *
 * AST node representation of an SMPL case expression
 */
public class SmplCase extends ASTNode1 {

    public SmplCase(ASTNode e) {
	super(e);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCase(this, arg);
    }

    public String toString() {
	return "case {\n" + getChild() + "}\n";
    }
}
