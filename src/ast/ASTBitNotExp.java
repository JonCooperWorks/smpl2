/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTBitNotExp extends ASTNmExp{
    public ASTBitNotExp(ASTNode b1){
        super("~", b1);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitBitNot(this, state);
    }
}
