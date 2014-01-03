/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTNmGrtrExp extends ASTNmExp{
    public ASTNmGrtrExp(ASTNode i1, ASTNode i2){
        super(">", i1, i2);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitNmGThan(this, state);
    }
}
