/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

/**
 *
 * @author jean-paul
 */
public class SMPLString {
    String value;
    
    public SMPLString(String param){
        value = param;
        
        // Format the string...
        for(int i = 0; i < value.length(); i++){
            
            if( value.charAt(i) == '\\' && i + 1 < value.length() ){
                char controlChar = 0;
                
                if( value.charAt(i + 1) == '\\' )
                    controlChar = '\\';
                else if( value.charAt(i + 1) == 'n' )
                    controlChar = '\n';
                else if( value.charAt(i + 1) == 't' )
                    controlChar = '\t';
                else if( value.charAt(i + 1) == 'f' )
                    controlChar = '\f';
                    
                value = value.substring(0, i) + controlChar + value.substring(i + 2);
            }
        }
    }
    
    @Override
    public String toString(){
        return value;
    }
}
