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

        System.out.println("SMPL Interpreter.");
        System.out.println("Press Ctrl-C to exit");

        if (args.length == 0) {
            while (true) {
                parseEvalShow(System.in, visitor, environment);
            }
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
        SMPLProgram program = null;

        System.out.print("\n>");
        try {
            parser = new SMPLParser(new SMPLLexer(stream));
            program = (SMPLProgram) parser.parse().value;
        } catch (Exception e) {
            throw e;
        }

        T result;
        if (program != null) {
            try {
                result = program.visit(visitor, state);
            } catch (NullPointerException e) {
                System.out.println("Runtime Error: " + e.getMessage());
            }
        }
    }
}
