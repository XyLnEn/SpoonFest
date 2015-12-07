package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.reference.CtExecutableReference;


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
	String snippet = "vv.spoon.logger.LogWriter.use(\"" + element.getSimpleName() + "(" + getAttributOf(element) + "\")";
        CtStatement state = getFactory().Code().createCodeSnippetStatement(snipper);

        executable.getBody().insertBegin(state);
    }
    
    public String getAttributeOf(CtExecutable<?> element) {
    string result = "";
    	for(CtParameter<?> param : element.getParameters()) {
    		result = result + param.getSimpleName() + ", " ;
    	}
    	return result;
    }

}
