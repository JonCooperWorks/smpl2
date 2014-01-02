package smpl.lang;

/**
 * SmplExp.java
 *
 * The SmplExp class is the abstract parent class AST node
 * representations of SMPL expressions. This is the parent of all
 * other classes because everything in SMPL is and expression,
 * including statements.
 *
 */
public abstract class SmplExp extends ASTNode{
    	public SmplExp(){
            super();
        }

        public int getChildCount(){return 0;}
        public ASTNode getChild(int i){return null;}
	}