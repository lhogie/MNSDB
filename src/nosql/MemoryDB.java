package nosql;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MemoryDB<E> extends AbstractDB<E>
{
	private Map<Integer, E> m = new HashMap<>();

	@Override
	public Set<Integer> getIDs()
	{
		return m.keySet();
	}

	@Override
	public int add(E e)
	{
		int id = getID(e);
		m.put(id, e);
		return id;
	}

	@Override
	public boolean containsKey(int hashCode)
	{
		return m.containsKey(hashCode);
	}

	@Override
	public E get(int hashcode)
	{
		return m.get(hashcode);
	}

	@Override
	public void remove(int id)
	{
		m.remove(id);
	}

}
