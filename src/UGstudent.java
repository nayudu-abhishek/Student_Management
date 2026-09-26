public class UGstudent extends Student{
    private static final long serialVersionId = 1L;
    private String degree;
    //constructor
    public UGstudent(int id,String name,double marks,String degree){
        super(id, name, marks);
        this.degree = degree;
    }
    //getter
    public String getDegree(){
        return degree;
    }
    @Override
    public String toString(){
        return super.toString()+" | UG | "+degree;
    }
}
