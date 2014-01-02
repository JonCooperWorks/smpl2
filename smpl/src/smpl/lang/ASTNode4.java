/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.lang;

/**
 *
 * @author Marcel
 */
public abstract class ASTNode4 extends ASTNode{
ASTNode[] children;


    public ASTNode4(ASTNode c1, ASTNode c2, ASTNode c3, ASTNode c4) {
	children = new ASTNode[]{c1, c2, c3, c4};

    }

    public ASTNode getChild(int i) {
	return children[i];
    }

 
}
