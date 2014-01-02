package smpl.lang;

/**
 * ExpLet class
 *
 * AST node representation of a variable binding in a let expression
 */
public class ExpLet extends ASTNode2 {

    public ExpLet(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpLet(this, arg);
    }

    public String toString() {
	return  getChild(0) + " be " + getChild(1);
	
    }
}
