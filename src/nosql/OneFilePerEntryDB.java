package nosql;

import java.io.IOException;

import toools.ExceptionUtilities;
import toools.io.file.RegularFile;
import toools.io.serialization.Serializer;

public abstract class OneFilePerEntryDB<E> extends DiskDB<E>
{
	public OneFilePerEntryDB(String dbName)
	{
		super(dbName);
	}

	@Override
	public E get(int id)
	{
		RegularFile f = getFile(id);

		if (f == null || ! f.exists())
		{
			throw new IllegalArgumentException("unknown ID: " + id);
		}
		else
		{
			try
			{
				return (E) Serializer.getDefaultSerializer().fromBytes(f.getContent());
			}
			catch (IOException e)
			{
				throw new IllegalStateException(ExceptionUtilities.toString(e));
			}
		}
	}

	public RegularFile getFile(int id)
	{
		return new RegularFile(getDataDirectory(), getPath(id));
	}

	public abstract String getPath(int id);


	@Override
	public boolean containsKey(int h)
	{
		return getFile(h).exists();
	}

	@Override
	public int add(E e)
	{
		int id = getID(e);

		try
		{
			RegularFile f = getFile(id);
			
			if(!f.getParent().exists())
			{
				f.getParent().mkdirs();
			}
			
			f.setContent(Serializer.getDefaultSerializer().toBytes(e));
		}
		catch (IOException ex)
		{
			throw new IllegalStateException(ExceptionUtilities.toString(ex));
		}

		return id;
	}

	@Override
	public void remove(int id)
	{
		getFile(id).delete();
	}
}
