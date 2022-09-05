/**
 * This package contains the classes that create and manage the various factory.
 */
package persistence;

import java.io.IOException;
import java.util.Map;

/**
 * <p>This interface contains the CRUD methods.
 * It also contains the method to return the entire list of objects.
 * T Generic object is the Object.
 * T1 Generic object is Key type of the T1.</p>
 *
 * @author Diana Cantaluppi, Matr. 744457 Sede Como.
 * @author Edoardo Ballabio, Matr. 745115 Sede Como.
 */
public interface IGeneric_Factory<T, T1> {
    public void create(T t) throws Exception, IOException;
    public T getById(T1 id) throws Exception;
    public void update(T t) throws Exception, IOException;
    public void delete(T t) throws Exception;
    public Map<T1, T> listAll();
}
