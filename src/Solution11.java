import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//CrUD Batch - multiple Creation, Updates, Deletion.

public class Solution11 {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        allPeople.add(Person.createMale("Иванов Иван", new Date()));
        allPeople.add(Person.createMale("Петров Петр", new Date()));
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        try {
            switch (args[0]) {
                case "-c": {
                    int countCreating = (args.length - 1) / 3;
                    synchronized (allPeople) {
                        for (int i = 0; i < countCreating; i++) {
                            Date inputDate = new SimpleDateFormat("d/M/y").parse(args[3 + (i * 3)]);
                            if (args[2 + (i * 3)].equals("м"))
                                allPeople.add(Person.createMale(args[1 + (i * 3)], inputDate));
                            if (args[2 + (i * 3)].equals("ж"))
                                allPeople.add(Person.createFemale(args[1 + (i * 3)], inputDate));
                            System.out.println(allPeople.size() - 1);
                        }//end for countCreating
                    }
                    break;
                }//end case "-c"

                case "-u": {
                    int countUpdating = (args.length - 1) / 4;
                    synchronized (allPeople) {
                        for (int i = 0; i < countUpdating; i++) {
                            Sex updateSex = ((args[3 + (i * 4)].equals("м")) ? Sex.MALE : Sex.FEMALE);
                            Date inputDate = new SimpleDateFormat("d/M/y").parse(args[4 + (i * 4)]);
                            allPeople.get(Integer.valueOf(args[1 + (i * 4)])).setName(args[2 + (i * 4)]);
                            allPeople.get(Integer.valueOf(args[1 + (i * 4)])).setSex(updateSex);
                            allPeople.get(Integer.valueOf(args[1 + (i * 4)])).setBirthDate(inputDate);
                        }//end for countUpdating
                    }
                    break;
                }//end case "-u"

                case "-d": {
                    int countDeleting = args.length - 1;
                    synchronized (allPeople) {
                        for (int i = 0; i < countDeleting; i++) {
                            allPeople.get(Integer.valueOf(args[1 + i])).setName(null);
                            allPeople.get(Integer.valueOf(args[1 + i])).setSex(null);
                            allPeople.get(Integer.valueOf(args[1 + i])).setBirthDate(null);
                        }//end for countDeleting
                    }
                    break;
                }//end case "-d"

                case "-i": {
                    int countOutput = args.length - 1;
                    synchronized (allPeople) {
                        for (int i = 0; i < countOutput; i++) {
                            Person currentPerson = allPeople.get(Integer.valueOf(args[1 + i]));
                            String currentSex = ((currentPerson.getSex() == Sex.MALE) ? "м" : "ж");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-y", Locale.ENGLISH);
                            System.out.println(currentPerson.getName() + " " + currentSex + " " + dateFormat.format(currentPerson.getBirthDate()));
                        }//end for countOutput
                    }
                    break;
                }//end case "-i"
            }//end switch

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }//end catch block

        //---------------------------------------------------
        /*for (Person person : allPeople)
            System.out.println(person.getName() + " " + person.getSex() + " " + person.getBirthDate());*/

    }//end void main
}
