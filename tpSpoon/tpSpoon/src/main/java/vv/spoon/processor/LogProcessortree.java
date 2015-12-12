package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.support.reflect.code.CtReturnImpl;



public class LogProcessortree extends AbstractProcessor<CtExecutable<?>>  {


    @Override
    public boolean isToBeProcessed(CtExecutable<?> candidate) { 
        /*
        try {
            Class type = candidate.getTarget().getType().getActualClass();
            CtExecutableReference executable = candidate.getExecutable();

            if(type.equals(java.io.PrintStream.class)
                    && isPrint(executable)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }*/
        
        try {

        if((candidate instanceof CtMethod) || (candidate instanceof CtConstructor)) {
            return true;
        }
        return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void process(CtExecutable<?> element) {
    String snippet_enter = "vv.spoon.logger.LogWriter.enter(\"" + this.getElement(element)+ "\")";
        CtStatement state_begin = getFactory().Code().createCodeSnippetStatement(snippet_enter);

        element.getBody().insertBegin(state_begin);


        String snippet_sortie = "vv.spoon.logger.LogWriter.leave(\"" + this.getElement(element)+ "\")";
        CtStatement state_end = getFactory().Code().createCodeSnippetStatement(snippet_sortie);

        if(element.getBody().getLastStatement().getClass().getSimpleName().equals(CtReturnImpl.class.getSimpleName())){
            element.getBody().getLastStatement().insertBefore(state_end);
        }else{
            element.getBody().insertEnd(state_end);
        }
    }
    
    public String getElement(CtExecutable<?> element) {
        String result = "";
        result += element.getParent(CtClass.class).getSimpleName();
        result += ".";
        if(element instanceof CtConstructor) {
            result += element.getParent(CtClass.class).getSimpleName();
        } else {
            result += element.getSimpleName();
        }
        result += "(";
        for (CtParameter<?> parameter : element.getParameters()) {
            result += parameter.toString();
            result += ", ";
        }
        if (result.charAt(result.length() - 1) != '(') {
            result = result.substring(0,result.length() - 2);//on retire le dernier set de ", " si au moins un param a été ajouté
        }
        result += ")";
        return result;
    }

    public String getElement2(CtExecutable<?> element) {
        String result = "";
        result += element.getParent(CtClass.class).getSimpleName()+".";
        result += element.getSimpleName()+"("+element.getParameters()+")";
        return result;
    }

}