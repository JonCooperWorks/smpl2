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

        System.out.println("SMPL");

        if (args.length == 0) {
            parseEvalShow(System.in, visitor, environment);
        } else {
            try {
                parseEvalShow(new FileInputStream(new File(args[0])), visitor, environment);
            } catch (FileNotFoundException fnfe) {
                System.out.println("Error: File \"" + args[0] + "\" does not exist!");
            }
        }
    }

    private static <S, T> void parseEvalShow(InputStream stream, SMPLVisitor<S, T> visitor, S state) throws Exception {
        SMPLParser parser;
        SMPLProgram commands = null;

        System.out.print(">");
        try {
            parser = new SMPLParser(new SMPLLexer(stream));
            commands = (SMPLProgram) parser.parse().value;
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
