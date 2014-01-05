/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import ast.SMPLProgram;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import ast.SMPLLexer;
import ast.SMPLParser;


public class Repl {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        SMPLEnvironment environment = new SMPLEnvironment();
        SMPLEvaluator visitor = new SMPLEvaluator();
        
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
          System.out.println("SMPL test1");
        parseEvalShow(System.in, visitor, environment);
          System.out.println("SMPL test2");
    }
    
    private static <S, T> void parseEvalShow(InputStream stream, SMPLVisitor<S, T> visitor, S state) throws Exception{
        SMPLParser parser;
        SMPLProgram commands = null;
        
        System.out.print(">");
        try {
            parser = new SMPLParser(new SMPLLexer(stream));
            commands = (SMPLProgram)parser.parse().value;
        } catch (Exception e) {
            throw e;
        }

        T result;
        if (commands != null) {
            try {
                result = commands.visit(visitor, state);
            } catch (NullPointerException e) {
                System.out.println("Runtime Error: " + e.getMessage());
            }
        }
    }
}
