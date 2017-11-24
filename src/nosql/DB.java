package nosql;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public interface DB<E>
{
	Set<Integer> getIDs();

	int getID(E e);
	
	int add(E e);

	void remove(int id);

	boolean containsKey(int hashCode);

	E get(int hashcode);

	Iterator<E> search(Predicate<E> p);
}
