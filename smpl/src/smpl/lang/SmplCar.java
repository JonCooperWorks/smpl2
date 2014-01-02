package smpl.lang;

/**
 * Abstract Syntax Tree node representing the head of a list 
 *
 */
public class SmplCar extends ASTNode1 {

    public SmplCar(ASTNode e1) {
	super(e1);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdCar(this, arg);
    }

    public String toString() {
	return "car(" + getChild() + ")";
    }
}
