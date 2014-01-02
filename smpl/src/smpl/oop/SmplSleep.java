/*
 * SmplSleep.java
 *
 * Created on December 20, 2006, 11:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package smpl.oop;

import smpl.lang.SmplException;
import smpl.lang.SmplExp;
import smpl.lang.Visitor;

/**
 *
 * @author dephilio
 */
public class SmplSleep extends SmplExp {
    
    Integer value;
    /** Creates a new instance of
     * SmplSleep */
    public SmplSleep(Integer val) {
        
        value = val;
    }

    public String toString() {
        
        return "";
    }

    public Integer getValue() {
        return value;
    }

    public Object visit(Visitor v , Object arg) throws SmplException{
        
        return v.visitSmplSleep(this,arg);
        
        
    }
}
