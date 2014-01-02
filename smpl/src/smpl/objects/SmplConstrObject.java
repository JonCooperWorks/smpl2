/*
 * SmplConstrObject.java
 *
 * Created on December 21, 2006, 2:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package smpl.objects;

import java.util.ArrayList;
import smpl.lang.*;

/**
 *
 * @author Administrator
 */
public class SmplConstrObject extends SmplExp{
    
    ArrayList params,body;
    
    /** Creates a new instance of SmplConstrObject */
    public SmplConstrObject(ArrayList params, ArrayList body) {
        this.params = params;
        this.body = body;
    }

    public ArrayList getParams() {
        return params;
    }

    public ArrayList getBody() {
        return body;
    }

    public String toString() {
        
        return "Constructor";
    }
    public Object visit(Visitor v,Object arg) throws SmplException{return null;}
    
    
    

    
    
    
}
