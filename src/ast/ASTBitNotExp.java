/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTBitNotExp extends ASTNmExp{
    public ASTBitNotExp(ASTNode b1){
        super("~", b1);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitBitNot(this, state);
    }
}
