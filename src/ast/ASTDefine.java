/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTDefine extends ASTNode{

    String id;
    
    public ASTDefine(String name, ASTNode node){
        super(name + "=", node);
        id = name;
    }
    
    public String getID(){
        return id;
    }
    
    public ASTNode getContents(){
        return this.getChild(0);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitDefine(this, state);
    }
}
