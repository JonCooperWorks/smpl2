/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import java.util.ArrayList;
import smpl.Visitor;

/**
 *
 * @author jean-paul
 */
public class ASTFunCallExp extends ASTNode{
    
    ASTNode function;
    public ASTFunCallExp(ASTNode func, ASTNode args){
        super("Function Call", args);
        function = func;
    }
    
    public ASTFunCallExp(ASTNode func, ArrayList<ASTNode> args){
        super("function", args);
        function = func;
    }
    
    public ASTNode getFunction(){
        return function;
    }
    
    public ASTNode getArgs(){
        return children.get(0);
    }
    
    public ArrayList<ASTNode> getArgs2(){
        return children;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitFunCall(this, state);
    }
    
}
