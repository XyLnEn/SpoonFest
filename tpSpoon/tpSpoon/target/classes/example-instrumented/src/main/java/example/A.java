package example;


public class A {
    public static void main(java.lang.String[] args) {
        vv.spoon.logger.LogWriter.enter("A.main(java.lang.String[] args)");
        java.lang.System.out.println("A.main(String[] args)");
        example.A a = new example.A();
        a.mth1(java.lang.Integer.parseInt(args[0]));
        vv.spoon.logger.LogWriter.leave("A.main(java.lang.String[] args)");
    }

    public void mth1(int count) {
        vv.spoon.logger.LogWriter.enter("A.mth1(int count)");
        java.lang.System.out.println("A.mth1(int count)");
        example.B b = new example.B();
        for (int i = 0 ; i < count ; i++) {
            try {
                b.mth1(i);
                b.mth2();
            } catch (java.lang.Exception e) {
                java.lang.System.err.println("error in A.mth1(int count)");
            }
        }
        vv.spoon.logger.LogWriter.leave("A.mth1(int count)");
    }
}

