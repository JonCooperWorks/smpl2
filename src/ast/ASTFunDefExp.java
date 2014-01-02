/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import smpl.Visitor;

public class ASTFunDefExp extends ASTNode{

    ArrayList<String> parameters;
    ArrayList<String> nodes;
    ASTNode body;
    
    public ASTFunDefExp(String nm, ArrayList<String> param, ASTNode contents){
        super(nm);
        this.parameters = param;
        this.body = contents;
    }
    
    public ASTFunDefExp(ArrayList<ASTNode> param, ASTNode contents){
        super("let", param);
        this.body = contents;
    }
    
    public ArrayList<String> getParameters() {
        return parameters;
    }

    public ASTNode getBody() {
        return body;
    }
    
    public ArrayList<ASTNode> getParamNodes(){
        return children;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        if( !"let".equals(name) )
            return visitor.visitFunDef(this, state);
        else
            return visitor.visitLetDef(this, state);
    }
    
}
