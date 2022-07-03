import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Транзакционность
*/

public class Solution21_2 {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    static {
        try {
            Scanner sc = new Scanner(System.in);
            String str1 = sc.nextLine(); //считываем файл #1
            String str2 = sc.nextLine(); //считываем файл #2
            allLines = Files.readAllLines(Paths.get(str1)); //переносим все из файла #1 в allLines. (Нашел в гугле)
            forRemoveLines = Files.readAllLines(Paths.get(str2)); //переносим все из файла #2 в forRemoveLines. (Нашел в гугле)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Solution21_2 solution = new Solution21_2();
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
