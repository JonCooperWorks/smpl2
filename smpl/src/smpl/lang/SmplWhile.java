package smpl.lang;

/**
 * CmdWhile class
 *
 * AST node representation of an SMPL implementation of thie while
 * loop
 */
public class SmplWhile extends ASTNode2 {

    public SmplWhile(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitWhile(this, arg);
    }

    public String toString() {
	return "while (" + getChild(0) + ")\n" + getChild(1);
    }
}
