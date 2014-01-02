/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

/**
 *
 * @author jean-paul
 */
public class SMPLContainer {
    Object value;
    String type;

    public SMPLContainer(SMPLBoolean value) {
        this.value = value;
        type = "Boolean";
    }
    
    public SMPLContainer(SMPLFloat value) {
        this.value = value;
        type = "Float";
    }
    
    public SMPLContainer(SMPLInt value) {
        this.value = value;
        type = "Integer";
    }
    
    public SMPLContainer(SMPLString value) {
        this.value = value;
        type = "String";
    }
    
    public SMPLContainer(SMPLVector value) {
        this.value = value;
        type = "Vector";
    }
    
    public SMPLContainer(SMPLFunction value){
        this.value = value;
        type = "Function";
    }
    
    public Object getValue(){
        return value;
    }

    public String getType() {
        return type;
    }
}
