/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class SMPLProgram {
    ASTSequence body;
    
    public SMPLProgram(ASTSequence param){
        body = param;
    }
    
    public ASTSequence getBody() {
        return body;
    }
    
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state){
        return visitor.visitProgram(this, state);
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
