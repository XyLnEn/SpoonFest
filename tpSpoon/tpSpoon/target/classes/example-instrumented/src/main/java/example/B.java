package example;


public class B {
    public void mth1(int i) {
        vv.spoon.logger.LogWriter.enter("B.mth1(int i)");
        java.lang.System.out.println("B.mth1(int i)");
        example.C c = new example.C(i);
        int result = c.mth1();
        java.lang.System.out.println(("result = " + result));
        vv.spoon.logger.LogWriter.leave("B.mth1(int i)");
    }

    public void mth2() {
        vv.spoon.logger.LogWriter.enter("B.mth2()");
        java.lang.System.out.println("B.mth2()");
        vv.spoon.logger.LogWriter.leave("B.mth2()");
    }
}

