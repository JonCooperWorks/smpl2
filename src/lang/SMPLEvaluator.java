/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import ast.ASTCdrExp;
import ast.ASTSizeExp;
import ast.ASTPairQExp;
import ast.ASTSequence;
import ast.ASTBoAndExp;
import ast.ASTBooleanExp;
import ast.ASTBoOrExp;
import ast.SMPLProgram;
import ast.ASTIntExp;
import ast.ASTEqvExp;
import ast.ASTVectorExp;
import ast.ASTNmDivExp;
import ast.ASTBitOrExp;
import ast.ASTFloatExp;
import ast.ASTNmGEqlExp;
import ast.ASTPrintExp;
import ast.ASTBitNotExp;
import ast.ASTStringExp;
import ast.ASTNode;
import ast.ASTCarExp;
import ast.ASTBoNotExp;
import ast.ASTReadExp;
import ast.ASTNmEqlExp;
import ast.ASTVecExp;
import ast.ASTBitAndExp;
import ast.ASTListExp;
import ast.ASTVectorIndexExp;
import ast.ASTIdExp;
import ast.ASTEqualExp;
import ast.ASTNmGrtrExp;
import ast.ASTNmSubExp;
import ast.ASTNmModExp;
import ast.ASTCaseStmtExp;
import ast.ASTCaseExp;
import ast.ASTFunCallExp;
import ast.ASTNmLEqlExp;
import ast.ASTMulAssignExp;
import ast.ASTNmLessExp;
import ast.ASTNmNotEqlExp;
import ast.ASTIfExp;
import ast.ASTAssignExp;
import ast.ASTSubstrExp;
import ast.ASTPairExp;
import ast.ASTListConcatExp;
import ast.ASTFunDefExp;
import ast.ASTLazyExp;
import ast.ASTReadIntExp;
import ast.ASTNmMulExp;
import ast.ASTNmAddExp;
import ast.ASTVectorListExp;
import ast.ASTDefine;
import java.util.ArrayList;
import java.util.Scanner;
import natives.*;

public class SMPLEvaluator implements SMPLVisitor<SMPLEnvironment, SMPLContainer> {

    @Override
    public void output(SMPLContainer value) {
        System.out.println(value.getValue().toString());
    }

    @Override
    public SMPLContainer visitProgram(SMPLProgram prog, SMPLEnvironment state) {
	System.out.print("visitProgram...");
        state = new SMPLEnvironment();

        ASTSequence seq = prog.getBody();
        SMPLContainer result = seq.visit(this, state);

        return result;
    }

    @Override
    public SMPLContainer visitSequence(ASTSequence seq, SMPLEnvironment state) {
	System.out.print("visitSeq...");
        SMPLContainer result = null;
        for (ASTNode stmt : seq.statements()) {
            result = (SMPLContainer) stmt.visit(this, state);
        }

        return result;
    }

    @Override
    public SMPLContainer visitDefine(ASTDefine def, SMPLEnvironment state) {
	System.out.print("visitDefine...");
        SMPLContainer contents = def.getContents().visit(this, state);
        String key = def.getID();
        state.put(key, contents);
        return contents;
    }

    @Override
    public SMPLContainer visitID(ASTIdExp id, SMPLEnvironment state) {
	System.out.print("visitID...");
        return state.get(id.getName());
    }

    @Override
    public SMPLContainer visitMakeBoolean(ASTBooleanExp bool, SMPLEnvironment state) {
	System.out.print("visitMakeBoolean...");
        return new SMPLContainer(new SMPLBoolean(bool.getValue()));
    }

    @Override
    public SMPLContainer visitMakeFloat(ASTFloatExp flVal, SMPLEnvironment state) {
	System.out.print("visitMakeFloat...");
        return new SMPLContainer(new SMPLFloat(flVal.getValue()));
    }

    @Override
    public SMPLContainer visitMakeInt(ASTIntExp intVal, SMPLEnvironment state) {
	System.out.print("visitMakeInt...");
        return new SMPLContainer(new SMPLInt(intVal.getValue()));
    }

