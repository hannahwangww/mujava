package org.file.iii;

import java.io.*;

public class PrintStreamTest
{
	public static void main(String[] args)
	{
		try{
			FileOutputStream fos = new FileOutputStream("/opt/test.txt");
			PrintStream ps = new PrintStream(fos);
			ps.println("普通字符串");
			ps.println(new PrintStreamTest());
			ps.close();
//			fos.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}

