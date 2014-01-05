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

public interface SMPLVisitor<S, T> {

    public void output(T value);

    public T visitProgram(SMPLProgram prog, S state);

    public T visitSequence(ASTSequence seq, S state);

    public T visitDefine(ASTDefine def, S state);

    public T visitID(ASTIdExp id, S state);

    public T visitMakeBoolean(ASTBooleanExp bool, S state);

    public T visitMakeFloat(ASTFloatExp flVal, S state);

    public T visitMakeInt(ASTIntExp intVal, S state);

    public T visitMakeString(ASTStringExp str, S state);

    public T visitMakeVector(ASTVectorExp vec, S state);

    public T visitBoolNot(ASTBoNotExp bnot, S state);

    public T visitBoolAnd(ASTBoAndExp band, S state);

    public T visitBoolOr(ASTBoOrExp bor, S state);

    public T visitNmAdd(ASTNmAddExp nmadd, S state);

    public T visitNmSub(ASTNmSubExp nmsub, S state);

    public T visitNmMul(ASTNmMulExp nmmul, S state);

    public T visitNmDiv(ASTNmDivExp nmdiv, S state);

    public T visitNmMod(ASTNmModExp nmmod, S state);

    public T visitNmEquals(ASTNmEqlExp nmeqls, S state);

    public T visitNmLThan(ASTNmLessExp nmless, S state);

    public T visitNmGThan(ASTNmGrtrExp nmgrtr, S state);

    public T visitNmLEqls(ASTNmLEqlExp nmleql, S state);

    public T visitNmGEqls(ASTNmGEqlExp nmeqls, S state);

    public T visitNmNotEql(ASTNmNotEqlExp nmneql, S state);

    public T visitBitAnd(ASTBitAndExp btand, S state);

    public T visitBitOr(ASTBitOrExp btand, S state);

    public T visitBitNot(ASTBitNotExp btand, S state);

    public T visitFunDef(ASTFunDefExp fundef, S state);

    public T visitLetDef(ASTFunDefExp fundef, S state);

    public T visitFunCall(ASTFunCallExp funcall, S state);

    public T visitAssign(ASTAssignExp i, S state);

    public T visitIfExp(ASTIfExp i, S state);

    public T visitPrint(ASTPrintExp i, S state);

    public T visitRead(ASTReadExp i, S state);

    public T visitReadInt(ASTReadIntExp i, S state);

    public T visitPairExp(ASTPairExp e, S state);

    public T visitCarExp(ASTCarExp e, S state);

    public T visitCdrExp(ASTCdrExp e, S state);

    public T visitPairQExp(ASTPairQExp e, S state);

    public T visitListExp(ASTListExp e, S state);

    public T visitSizeExp(ASTSizeExp e, S state);

    public T visitEqvExp(ASTEqvExp e, S state);

    public T visitEqualExp(ASTEqualExp e, S state);

    public T visitVectorIndexExp(ASTVectorIndexExp e, S state);

    public T visitListConcat(ASTListConcatExp e, S state);

    public T visitStringExp(ASTSubstrExp e, S state);

    public T visitVecExp(ASTVecExp e, S state);

    public T visitVectorListExp(ASTVectorListExp e, S state);

    public T visitMulAssignExp(ASTMulAssignExp e, S state);

    public T visitCaseExp(ASTCaseExp e, S state);

    public T visitCaseStmtExp(ASTCaseStmtExp e, S state);

    public T visitLazyExp(ASTLazyExp e, S state);
}
