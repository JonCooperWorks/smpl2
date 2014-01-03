/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTBitAndExp extends ASTNmExp{
    public ASTBitAndExp(ASTNode b1, ASTNode b2){
        super("&", b1, b2);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitBitAnd(this, state);
    }
}
