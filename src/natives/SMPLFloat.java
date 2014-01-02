/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;


public class SMPLFloat {
    double value;
    
    public SMPLFloat(double param){
        value = param;
    }

    public double getValue() {
        return value;
    }
    
    public SMPLFloat(String param) throws Exception{
        if ( !param.equalsIgnoreCase("") )
        {
            value = Float.parseFloat(param);
        }
        else {
            throw new Exception("Cannot convert no-value to floating point number.");
        }
    }
}
