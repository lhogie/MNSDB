package nosql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import toools.io.file.AbstractFile;
import toools.io.file.Directory;
import toools.io.file.RegularFile;

public class HierarchicalDirDB<E> extends OneFilePerEntryDB<E>
{

	public HierarchicalDirDB(String name)
	{
		super(name);
	}

	@Override
	public Set<Integer> getIDs()
	{
		Set<Integer> r = new HashSet<>();
		List<Directory> list = new ArrayList<>();
		list.add(getDataDirectory());

		while ( ! list.isEmpty())
		{
			Directory d = list.remove(0);

			for (AbstractFile f : d.getChildren())
			{
				if (f instanceof RegularFile)
				{
					String n = ((RegularFile) f).getNameWithoutExtension();
					Directory parent = f.getParent();

					while (!parent.equals(getDataDirectory()))
					{
						n = parent.getName() + n;
						parent = parent.getParent();
					}

					r.add(Integer.valueOf(n));
				}
				else if (f instanceof Directory)
				{
					list.add((Directory) f);
				}
			}
		}

		return r;
	}

	@Override
	public String getPath(int id)
	{
		StringBuilder b = new StringBuilder();
		char[] a = String.valueOf(id).toCharArray();

		for (int i = 0; i < a.length; ++i)
		{
			b.append(a[i]);

			if (i < a.length - 1)
			{
				b.append('/');
			}
		}

		b.append(".e");

		return b.toString();
	}

}
