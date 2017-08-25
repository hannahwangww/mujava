package org.file.vi.resolve;

import java.io.*;

public class ResolveTest
{
	public static void main(String[] args)
	{
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("tran.txt"));
			ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("tran.txt"));
			oos.writeObject(Orientation.HORIZONTAL);
			Orientation ori = (Orientation)ois.readObject();
			System.out.println(ori == Orientation.HORIZONTAL);
			ois.close();
			oos.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}