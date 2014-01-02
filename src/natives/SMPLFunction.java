/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

import ast.ASTNode;
import java.util.ArrayList;


public class SMPLFunction {
    String name;
    ArrayList<String> parameters;
    ASTNode body;
    
    public SMPLFunction(String nm, ArrayList<String> param, ASTNode contents){
        this.name = nm;
        this.parameters = param;
        this.body = contents;
    }
    
    public String getName() {
	return name;
    }

    public ArrayList<String> getParams() {
	return parameters;
    }

    public String getParam(int index) {
	return parameters.get(index);
    }

    public ASTNode getBody() {
	return body;
    }
    
    @Override
    public String toString(){
        return "function";
    }
}
