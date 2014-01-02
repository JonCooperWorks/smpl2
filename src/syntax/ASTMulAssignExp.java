/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import java.util.ArrayList;
import smpl.Visitor;

/**
 *
 * @author Michelle
 */
public class ASTMulAssignExp extends ASTNode{
    
    private ArrayList<String> varList;
    private ASTNode exp;
    
    public ASTMulAssignExp(ArrayList<String> vl, ASTNode e){
        super("Multiple Assign");
        varList = vl;
        exp = e;
    }
    
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitMulAssignExp(this, state);
    }
    
    public ArrayList<String> getVariableList(){
        return varList;
    }
    
    public ASTNode getExpression(){
        return exp;
    }
}
