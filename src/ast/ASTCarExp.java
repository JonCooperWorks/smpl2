/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTCarExp extends ASTNode{
    
    public ASTCarExp(ASTNode n){
        super("carExp", n);
    }
    
    public ASTNode getExp(){
        return children.get(0);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitCarExp(this, state);
    }
}
