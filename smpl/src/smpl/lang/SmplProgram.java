package smpl.lang;
import smpl.sys.SmplEnvironment;
/**
 * SmplProgram class
 *
 * This is basically the collection of SMLP commands, that is, the
 * collection of the entire AST
 */
public class SmplProgram extends ASTNode0 {
    SmplSeq body;

    public SmplProgram(SmplSeq b) {
	    super();
        body = b;
    }

    public SmplSeq getBody() {
	return body;
    }

    public String toString() {
	return body.toString();
    }

    public Object visit(Visitor v, Object info) throws SmplException {
	return v.visitProgram(this, info);
    }

    public void exec(SmplEnvironment state) {
    
    //eval.visit(state, eval);

    }
}
	
