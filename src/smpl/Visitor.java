/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl;

import syntax.ASTCdrExp;
import syntax.ASTSizeExp;
import syntax.ASTPairQExp;
import syntax.ASTSequence;
import syntax.ASTBoAndExp;
import syntax.ASTBooleanExp;
import syntax.ASTBoOrExp;
import syntax.SMPLProgram;
import syntax.ASTIntExp;
import syntax.ASTEqvExp;
import syntax.ASTVectorExp;
import syntax.ASTNmDivExp;
import syntax.ASTBitOrExp;
import syntax.ASTFloatExp;
import syntax.ASTNmGEqlExp;
import syntax.ASTPrintExp;
import syntax.ASTBitNotExp;
import syntax.ASTStringExp;
import syntax.ASTCarExp;
import syntax.ASTBoNotExp;
import syntax.ASTReadExp;
import syntax.ASTNmEqlExp;
import syntax.ASTVecExp;
import syntax.ASTBitAndExp;
import syntax.ASTListExp;
import syntax.ASTVectorIndexExp;
import syntax.ASTIdExp;
import syntax.ASTEqualExp;
import syntax.ASTNmGrtrExp;
import syntax.ASTNmSubExp;
import syntax.ASTNmModExp;
import syntax.ASTCaseStmtExp;
import syntax.ASTCaseExp;
import syntax.ASTFunCallExp;
import syntax.ASTNmLEqlExp;
import syntax.ASTMulAssignExp;
import syntax.ASTNmLessExp;
import syntax.ASTNmNotEqlExp;
import syntax.ASTIfExp;
import syntax.ASTAssignExp;
import syntax.ASTSubstrExp;
import syntax.ASTPairExp;
import syntax.ASTListConcatExp;
import syntax.ASTFunDefExp;
import syntax.ASTLazyExp;
import syntax.ASTReadIntExp;
import syntax.ASTNmMulExp;
import syntax.ASTNmAddExp;
import syntax.ASTVectorListExp;
import syntax.ASTDefine;

/**
 *
 * @author jean-paul
 */
public interface Visitor<S, T> {
    /**
     * This function will be called once when the visitor is first instantiated.
     * Place any initialization code here that is needed for a one-time setup of
     * the visitor.
     */
    public void setup();
    
    /**
     * Create a fresh instance of the state used by this visitor.  The result
     * of this method will be passed to the visitor on its first call with 
     * visitProgram.
     * @return an instance of state in its initial configuration.
     */
    public S createInitialState();
    
    /**
     * Output the result of a visitation.
     * @param value The value to be output, presumably the value returned by
     * a call to visitProgram.
     */
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
