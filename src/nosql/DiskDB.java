package nosql;

import toools.io.file.Directory;

public abstract class DiskDB<E> extends AbstractDB<E>
{
	private final Directory dataDirectory;

	public DiskDB(String name)
	{
		this.dataDirectory = new Directory("$HOME/luccidb/" + name);
		
		if (!dataDirectory.exists())
		{
			dataDirectory.mkdirs();
		}
	}

	
	public Directory getDataDirectory()
	{
		return dataDirectory;
	}


}
