/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTSizeExp extends ASTNode{
    
    public ASTSizeExp(ASTNode e){
        super("Size", e);
    }
    
    public ASTNode getExp(){
        return children.get(0);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitSizeExp(this, state);
    }
}
