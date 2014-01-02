/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import java.util.ArrayList;

/**
 *
 * @author jean-paul
 */
public abstract class ASTNmExp extends ASTNode{

    public ASTNmExp() {
    }

    public ASTNmExp(String nm) {
        super(nm);
    }

    public ASTNmExp(String nm, ASTNode... c) {
        super(nm, c);
    }

    public ASTNmExp(String nm, ArrayList<ASTNode> c) {
        super(nm, c);
    }
    
    public ASTNode getOper(){
        if ( !this.children.isEmpty() ){ return this.getChild(0); }
        else { return null; }
    }
    
    public ASTNode getOper2(){
        if ( this.children.size() > 0 ){ return this.getChild(1); }
        else { return null; }
    }
}
