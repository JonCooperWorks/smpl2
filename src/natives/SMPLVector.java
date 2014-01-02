/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

import java.util.ArrayList;
import java.util.Arrays;


public class SMPLVector {
    public static ArrayList<SMPLContainer> NIL = new ArrayList();
    ArrayList<SMPLContainer> values;
    
    public SMPLVector(){
        values = new ArrayList();
    }
    
    public SMPLVector(SMPLContainer... param){
        values = new ArrayList(Arrays.asList(param));
    }
    
    public SMPLVector(ArrayList<SMPLContainer> param){
        values = new ArrayList(param);
    }
    
    public SMPLVector(SMPLVector param){
        values = new ArrayList(param.values);
    }

    public ArrayList<SMPLContainer> getValues() {
        return values;
    }
    
    @Override
    public String toString(){
        
        String strOutput = "";
        for(SMPLContainer c : values)
            strOutput += c.value.toString() + ",";
        
        if( strOutput.length() > 0 )
            strOutput = strOutput.substring(0, strOutput.length() - 1);
        
        return "[" + strOutput + "]";
    }
}
