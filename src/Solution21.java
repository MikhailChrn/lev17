import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

//Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
//1.(+) Считать с консоли 2 имени файла.
//2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines.
//В методе joinData:
//3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines.
//4. Если условие из п.3 не выполнено, то:
//4.1. очистить allLines от данных
//4.2. выбросить исключение CorruptedDataException
//Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
//Не забудь закрыть потоки.

public class Solution21 {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Scanner scanner1 = new Scanner(reader.readLine());
            Scanner scanner2 = new Scanner(reader.readLine());
            while (scanner1.hasNextLine()) allLines.add(scanner1.nextLine());
            while (scanner2.hasNextLine()) forRemoveLines.add(scanner2.nextLine());
            scanner1.close();
            scanner2.close();
        } catch (IOException IO) {}
    }//end static


    public static void main(String[] args) throws IOException {
        /*Charset ANSI = Charset.forName("windows-1251");
        FileReader fileReader1 = new FileReader("C:\\java\\data1.txt", ANSI);
        FileReader fileReader2 = new FileReader("C:\\java\\data1.txt", ANSI);*/
        Solution21 solution = new Solution21();
        solution.joinData();
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) allLines.removeAll(forRemoveLines);
        else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }//end void joinData()
}
