/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTNmSubExp extends ASTNmExp{
    
    public ASTNmSubExp(ASTNode i1, ASTNode i2){
        super("-", i1, i2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitNmSub(this, state);
    }
}
