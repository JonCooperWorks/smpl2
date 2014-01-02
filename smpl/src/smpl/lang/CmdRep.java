package smpl.lang;

/**
 * CmdRep class
 *
 * AST node representation of an SMPL implementation of pascal - style
 * repeats
 */
public class CmdRep extends ASTNode2 {

    public CmdRep(ASTNode e1, ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitRep(this, arg);
    }

    public String toString() {
	return "repeat (" + getChild(0) + ")\n" + getChild(1);
    }
}
