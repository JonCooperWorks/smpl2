/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl;

import ast.SMPLProgram;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SMPL {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        SMPLEnvironment environment = new SMPLEnvironment();
        SMPLVisitor visitor = new SMPLVisitor();
        
        System.out.println("SMPL v0.1a");
        args = new String[1];
        args[0] = "";
        for (String arg : args) {
            try {
                if (arg.startsWith("-file")) {
                    parseEvalShow(new FileInputStream(new File(arg.substring(6))), visitor, environment);
                } else {
                }
            } catch (FileNotFoundException fe) {
                System.out.println("Could not find file " + arg.substring(7));
            }
        }
        
        parseEvalShow(System.in, visitor, environment);
    }
    
    private static <S, T> void parseEvalShow(InputStream stream, Visitor<S, T> visitor, S state) throws Exception{
        SMPLLexer lexer;
        SMPLParser parser;
        SMPLProgram commands = null;

        try {
            lexer = new SMPLLexer(stream);
            parser = new SMPLParser(lexer);
            commands = (SMPLProgram)parser.parse().value;
        } catch (Exception e) {
            throw e;
        }

        T result;
        if (commands != null) {
            try {
                result = commands.visit(visitor, state);
                if (result != null) {
                    // Display value returned.
                } else {
                    //System.out.println("\nNo result");
                }
            } catch (NullPointerException e) {
                System.out.println("Runtime Error: " + e.getMessage());
            }
        }
    }
}
