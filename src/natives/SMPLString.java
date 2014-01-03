/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package natives;

public class SMPLString {

    String value;

    public SMPLString(String param) {
        value = param;

        // Format the string...
        for (int i = 0; i < value.length(); i++) {

            if (value.charAt(i) == '\\' && (i + 1) < value.length()) {
                char controlChar = 0;

                switch (value.charAt(i + 1)) {
                    case '\\':
                        controlChar = '\\';
                        break;

                    case 'n':
                        controlChar = '\n';
                        break;

                    case 't':
                        controlChar = '\t';
                        break;

                    case 'f':
                        controlChar = '\f';
                        break;
                }
                value = value.substring(0, i) + controlChar + value.substring(i + 2);
            }
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
