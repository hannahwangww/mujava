package org.file.ii;

import java.io.*;

public class FileInputStreamTest
{
	public static void main(String[] args) throws IOException
	{
		File f=new File("/opt/test/nohup.out");
		InputStream fis = new FileInputStream(f);
		byte[] bbuf = new byte[fis.available()];
		int hasRead = 0;
		while ((hasRead = fis.read(bbuf)) > 0 )
		{
			System.out.print(new String(bbuf , 0 , hasRead ));
		}
		fis.close();
	}
}
