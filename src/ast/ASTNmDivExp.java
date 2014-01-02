/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTNmDivExp extends ASTNmExp{
    
    public ASTNmDivExp(ASTNode i1, ASTNode i2){
        super("/", i1, i2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitNmDiv(this, state);
    }
}
