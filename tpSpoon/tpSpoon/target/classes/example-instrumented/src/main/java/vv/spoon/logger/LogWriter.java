package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class LogWriter {

    private static PrintWriter fileWriter;
    private static HashMap<String, Integer> appels;
    private static Integer level;
    private static ShutdownHookLog Hook;
    
    public static void writeLog() {
    try {
            if (fileWriter == null) {
                fileWriter = new PrintWriter("log");
            }
            if (appels != null) {
                for (Map.Entry<String, Integer> entry : appels.entrySet()) {
                    fileWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
    
    public static void use(String string) {
        if(Hook == null) {
            Hook = new ShutdownHookLog();
            Runtime.getRuntime().addShutdownHook(Hook);
        }
        
        if (appels == null) {
            appels = new HashMap<String, Integer>();
        }
        
        Integer i = appels.get(string);
        if(i == null) {
        appels.put(string,1);
        } else {
        appels.put(string,++i);
        }
    }

    public static void enter(String string){
        if(Hook == null) {
            Hook = new ShutdownHookLog();
            Runtime.getRuntime().addShutdownHook(Hook);
        }

        try{
            if (fileWriter == null) {
                fileWriter = new PrintWriter("logtree");
            }
            if(level == null){
                level = 0;
            }else{
                ++level;
            }
            for (int i = 0; i < level; i++) {
                fileWriter.write(" | ");
            }
            fileWriter.write(string + "\n");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void leave(String string){
        if(Hook == null) {
            Hook = new ShutdownHookLog();
            Runtime.getRuntime().addShutdownHook(Hook);
        }
        if(level == null){
            level = 0;
        }else{
            --level;
        }
    }
    
}
