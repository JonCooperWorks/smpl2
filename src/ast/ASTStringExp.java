/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTStringExp extends ASTNode{
    String value;
    
    public ASTStringExp(String param){
        super("string");
        value = param;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitMakeString(this, state);
    }
}
