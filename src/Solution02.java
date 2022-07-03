import java.util.ArrayList;
import java.util.List;

//lev 17: Создай public static нить SortThread, которая в методе run отсортирует статический массив testArray используя метод sort.

public class Solution02 {
    public static int threadCount = 10;
    public static int[] testArray = new int[1000];

    static {
        for (int i = 0; i < Solution02.testArray.length; i++) {
            testArray[i] = i;
        }
    }//end static block

    public static void main(String[] args) throws InterruptedException {
        StringBuffer expectedResult = new StringBuffer();
        for (int i = 1000 - 1; i >= 0; i--) {
            expectedResult.append(i).append(" "); //ожидаемый результат
        }

        initThreads(); //создание нитей

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < testArray.length; i++) {
            result.append(testArray[i]).append(" "); //фактический результат после сортировки
        }
        System.out.println(result);
        System.out.println((result.toString()).equals(expectedResult.toString()));
    }//end void main()

    public static void initThreads() throws InterruptedException {
        List<Thread> threads = new ArrayList<Thread>(threadCount);
        for (int i = 0; i < threadCount; i++) threads.add(new SortThread());
        for (Thread thread : threads) thread.start(); //сначала все стартуют
        for (Thread thread : threads) thread.join(); //потом все ждут
    }//end void initThreads()

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int k = array[i];
                    array[i] = array[j];
                    array[j] = k;
                }
            }
        }
    }//end void sort()

    public static class SortThread extends Thread {
        @Override
        public void run() {
            sort(testArray); //запуск сортировки массива
        }
    }//end class SortThread
}
