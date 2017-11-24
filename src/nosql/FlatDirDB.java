package nosql;

import java.util.HashSet;
import java.util.Set;

public class FlatDirDB<E> extends OneFilePerEntryDB<E>
{
	public FlatDirDB(String name)
	{
		super(name);
	}

	public String getPath(int id)
	{
		return String.valueOf(id);
	}

	@Override
	public Set<Integer> getIDs()
	{
		Set<Integer> r = new HashSet<>();

		for (String s : getDataDirectory().toFile().list())
		{
			r.add(Integer.valueOf(s));
		}

		return r;
	}
}
