/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import natives.SMPLContainer;
import lang.SMPLVisitor;


public class ASTAssignExp extends ASTNode{
    
    public ASTAssignExp(String i1, ASTNode i2){
        super(i1, i2);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        try{
        return visitor.visitAssign(this, state);
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public String getVariableName(){
        return name;
    }
    
    public ASTNode getValue(){
        return children.get(0);
    }
}
