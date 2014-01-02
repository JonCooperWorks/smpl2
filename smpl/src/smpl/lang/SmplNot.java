package smpl.lang;

/**
 * Abstract Syntax Tree node for logic NOT.
 *
 */
public class SmplNot extends ASTNode1 {

    public SmplNot(ASTNode e) {
	super(e);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpNot(this, arg);
    }

    public String toString() {
	return "not " + getChild();
    }
}

