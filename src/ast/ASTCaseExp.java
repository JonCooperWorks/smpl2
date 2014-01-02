/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;

public class ASTCaseExp extends ASTNode{
    
    public ASTCaseExp(ASTNode pred, ASTNode cons){
        super("CaseExp", pred, cons);
    }
    
    public ASTNode getPredicate(){
        return children.get(0);
    }
    
    public ASTNode getConsequent(){
        return children.get(1);
    }
    
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitCaseExp(this, state);
    }
}
