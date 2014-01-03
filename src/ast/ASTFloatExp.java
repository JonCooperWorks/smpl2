/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTFloatExp extends ASTNmExp{

    float value;
    
    public ASTFloatExp(float param){
        super("float");
        value = param;
    }

    public float getValue() {
        return value;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitMakeFloat(this, state);
    }
}
