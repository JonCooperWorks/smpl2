package smpl.lang;

 /**
 * Abstract Syntax Tree node for an SMPL variable.
 *
 */
public class SmplVar extends ASTNode1 {
    String var;

    public SmplVar(String v) {
        super(new SmplLit(v));
        var = v;
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpVar(this, arg);
    }

    public String getVar() {
	return var;
    }

    public String toString() {
	return var;
    }
}

