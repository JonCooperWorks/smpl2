/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.lang;

/**
 *
 * @author Marcel
 */
public abstract class ASTNodeX extends ASTNode{

    ASTNode[] children;


    public ASTNodeX(ASTNode[] children) {
	this.children = children;

    }

    public ASTNode getChild(int x){
        return children[x];
    }

    public int getChildCount() {
	return children.length;
    }

}