    @Override
    public SMPLContainer visitMakeString(ASTStringExp str, SMPLEnvironment state) {
	System.out.print("visitMakeString...");
        return new SMPLContainer(new SMPLString(str.getValue()));
    }

    @Override
    public SMPLContainer visitMakeVector(ASTVectorExp vec, SMPLEnvironment state) {
	System.out.print("visitMakeVector...");
        ArrayList<ASTNode> contents = vec.getValue();
        ArrayList<SMPLContainer> container = new ArrayList<SMPLContainer>();
        for (int i = 0; i < contents.size(); i++) {
            ASTNode curr = contents.get(i);
            container.add(curr.visit(this, state));
        }
        return new SMPLContainer(new SMPLVector(container));
    }

    @Override
    public SMPLContainer visitBoolNot(ASTBoNotExp bnot, SMPLEnvironment state) {
	System.out.print("visitBoolNot...");
        boolean bexp = ((SMPLBoolean) bnot.getOper().visit(this, state).getValue()).isValue();
        return new SMPLContainer(new SMPLBoolean(!bexp));
    }

    @Override
    public SMPLContainer visitBoolAnd(ASTBoAndExp band, SMPLEnvironment state) {
	System.out.print("visitBoolAnd...");
        boolean bexp1 = ((SMPLBoolean) band.getOper().visit(this, state).getValue()).isValue();
        boolean bexp2 = ((SMPLBoolean) band.getOper2().visit(this, state).getValue()).isValue();
        return new SMPLContainer(new SMPLBoolean(bexp1 && bexp2));
    }

    @Override
    public SMPLContainer visitBoolOr(ASTBoOrExp bor, SMPLEnvironment state) {
	System.out.print("visitBoolOr...");
        boolean bexp1 = ((SMPLBoolean) bor.getOper().visit(this, state).getValue()).isValue();
        boolean bexp2 = ((SMPLBoolean) bor.getOper2().visit(this, state).getValue()).isValue();
        return new SMPLContainer(new SMPLBoolean(bexp1 || bexp2));
    }

