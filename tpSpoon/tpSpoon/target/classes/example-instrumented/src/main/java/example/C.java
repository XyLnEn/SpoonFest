package example;


public class C {
    private int i;

    public C(int i) {
        vv.spoon.logger.LogWriter.enter("C.C(int i)");
        java.lang.System.out.println("C.C(int i)");
        this.i = i;
        vv.spoon.logger.LogWriter.leave("C.C(int i)");
    }

    public int mth1() {
        vv.spoon.logger.LogWriter.enter("C.mth1()");
        java.lang.System.out.println("C.mth1()");
        vv.spoon.logger.LogWriter.leave("C.mth1()");
        return 100 / (i);
    }
}

