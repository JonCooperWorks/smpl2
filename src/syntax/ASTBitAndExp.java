/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import smpl.Visitor;

/**
 *
 * @author jean-paul
 */
public class ASTBitAndExp extends ASTNmExp{
    public ASTBitAndExp(ASTNode b1, ASTNode b2){
        super("&", b1, b2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitBitAnd(this, state);
    }
}
