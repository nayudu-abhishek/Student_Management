import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;

public class ReportService {
    //Height score
    public static void topScore(Collection<Student> students,int n){
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .limit(n)
                .forEach(System.out::println);
    }
    //averages score
    public static void averageMarks(Collection<Student> students){
        students.stream()
                .mapToDouble(Student::getMarks)
                .average()
                .orElse(0.0);
    }

    public static  void passFailList(Collection<Student> students, double passMark){
        Map<Boolean, List<Student>> partion = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getMarks() >= passMark));

        System.out.println("Pass"+passMark);
        partion.get(true).forEach(System.out::println);

        System.out.println("Fail"+passMark);
        partion.get(false).forEach((System.out::println));
    }
//
//    public static void main(String[] args) {
//        List<Student> students = Arrays.asList(
//                new Student(1,"John", 85),
//                new Student(1,"Abhishek", 92),
//                new Student(1,"Rahul", 67),
//                new Student(1,"Priya", 45),
//                new Student(1,"Sneha", 78)
//        );
//        System.out.println("----- TOP 3 STUDENTS -----");
//        topScore(students, 3);
//        System.out.println("\n----- AVERAGE MARKS -----");
//        averageMarks(students,3);
//        System.out.println("\n----- PASS / FAIL -----");
//        passFailList(students, 50);
//    }
}
