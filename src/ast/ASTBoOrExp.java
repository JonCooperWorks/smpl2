/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTBoOrExp extends ASTBoExp{
    
    public ASTBoOrExp(ASTNode b1, ASTNode b2){
        super("or", b1, b2);
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitBoolOr(this, state);
    }
}
