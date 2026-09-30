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
}
