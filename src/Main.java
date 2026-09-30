import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import Exceptions.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Repository<Student> repository = new Repository<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //1.load the data
        Map<Integer, Student> loaded = FileHandler.load();
        if (loaded.isEmpty()) {
            seedSampleDatat();
        } else {
            repository.loadAll(loaded);
        }

        //2.showing the menu

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice:");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateMarks();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> report();
                case 6 -> {
                    FileHandler.save(repository.asMap());
                    System.out.println("Good Bye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice ,Try again!");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("===Student Management System");
        System.out.println("1.Add Student");
        System.out.println("2.Update Marks");
        System.out.println("3.Delete Student");
        System.out.println("4.Search Student");
        System.out.println("5.Report");
        System.out.println("6.Save & Exit");
    }

    private static void addStudent() {
        int id = readInt("Id: ");
        String name = readLine("Name: ");
        Double marks = readDouble("Marks: ");
        String type = readLine("Type of(PG/UG)").trim().toUpperCase();
        String extra = readLine(type.equals("PG") ? "Speclization:" : "Degree:");

        try {
            if (repository.exists(id)) {
                throw new DuplicateIdException("Student :" + id + "Already exits");
            }
            if (marks < 0 || marks > 100) {
                throw new InvalidMarksException("Marks should  be between 0 and 100,got" + marks);
            }
            Student student = type.equals("PG")
                    ? new PGstudent(id, name, marks, extra)
                    : new UGstudent(id, name, marks, extra);
            repository.add(id, student);
            System.out.println("Added" + student);
        }catch(DuplicateIdException | InvalidMarksException e){
            System.out.println("could not add the student"+e.getMessage());
        }
    }
    //2.update method implementation
    private  static void updateMarks(){
       int id = readInt("Id to update:");
        Student student = repository.get(id);
        if(student == null){
            System.out.println("No Student with this id"+id);
            return;
        }
        double newMarks = readDouble("Newmarks:");
        try{
            if(newMarks <0 || newMarks>100){
                throw new InvalidMarksException("Marks should  be between 0 and 100,got"+newMarks);
            }
            student.setMarks(newMarks);
            System.out.println("Updated"+student);
        }catch(InvalidMarksException e){
            System.out.println("Updated rejected"+e.getMessage());
        }
    }
    //3.Delete method implementation
    private static void deleteStudent(){
        int id = readInt("Id to remove");
        if(!repository.exists(id)){
            System.out.println("No student with thid id"+id);
            return;
        }repository.remove(id);
        System.out.println("Deleted the student");
    }
    //4.Search method implementation
    private static void searchStudent(){
        int id = readInt("Id to search:");
        Student student = repository.get(id);
        System.out.println(student != null ? student : "Not Found");
    }
    //5.Report
    private static void report(){
        Collection<Student> all = repository.getAll();
        if(all.isEmpty()){
            System.out.println("No data to report on");
            return;
        }
        ReportService.topScore(all,3);
        ReportService.averageMarks(all);
        ReportService.passFailList(all,50.0);
    }
    //Sample data
    private static void seedSampleDatat(){
        repository.add(101,new UGstudent(101,"john",98,"BTech"));
        repository.add(101, new UGstudent(101, "Arjun", 78.5, "B.Tech CSE"));
        repository.add(102, new UGstudent(102, "Divya", 91.0, "B.Sc Physics"));
        repository.add(103, new PGstudent(103, "Karthik", 55.0, "M.Tech AI"));
        repository.add(104, new UGstudent(104, "Meena", 40.0, "B.Com"));
        repository.add(105, new PGstudent(105, "Rahul", 88.0, "MCA"));
        System.out.println("Seed"+repository.size()+"same students");
    }

    //Input helpers

    public static int readInt(String prompt){
        System.out.println(prompt);
        while (!sc.hasNextInt()){
            System.out.println("Please enter the number");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double readDouble(String prompt){
        System.out.println(prompt);
        while(!sc.hasNextDouble()){
            System.out.println("please enter the number");
            sc.next();
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    private static String readLine(String prompt){
        System.out.println(prompt);
        return sc.nextLine();
    }
}