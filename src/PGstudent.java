public class PGstudent extends Student{
    private static final long serialVersoinId = 1L;
    private String specilization;
    //constructor
    public PGstudent(int id,String name,double marks,String specilization){
        super(id,name,marks);
        this.specilization = specilization;
    }
    //getter

    public String getSpecilization() {
        return specilization;
    }

    @Override
    public String toString(){
        return super.toString()+" | PG "+specilization;
    }

    public static void main(String[] args) {
        PGstudent p = new PGstudent(1,"Jhon",100,"MTech");
        System.out.println(p.getSpecilization());
        System.out.println(p.toString());
    }
}
