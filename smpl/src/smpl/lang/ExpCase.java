package smpl.lang;

/**
 * ExpCase class
 *
 * AST node representation of a variable binding in a let expression
 */
public class ExpCase extends ASTNode2 {

    public ExpCase(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpCase(this, arg);
    }

    public String toString() {
	return  getChild(0) + " : " + getChild(1);
	
    }
}
