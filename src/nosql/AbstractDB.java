package nosql;

import java.util.Iterator;
import java.util.function.Predicate;

import toools.io.file.RegularFile;
import toools.thread.Generator;

public abstract class AbstractDB<E> implements DB<E>
{
	public void addJSON(RegularFile f)
	{
		
	}

	
	
	@Override
	public Iterator<E> search(Predicate<E> p)
	{
		Iterator<Integer> i = getIDs().iterator();

		return new Generator<E>()
		{
			@Override
			public void produce()
			{
				while (i.hasNext())
				{
					E e = get(i.next());

					if (p.test(e))
					{
						deliver(e);
					}
				}
			}
		}.iterator();
	}

	public void remove(E e)
	{
		remove(getID(e));
	}
	
	@Override
	public int getID(E e)
	{
		return System.identityHashCode(e);
	}
}
