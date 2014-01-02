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
public class ASTNmEqlExp extends ASTNmExp{
    public ASTNmEqlExp(ASTNode i1, ASTNode i2){
        super("=", i1, i2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitNmEquals(this, state);
    }
}