/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

/**
 *
 * @author jean-paul
 */
public class SMPLInt {
    int value;
    
    public SMPLInt(int param){
        value = param;
    }

    public int getValue() {
        return value;
    }
    
    public SMPLInt(String param) throws Exception{
        if ( !param.equalsIgnoreCase("") )
        {
            if ( param.startsWith("0x") ){
                value = Integer.parseInt(param.substring(2), 16);
            }
            else if ( param.startsWith("0b") ){
                value = Integer.parseInt(param.substring(2), 2);
            }
        }
        else{
            throw new Exception("Cannot convert no-value to integer.");
        }
    }
    
    @Override
    public String toString() {
        return "" + value;
    }
}
