import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private static final String FILE_NAME = "Student.dat";
    public static void save(Map<Integer,Student>data){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(data);
            System.out.println("saved"+data.size()+"record(S) to"+FILE_NAME);
        } catch (IOException e) {
            System.out.println("could not save data:"+e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static Map<Integer,Student> load(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            System.out.println("no existing file found -> starting fresh");
            return  new HashMap<>();
        }
        try(ObjectInputStream ois = new ObjectInputStream( new FileInputStream(FILE_NAME))){
            Map<Integer,Student> data = (Map<Integer, Student>) ois.readObject();
            System.out.println("Loaded" + data.size()+"record(s) from"+FILE_NAME);
            return data;
        }catch(IOException | ClassNotFoundException e) {
            System.out.println("could not load the data"+e.getMessage());
            return new HashMap<>();
        }
    }
}
