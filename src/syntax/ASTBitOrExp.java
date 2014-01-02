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
public class ASTBitOrExp extends ASTNmExp{
    public ASTBitOrExp(ASTNode b1, ASTNode b2){
        super("or", b1, b2);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitBitOr(this, state);
    }
}
