/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import java.util.ArrayList;
import java.util.Arrays;
import smpl.Visitor;

/**
 *
 * @author jean-paul
 */
public class ASTVectorExp extends ASTNode{
    
    ArrayList<ASTNode> value;
    
    public ASTVectorExp(ASTNode... param){
        super("vector");
        value = new ArrayList(Arrays.asList(param));
    }
    
    public ASTVectorExp(ArrayList<ASTNode> param){
        super("vector");
        value = param;
    }

    public ArrayList<ASTNode> getValue() {
        return value;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitMakeVector(this, state);
    } 
}
