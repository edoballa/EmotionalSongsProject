/**
* This package contains the classes that create and manage the various factory.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package persistence;

import java.io.IOException;
import java.util.Map;

/**
 * This interface contains the CRUD methods.
 * It also contains the method to return the entire list of objects.
 *
 * @param <T> Generic object.
 * @param <T1> Key type of the generic object.
 */
public interface IGeneric_Factory<T, T1> {
    public void create(T t) throws Exception, IOException;
    public T getById(T1 id) throws Exception;
    public void update(T t) throws Exception, IOException;
    public void delete(T t) throws Exception;
    public Map<T1, T> listAll();
}
