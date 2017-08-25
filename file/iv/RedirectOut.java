package org.file.iv;

import java.io.*;
public class RedirectOut
{
	public static void main(String[] args)
	{
		try{
			PrintStream ps = new PrintStream(new FileOutputStream("out.txt"));
			System.setOut(ps);
			System.out.println("普通字符串");
			System.out.println(new RedirectOut());
			ps.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}

