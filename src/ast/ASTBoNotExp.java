/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTBoNotExp extends ASTBoExp{

    public ASTBoNotExp(ASTNode b1){
        super("not", b1);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitBoolNot(this, state);
    }
    
}
