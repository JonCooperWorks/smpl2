/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.HashMap;
import natives.SMPLContainer;


public class SMPLEnvironment {
    private HashMap<String, SMPLContainer> env;
    private SMPLEnvironment parent;
    
    // Creates environ as global.
    public SMPLEnvironment(){
        env = new HashMap<String, SMPLContainer>();
        parent = null;
    }
    
    public SMPLEnvironment(SMPLEnvironment mother){
        env = new HashMap<String, SMPLContainer>();
        parent = mother;
    }
    
    public void put(String id, SMPLContainer item){
        env.remove(id);
        env.put(id, item);
    }
    
    public SMPLContainer get(String id){
        SMPLContainer target = env.get(id);
        if (target == null){
            
            if( parent == null )
                return null;
            
            return parent.get(id);
        }
        return target;
    }
}