    @Override
    public SMPLContainer visitNmAdd(ASTNmAddExp nmadd, SMPLEnvironment state) {
	System.out.print("visitNmAdd...");
        SMPLContainer container;
        String type1 = nmadd.getOper().visit(this, state).getType();
        String type2 = nmadd.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmadd.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmadd.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLFloat(oper1 + oper2));
        } else {
            int oper1 = ((SMPLInt) nmadd.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmadd.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLInt(oper1 + oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmSub(ASTNmSubExp nmsub, SMPLEnvironment state) {
        System.out.print("visitNmSub...");
	SMPLContainer container;
        String type1 = nmsub.getOper().visit(this, state).getType();
        String type2 = nmsub.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmsub.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmsub.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLFloat(oper1 - oper2));
        } else {
            int oper1 = ((SMPLInt) nmsub.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmsub.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLInt(oper1 - oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmMul(ASTNmMulExp nmmul, SMPLEnvironment state) {
        System.out.print("visitNmMul...");
	SMPLContainer container;
        String type1 = nmmul.getOper().visit(this, state).getType();
        String type2 = nmmul.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmmul.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmmul.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLFloat(oper1 * oper2));
        } else {
            int oper1 = ((SMPLInt) nmmul.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmmul.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLInt(oper1 * oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmDiv(ASTNmDivExp nmdiv, SMPLEnvironment state) {
	System.out.print("visitNmDiv...");
        SMPLContainer container;
        String type1 = nmdiv.getOper().visit(this, state).getType();
        String type2 = nmdiv.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmdiv.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmdiv.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLFloat(oper1 / oper2));
        } else {
            int oper1 = ((SMPLInt) nmdiv.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmdiv.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLInt(oper1 / oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmMod(ASTNmModExp nmmod, SMPLEnvironment state) {
	System.out.print("visitNmMod...");
        SMPLContainer container;
        String type1 = nmmod.getOper().visit(this, state).getType();
        String type2 = nmmod.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmmod.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmmod.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLFloat(oper1 % oper2));
        } else {
            int oper1 = ((SMPLInt) nmmod.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmmod.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLInt(oper1 % oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmEquals(ASTNmEqlExp nmeqls, SMPLEnvironment state) {
	System.out.print("visitNmEq...");
        SMPLContainer container;
        String type1 = nmeqls.getOper().visit(this, state).getType();
        String type2 = nmeqls.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmeqls.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmeqls.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 == oper2));
        } else {
            int oper1 = ((SMPLInt) nmeqls.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmeqls.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 == oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmLThan(ASTNmLessExp nmless, SMPLEnvironment state) {
	System.out.print("visitNmLT...");
        SMPLContainer container;
        String type1 = nmless.getOper().visit(this, state).getType();
        String type2 = nmless.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmless.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmless.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 < oper2));
        } else {
            int oper1 = ((SMPLInt) nmless.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmless.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 < oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmGThan(ASTNmGrtrExp nmgrtr, SMPLEnvironment state) {
	System.out.print("visitNmGT...");        
	SMPLContainer container;
        String type1 = nmgrtr.getOper().visit(this, state).getType();
        String type2 = nmgrtr.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmgrtr.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmgrtr.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 > oper2));
        } else {
            int oper1 = ((SMPLInt) nmgrtr.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmgrtr.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 > oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmLEqls(ASTNmLEqlExp nmleql, SMPLEnvironment state) {
	System.out.print("visitNmLTEQ...");        
	SMPLContainer container;
        String type1 = nmleql.getOper().visit(this, state).getType();
        String type2 = nmleql.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmleql.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmleql.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 <= oper2));
        } else {
            int oper1 = ((SMPLInt) nmleql.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmleql.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 <= oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmGEqls(ASTNmGEqlExp nmgeql, SMPLEnvironment state) {
	System.out.print("visitNmGTEQ...");        
	SMPLContainer container;
        String type1 = nmgeql.getOper().visit(this, state).getType();
        String type2 = nmgeql.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmgeql.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmgeql.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 >= oper2));
        } else {
            int oper1 = ((SMPLInt) nmgeql.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmgeql.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 >= oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitNmNotEql(ASTNmNotEqlExp nmneql, SMPLEnvironment state) {
	System.out.print("visitNmNotEq...");
        SMPLContainer container;
        String type1 = nmneql.getOper().visit(this, state).getType();
        String type2 = nmneql.getOper2().visit(this, state).getType();
        if ("Float".equals(type1) || "Float".equals(type2)) {
            double oper1 = ((SMPLFloat) nmneql.getOper().visit(this, state).getValue()).getValue();
            double oper2 = ((SMPLFloat) nmneql.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 != oper2));
        } else {
            int oper1 = ((SMPLInt) nmneql.getOper().visit(this, state).getValue()).getValue();
            int oper2 = ((SMPLInt) nmneql.getOper2().visit(this, state).getValue()).getValue();
            container = new SMPLContainer(new SMPLBoolean(oper1 != oper2));
        }
        return container;
    }

    @Override
    public SMPLContainer visitBitAnd(ASTBitAndExp btand, SMPLEnvironment state) {
	System.out.print("visitBitAnd...");
        int oper1 = ((SMPLInt) btand.getOper().visit(this, state).getValue()).getValue();
        int oper2 = ((SMPLInt) btand.getOper2().visit(this, state).getValue()).getValue();
        return new SMPLContainer(new SMPLInt(oper1 & oper2));
    }

    @Override
    public SMPLContainer visitBitOr(ASTBitOrExp btor, SMPLEnvironment state) {
	System.out.print("visitBitOr...");
        int oper1 = ((SMPLInt) btor.getOper().visit(this, state).getValue()).getValue();
        int oper2 = ((SMPLInt) btor.getOper2().visit(this, state).getValue()).getValue();
        return new SMPLContainer(new SMPLInt(oper1 | oper2));
    }

    @Override
    public SMPLContainer visitBitNot(ASTBitNotExp btnot, SMPLEnvironment state) {
	System.out.print("visitBitNot...");
        int oper = ((SMPLInt) btnot.getOper().visit(this, state).getValue()).getValue();
        return new SMPLContainer(new SMPLInt(~oper));
    }

    @Override
    public SMPLContainer visitFunDef(ASTFunDefExp fundef, SMPLEnvironment state) {
	System.out.print("visitFunDef...");
        String key = fundef.getName();
        ASTNode body = fundef.getBody();
        ArrayList<String> params = fundef.getParameters();
        SMPLContainer contents = new SMPLContainer(new SMPLFunction(key, params, body));
        state.put(key, contents);
        return contents;
    }

    public SMPLContainer visitLetDef(ASTFunDefExp letdef, SMPLEnvironment state) {
	System.out.print("visitLetDef...");
        SMPLEnvironment newEnv = new SMPLEnvironment(state);
        ArrayList<ASTNode> params = letdef.getParamNodes();

        for (ASTNode node : params) {
            node.visit(this, newEnv);
        }

        return letdef.getBody().visit(this, newEnv);
    }

    @Override
    public SMPLContainer visitFunCall(ASTFunCallExp funcall, SMPLEnvironment state) {
	System.out.print("visitFunCall...");
        SMPLEnvironment child = new SMPLEnvironment(state);
        SMPLContainer funcToCallContainer = funcall.getFunction().visit(this, state);

        if ("Function Call".equals(funcall.getName())) {

            SMPLContainer argsContainer = funcall.getArgs().visit(this, state);

            if (!argsContainer.getType().equals("Vector")) {
                System.out.print("Invalid function call. Vector required for parameters.");
                return null;
            }

            SMPLVector args = (SMPLVector) argsContainer.getValue();

            // Flatten the list...
            ArrayList<SMPLContainer> vals = flatten(args);

            SMPLFunction funcToCall = (SMPLFunction) funcToCallContainer.getValue();

            for (int i = 0; i < funcToCall.getParams().size(); i++) {
                child.put(funcToCall.getParam(i), vals.get(i));
            }

            if (funcToCall.getParams().size() < vals.size()) {
                ArrayList<SMPLContainer> excessParams = new ArrayList<SMPLContainer>();

                for (int i = funcToCall.getParams().size(); i < vals.size(); i++) {
                    excessParams.add(vals.get(i));
                }

                SMPLVector excessParamsVector = new SMPLVector(excessParams);
                child.put("prest", new SMPLContainer(excessParamsVector));
            }

            return funcToCall.getBody().visit(this, child);
        } else {

            SMPLContainer funcContainer = funcall.getFunction().visit(this, state);

            if (!"Function".equals(funcContainer.getType())) {
                System.out.println("Expected function.");
                return null;
            }

            SMPLFunction funcToCall = (SMPLFunction) funcContainer.getValue();

            ArrayList<ASTNode> args = funcall.getArgs2();
            ArrayList<SMPLContainer> vals = new ArrayList<SMPLContainer>();

            for (ASTNode n : args) {
                vals.add(n.visit(this, state));
            }

            for (int i = 0; i < funcToCall.getParams().size(); i++) {
                child.put(funcToCall.getParam(i), vals.get(i));
            }

            if (funcToCall.getParams().size() < vals.size()) {
                ArrayList<SMPLContainer> excessParams = new ArrayList<SMPLContainer>();

                for (int i = funcToCall.getParams().size(); i < vals.size(); i++) {
                    excessParams.add(vals.get(i));
                }

                SMPLVector excessParamsVector = new SMPLVector(excessParams);
                child.put("prest", new SMPLContainer(excessParamsVector));
            }

            return funcToCall.getBody().visit(this, child);
        }

    }

    public static ArrayList<SMPLContainer> flatten(SMPLVector v) {
	System.out.println("flatten...");
        if (v.getValues().size() > 1) {
            ArrayList<SMPLContainer> lst = new ArrayList<SMPLContainer>();

            lst.add(v.getValues().get(0));
            lst.addAll(flatten((SMPLVector) v.getValues().get(1).getValue()));

            return lst;
        } else {
            ArrayList<SMPLContainer> lst = new ArrayList<SMPLContainer>();

            lst.add(v.getValues().get(0));
            return lst;
        }
    }

    @Override
    public SMPLContainer visitAssign(ASTAssignExp assignExp, SMPLEnvironment state) {
	System.out.print("visitAssign...");
        String strVarName = assignExp.getVariableName();
        SMPLContainer varVal = assignExp.getValue().visit(this, state);
        state.put(strVarName, varVal);
        return varVal;
    }

    @Override
    public SMPLContainer visitIfExp(ASTIfExp ifExp, SMPLEnvironment state) {
	System.out.print("visitIfExp...");
        SMPLContainer ifCond = ifExp.getCondition().visit(this, state);
        SMPLBoolean cond = null;

        try {
            cond = (SMPLBoolean) ifCond.getValue();
        } catch (Exception e) {
            System.out.println("Expected Boolean expression.");
            return null;
        }

        if (cond.isValue()) {
            SMPLContainer result = ifExp.getExpression().visit(this, state);
            return result;
        } else if (ifExp.getElseExpression() != null) {
            SMPLContainer result = ifExp.getElseExpression().visit(this, state);
            return result;
        }

        return null;
    }

    @Override
    public SMPLContainer visitPrint(ASTPrintExp printExp, SMPLEnvironment state) {
	System.out.print("visitPrint...");
        SMPLContainer exp = printExp.getExpression().visit(this, state);
        Object output = exp.getValue().toString();
        

        if (printExp.PrintLine()) {
            System.out.println(output);
        } else {
            System.out.print(output);
        }

        return null;
    }

    @Override
    public SMPLContainer visitRead(ASTReadExp _, SMPLEnvironment state) {
        System.out.print("visitRead...");
	Scanner scan = new Scanner(System.in);
        String str = scan.next();

        return new SMPLContainer(new SMPLString(str));
    }

    @Override
    public SMPLContainer visitReadInt(ASTReadIntExp _, SMPLEnvironment state) {
	System.out.print("visitReadInt...");
        Scanner scan = new Scanner(System.in);
        try {
            int i = scan.nextInt();
            return new SMPLContainer(new SMPLInt(i));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SMPLContainer visitPairExp(ASTPairExp pair, SMPLEnvironment state) {
	System.out.print("visitPair...");
        return new SMPLContainer(new SMPLVector(pair.getExp1().visit(this, state),
                pair.getExp2().visit(this, state)));
    }

    @Override
    public SMPLContainer visitCarExp(ASTCarExp car, SMPLEnvironment state) {
	System.out.print("visitCar...");
        try {
            SMPLVector v = (SMPLVector) car.getExp().visit(this, state).getValue();

            ArrayList<SMPLContainer> values = v.getValues();

            if (values.isEmpty()) {
                return new SMPLContainer(new SMPLVector());
            } else {
                return values.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error with input vector.");
            return null;
        }
    }

    @Override
    public SMPLContainer visitCdrExp(ASTCdrExp cdr, SMPLEnvironment state) {
	System.out.print("visitCdr...");
        try {
            SMPLVector v = (SMPLVector) cdr.getExp().visit(this, state).getValue();

            ArrayList<SMPLContainer> values = v.getValues();

            if (values.isEmpty()) {
                return new SMPLContainer(new SMPLVector());
            } else {
                return values.get(1);
            }
        } catch (Exception ex) {
            System.out.println("Error with input vector.");
            return null;
        }
    }

    @Override
    public SMPLContainer visitPairQExp(ASTPairQExp pairq, SMPLEnvironment state) {
	System.out.print("visitPairQ...");
        return new SMPLContainer(new SMPLBoolean(pairq.getExp().visit(this, state).getType() == "Vector"));
    }

    private SMPLVector createLinkedList(ArrayList<ASTNode> lst, SMPLEnvironment state) {
	System.out.println("createdLinkedList...");
        if (lst.isEmpty()) {
            return new SMPLVector();
        }

        return ctllHelper(lst, lst.get(0), state);
    }

    private SMPLVector ctllHelper(ArrayList<ASTNode> l, ASTNode e, SMPLEnvironment state) {
	System.out.println("ctllHelper...");
        if (l.size() > 1) {
            SMPLVector newVector = new SMPLVector();

            newVector.getValues().add(e.visit(this, state));

            ArrayList<ASTNode> subList = new ArrayList<ASTNode>();

            for (int i = 1; i < l.size(); i++) {
                subList.add(l.get(i));
            }

            newVector.getValues().add(new SMPLContainer(ctllHelper(subList, l.get(1), state)));
            return newVector;
        } else if (l.size() == 1) {
            return new SMPLVector(l.get(0).visit(this, state));
        } else {
            return null;
        }
    }

    private SMPLVector createLinkedListFromContainer(ArrayList<SMPLContainer> lst, SMPLEnvironment state) {
	System.out.println("createdLinkedListFromContainer...");
        if (lst.isEmpty()) {
            return new SMPLVector();
        }

        return createLinkedList(lst, lst.get(0), state);
    }

    private SMPLVector createLinkedList(ArrayList<SMPLContainer> l, SMPLContainer e, SMPLEnvironment state) {
	System.out.println("ctllHelper...");
        if (l.size() > 1) {
            SMPLVector newVector = new SMPLVector();

            newVector.getValues().add(e);

            ArrayList<SMPLContainer> subList = new ArrayList<SMPLContainer>();

            for (int i = 1; i < l.size(); i++) {
                subList.add(l.get(i));
            }

            newVector.getValues().add(new SMPLContainer(createLinkedList(subList, l.get(1), state)));
            return newVector;
        } else if (l.size() == 1) {
            return new SMPLVector(l.get(0));
        } else {
            return null;
        }
    }

    @Override
    public SMPLContainer visitListExp(ASTListExp list, SMPLEnvironment state) {
	System.out.print("visitList...");
        // Create a linked list of Pairs...
        return new SMPLContainer(createLinkedList(list.getValues(), state));
    }

    @Override
    public SMPLContainer visitSizeExp(ASTSizeExp size, SMPLEnvironment state) {
	System.out.print("visitSize...");
        SMPLContainer vector = size.getExp().visit(this, state);

        if (!"Vector".equals(vector.getType())) {
            System.out.println("The size function requires a Vector.");
            return null;
        }

        SMPLVector v = (SMPLVector) vector.getValue();

        return new SMPLContainer(new SMPLInt(getVectorSize(v)));
    }

    private int getVectorSize(SMPLVector v) {
	System.out.println("getVectorSize...");
        int iCount = 0;
        while (v.getValues().size() > 1) {
            iCount++;
            v = (SMPLVector) v.getValues().get(1).getValue();
        }

        iCount += v.getValues().size();

        return iCount;
    }

    @Override
    public SMPLContainer visitEqvExp(ASTEqvExp eqv, SMPLEnvironment state) {
	System.out.print("visitEqv...");
        SMPLContainer e1 = eqv.getExp1().visit(this, state);
        SMPLContainer e2 = eqv.getExp2().visit(this, state);

        return new SMPLContainer(new SMPLBoolean(e1.getValue() == e2.getValue()));
    }

    boolean objectsAreEqual(SMPLContainer e1, SMPLContainer e2) {

        if (!e1.getType().equals(e2.getType())) {
            return false;
        } else {
            if ("Vector".equals(e1.getType())) {
                SMPLVector v1 = (SMPLVector) e1.getValue();
                SMPLVector v2 = (SMPLVector) e2.getValue();

                if (getVectorSize(v1) != getVectorSize(v2)) {
                    return false;
                }

                ArrayList<SMPLContainer> fv1 = flatten(v1);
                ArrayList<SMPLContainer> fv2 = flatten(v2);

                for (int i = 0; i < fv1.size(); i++) {
                    if (!objectsAreEqual(fv1.get(i), fv2.get(i))) {
                        return false;
                    }
                }

                return true;
            } else if ("Boolean".equals(e1.getType())) {
                SMPLBoolean b1 = (SMPLBoolean) e1.getValue();
                SMPLBoolean b2 = (SMPLBoolean) e2.getValue();

                return b1.isValue() == b2.isValue();
            } else if ("Float".equals(e1.getType())) {
                SMPLFloat f1 = (SMPLFloat) e1.getValue();
                SMPLFloat f2 = (SMPLFloat) e2.getValue();

                return f1.getValue() == f2.getValue();
            } else if ("Integer".equals(e1.getType())) {
                SMPLInt i1 = (SMPLInt) e1.getValue();
                SMPLInt i2 = (SMPLInt) e2.getValue();

                return i1.getValue() == i2.getValue();
            } else if ("String".equals(e1.getType())) {
                SMPLString s1 = (SMPLString) e1.getValue();
                SMPLString s2 = (SMPLString) e2.getValue();

                return s1.toString().equals(s2.toString());
            }

            return false;
        }
    }

    @Override
    public SMPLContainer visitEqualExp(ASTEqualExp equal, SMPLEnvironment state) {
	System.out.print("visitObjectsAreEqual...");
        SMPLContainer e1 = equal.getExp1().visit(this, state);
        SMPLContainer e2 = equal.getExp2().visit(this, state);

        return new SMPLContainer(new SMPLBoolean(objectsAreEqual(e1, e2)));
    }

    public SMPLContainer visitVectorIndexExp(ASTVectorIndexExp idx, SMPLEnvironment state) {
	System.out.print("visitVecIndex...");
        SMPLContainer vectorContainer = idx.getExp().visit(this, state);
        SMPLContainer indexContainer = idx.getIndex().visit(this, state);

        if (!"Vector".equals(vectorContainer.getType())) {
            System.out.println("Index expressions must be vectors.");
            return null;
        } else if (!"Integer".equals(indexContainer.getType())) {
            System.out.println("Indices must be integers.");
            return null;
        }

        SMPLVector vector = (SMPLVector) vectorContainer.getValue();
        SMPLInt index = (SMPLInt) indexContainer.getValue();

        return flatten(vector).get(index.getValue());
    }

    public SMPLContainer visitListConcat(ASTListConcatExp concat, SMPLEnvironment state) {
	System.out.print("visitListConcat...");
        SMPLContainer vectorContainer1 = concat.getVector1().visit(this, state);
        SMPLContainer vectorContainer2 = concat.getVector2().visit(this, state);

        if (!"Vector".equals(vectorContainer1.getType())) {
            System.out.println("@ requires vectors.");
            return null;
        }

        if (!"Vector".equals(vectorContainer2.getType())) {
            System.out.println("@ requires vectors.");
            return null;
        }

        SMPLVector v1 = (SMPLVector) vectorContainer1.getValue();
        SMPLVector v2 = (SMPLVector) vectorContainer2.getValue();

        ArrayList<SMPLContainer> fv1 = flatten(v1);
        ArrayList<SMPLContainer> fv2 = flatten(v2);

        for (int i = 0; i < fv2.size(); i++) {
            fv1.add(fv2.get(i));
        }

        SMPLVector newVector = createLinkedListFromContainer(fv1, state);

        return new SMPLContainer(newVector);
    }

    public SMPLContainer visitStringExp(ASTSubstrExp st, SMPLEnvironment state) {
	System.out.print("visitString...");
        SMPLContainer strContainer = st.getString().visit(this, state);
        SMPLContainer startContainer = st.getStart().visit(this, state);
        SMPLContainer endContainer = st.getEnd().visit(this, state);

        if (!"String".equals(strContainer.getType())) {
            System.out.println("Substring requires a string as the first argument.");
            return null;
        }

        if (!"Integer".equals(startContainer.getType())) {
            System.out.println("Substring requires an integer as the second argument.");
            return null;
        }

        if (!"Integer".equals(endContainer.getType())) {
            System.out.println("Substring requires an integer as the third argument.");
            return null;
        }

        String str = ((SMPLString) strContainer.getValue()).toString();
        int start = ((SMPLInt) startContainer.getValue()).getValue();
        int end = ((SMPLInt) endContainer.getValue()).getValue();

        try {
            return new SMPLContainer(new SMPLString(str.substring(start, end)));
        } catch (Exception e1) {
            System.out.println("Error while evaluating substring.\n" + e1.getMessage());
            return null;
        }
    }

    public SMPLContainer visitVecExp(ASTVecExp vec, SMPLEnvironment state) {
	System.out.print("visitVec...");        
	SMPLContainer sizeContainer = vec.getSize().visit(this, state);

        if (!"Integer".equals(sizeContainer.getType())) {
            System.out.println("The size of the vector expression must be an integer.");
            return null;
        }

        int iCount = ((SMPLInt) sizeContainer.getValue()).getValue();
        SMPLVector v = new SMPLVector();
        for (int i = 0; i < iCount; i++) {
            ArrayList<ASTNode> args = new ArrayList<ASTNode>();
            args.add(new ASTIntExp(i));

            ASTFunCallExp funcCallExp = new ASTFunCallExp(vec.getFunction(), new ASTVectorExp(args));
            v.getValues().add(funcCallExp.visit(this, state));
        }

        return new SMPLContainer(v);
    }

    public SMPLContainer visitVectorListExp(ASTVectorListExp vecList, SMPLEnvironment state) {
	System.out.print("visitVecList...");
        // Create a Vector of each element...
        ArrayList<SMPLContainer> list = new ArrayList<SMPLContainer>();
        ArrayList<ASTNode> nodeList = vecList.getValues();

        for (int i = 0; i < nodeList.size(); i++) {

            SMPLContainer c = nodeList.get(i).visit(this, state);

            if ("Vector".equals(c.getType())) {
                list.addAll(((SMPLVector) c.getValue()).getValues());
            } else {
                list.add(c);
            }
        }

        return new SMPLContainer(new SMPLVector(list));
    }

    public SMPLContainer visitMulAssignExp(ASTMulAssignExp mulAssign, SMPLEnvironment state) {
	System.out.print("visitMulAssign...");
        SMPLContainer expListContainer = mulAssign.getExpression().visit(this, state);

        if (!"Vector".equals(expListContainer.getType())) {
            System.out.println("Expression must evaluate to a vector.");
            return null;
        }

        SMPLVector expList = (SMPLVector) expListContainer.getValue();
        ArrayList<String> varList = mulAssign.getVariableList();

        ArrayList<SMPLContainer> vals = flatten(expList);

        if (varList.size() != vals.size()) {
            System.out.println("You must have as many variables as expressions and vice versa.");
            return null;
        }

        for (int i = 0; i < varList.size(); i++) {
            SMPLContainer var = state.get(varList.get(i));

            if (var == null) {
                System.out.println("Use of an undefined variable: " + varList.get(i));
                return null;
            }

            state.put(varList.get(i), vals.get(i));
        }

        return null;
    }

    public SMPLContainer visitCaseExp(ASTCaseExp c, SMPLEnvironment state) {
	System.out.print("visitCase...");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SMPLContainer visitCaseStmtExp(ASTCaseStmtExp cStmt, SMPLEnvironment state) {
	System.out.print("visitCaseStmt...");
        // Go thru each case exp
        ArrayList<ASTNode> caseExps = cStmt.getCaseExpressions();

        for (ASTNode n : caseExps) {
            ASTCaseExp caseExp = (ASTCaseExp) n;

            SMPLContainer predContainer = caseExp.getPredicate().visit(this, state);

            if (!"Boolean".equals(predContainer.getType())) {
                System.out.println("Case expressions must begin with boolean conditions");
                return null;
            }

            if (((SMPLBoolean) predContainer.getValue()).isValue()) {
                SMPLContainer consContainer = caseExp.getConsequent().visit(this, state);
                return consContainer;
            }
        }

        return null;
    }

    public SMPLContainer visitLazyExp(ASTLazyExp lazy, SMPLEnvironment state) {
	System.out.print("visitLazy..."); 	
       return lazy.getExp().visit(this, state);
    }
}