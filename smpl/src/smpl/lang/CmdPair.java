package smpl.lang;

/**
 * Abstract Syntax Tree node for creation of an SMPL pair
 *
 */
public class CmdPair extends ASTNode2 {

    public CmdPair(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdPair(this, arg);
    }

    public String toString() {
	return "pair(" + getChild(0) + ", " + getChild(1) + ")";
    }
}
