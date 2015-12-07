package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class LogWriter {

    private static PrintWriter fileWriter;
    private static Map<String, Integer> appels;
    private static Integer level;
    private static ShutdownHookLog Hook;

    public static void writeLog() throws FileNotFoundException {
		if(fileWriter == null) {
			fileWriter = new PrintWriter("log");
		}
		if(appels != null) {
			for(Map.Entry<String, Integer> val: appels.entrySet()) {
				fileWriter.write(val.getKey() + ": " + val.getValue() + "\n")
			}
		}
        fileWriter.close();
    }

    public static  void out(String string, boolean error) {
        try {
            PrintWriter writer = getWriter();

            if(error) {
                writer.write("ERROR: ");
            } else {
                writer.write("INFO: ");
            }
            writer.write(string + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            ShutdownHookLog shutdownHook = new ShutdownHookLog();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
    
    public static void use(String string) throws FileNotFoundException {
        if(hook == null) {
	        hook = new ShutdownHookLog();
	        Runtime.getRuntime().addShutdownHook(hook);
		}
		
	    if (appels == null) {
	        appels = new HashMap<String, Integer>();
	    }
	    
	    Integer i = appels.get(string);
	    if(i == 0) {
	    	i = 1;
	    } else {
	    	i++;
	    }
	    appels.put(string,i);
		
        
        
    }
    
}
