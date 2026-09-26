import java.util.*;

public class Repository<T>{
    private Map<Integer,T> store = new HashMap<>();

    public void add(int id,T item){
        store.put(id,item);
    }
    public T get(int id){
       return store.get(id);
    }
    public void remove(int id ){
        store.remove(id);
    }
    public boolean Exits(int id){
       return store.containsKey(id);
    }
    public int size(){
        return store.size();
    }
    public void loadAll(Map<Integer,T>data){
        store = data;
    }
    public Map<Integer ,T> asMap(){
      return  store;
    }
}
