public class IMF {

    private static IMF imf;

    public static IMF getFund() {
        //add your code here - добавь код тут
        synchronized (IMF.class) {
            if (imf == null) {
                imf = new IMF();
            }//end if
            return imf;
        }
    }

    private IMF() {
    }
}