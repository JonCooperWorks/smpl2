/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

/**
 *
 * @author jean-paul
 */
public class SMPLBoolean {
    boolean value;
    
    public SMPLBoolean(boolean param){
        value = param;
    }

    public boolean isValue() {
        return value;
    }
    
    public SMPLBoolean(String param) throws Exception{
        if ( !param.equalsIgnoreCase("") )
        {
            value = Boolean.parseBoolean(param);
        }
        else{
            throw new Exception("Cannot convert no-value to boolean.");
        }
    }
    
    @Override
    public String toString(){
        if( value )
            return "true";
        else
            return "false";
    }
}
