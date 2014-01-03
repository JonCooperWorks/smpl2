/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


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
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitDefine(this, state);
    }
}
