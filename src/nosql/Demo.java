package nosql;

import java.util.Random;

import toools.StopWatch;
import toools.text.TextUtilities;

public class Demo
{
	public static void main(String[] args)
	{
		 //DB<String> db = new MemoryDB<>();
		DB<String> db = new FlatDirDB<>("flat");
		//DB<String> db = new HierarchicalDirDB<>("hierarchical");
		int n = 10000;
		StopWatch sw = new StopWatch();
		
		for (int i = 0; i < n; ++i)
		{
			int id = db.add(TextUtilities.pickRandomString(new Random(), 1, 100));
		}

		System.out.println(sw);
		sw.reset();

		for (int id : db.getIDs())
		{
			//System.out.println(id + "\t=>\t" + db.get(id));
			db.get(id).hashCode();
		}

		System.out.println(sw);
}
}
