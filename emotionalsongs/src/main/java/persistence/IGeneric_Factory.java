package persistence;

import java.io.IOException;
import java.util.Map;

public interface IGeneric_Factory<T, T1> {
    public void create(T t) throws Exception, IOException;
    public T getById(T1 id) throws Exception;
    public void update(T t) throws Exception, IOException;
    public void delete(T t) throws Exception;
    public Map<T1, T> listAll();
    /*public T getByUsername(String username) throws Exception;
    public Map<Long, String> getAllUsername();
    private Boolean save();
    private void prepareDataForWriting();
    private void fillCVList();*/
}