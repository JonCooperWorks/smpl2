package smpl.lang;

/**
 * Class to represent AST nodes that have two children.  These nodes
 * typically represent a special form that has two sub expressions.
 * For example, the arithmetic operators fall into this category.
 */
public abstract class ASTNode2 extends ASTNode {

    ASTNode[] children;
    

    public ASTNode2(ASTNode c1, ASTNode c2) {
	children = new ASTNode[]{c1, c2};
	
    }

    public ASTNode getChild(int i) {
        return children[i];
    }

    public int getChildCount(){return children.length;}

    
}
