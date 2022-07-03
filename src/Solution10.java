import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


//CRUD - Create, Read, Update, Delete.
//

public class Solution10 {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //напишите тут ваш код

        if (args[0].equals("-c")) {
            Date inputDate = new SimpleDateFormat("d/M/y").parse(args[3]);
            if (args[2].equals("м")) allPeople.add(Person.createMale(args[1], inputDate));
            if (args[2].equals("ж")) allPeople.add(Person.createFemale(args[1], inputDate));
            System.out.println(allPeople.size() - 1);
        }//end if("-c")

        if (args[0].equals("-r")) {
            Person currentPerson = allPeople.get(Integer.valueOf(args[1]));
            String currentSex = ((currentPerson.getSex() == Sex.MALE) ? "м" : "ж");
            SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-y", Locale.ENGLISH);
            System.out.println(currentPerson.getName()+ " " + currentSex + " " + dateFormat.format(currentPerson.getBirthDate()));
        }//end if("-r")

        if (args[0].equals("-u")) {
            Sex updateSex = ((args[3].equals("м")) ? Sex.MALE : Sex.FEMALE);
            Date inputDate = new SimpleDateFormat("d/M/y").parse(args[4]);
            allPeople.get(Integer.valueOf(args[1])).setName(args[2]);
            allPeople.get(Integer.valueOf(args[1])).setSex(updateSex);
            allPeople.get(Integer.valueOf(args[1])).setBirthDate(inputDate);
        }//end if("-u")

        if (args[0].equals("-d")) {
            allPeople.get(Integer.valueOf(args[1])).setName(null);
            allPeople.get(Integer.valueOf(args[1])).setSex(null);
            allPeople.get(Integer.valueOf(args[1])).setBirthDate(null);
        }//end if("-d")

        for (Person person : allPeople)
            System.out.println(person.getName() + " " + person.getSex() + " " + person.getBirthDate());

    }//end void main()
}
