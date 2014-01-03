/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;


import java.util.ArrayList;
import java.util.Arrays;
import lang.SMPLVisitor;


public abstract class ASTNode {
    
    ArrayList<ASTNode> children = new ArrayList<ASTNode>();
    String name;
    
    public ASTNode(){
        name = "";
    }
    
    public ASTNode(String nm) {
        name = nm;
    }
    
    public ASTNode(String nm, ASTNode... c) {
        this(nm);
        setChildren(new ArrayList<ASTNode>(Arrays.asList(c)));
    }
    
    public ASTNode(String nm, ArrayList<ASTNode> c) {
        this(nm);
        setChildren(c);
    }

    public String getName() {
        return name;
    }
    
    private void setChildren(ArrayList<ASTNode> c) {
        children = c;
    }
    
    public ASTNode getChild(int index) {
        return children.get(index);
    }
    
    /**
     * Visit this node using the given visitor
     * @param <S> The type of the state to be used by the visitor
     * @param <T> The type of the return value produced by the visitor
     * @param visitor The visitor to be used to perform the visit
     * @param state The state to be passed to the visitor
     * @return The result of calling the visitor's visit method for this AST 
     * node
     * @throws CGException If anything goes wrong during the visit computation 
     */
    public abstract <S, T> T visit(SMPLVisitor<S, T> visitor, S state);
    
    protected String listChildren() {
        String result = "";
        if (children.isEmpty()){
            return result;
        }
        else {
            result = children.get(0).toString();
            for (int i = 1; i < children.size(); i++) {
                result += ", " + children.get(i);
            }
        }
        return result;
    }
    
    @Override
    public String toString() {
        return name + "(" + listChildren() + ")";
    }
}