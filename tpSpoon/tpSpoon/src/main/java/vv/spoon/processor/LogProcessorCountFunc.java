package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;



public class LogProcessorCountFunc extends AbstractProcessor<CtExecutable<?>>  {


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
	String snippet = "vv.spoon.logger.LogWriter.use(\"" + this.getElement(element)+ "\")";
        CtStatement state = getFactory().Code().createCodeSnippetStatement(snippet);

        element.getBody().insertBegin(state);
    }
    
    public String getElement(CtExecutable<?> element) {
    	String result = "";
    	result += element.getParent(CtClass.class).getSimpleName()+".";
        result += element.getSimpleName()+"("+element.getParameters()+")";
        return result;
    }

}
