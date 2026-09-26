import java.io.Serializable;

public class Student implements Serializable {
    private static long serialVerionId = 1;
    private int id;
    private String name;
    private double marks;

    public Student(int id,String name,double marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    //Getter

    public int getId(){
        return  id;
    }
    public String getName(){
        return name;
    }
    public double getMarks(){
        return marks;
    }

    //Setter

    public  void setId(int id){
        this.id = id;
    }
   public void setName(String name){
        this.name = name;
   }
   public void setMarks(double marks){
        this.marks = marks;
   }
   @Override
   public String toString(){
        return  String.format("%-5d | %-10s | %6.2f",id, name,marks);
   }
}
