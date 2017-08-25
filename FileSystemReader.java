package org.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemReader {
	public static void main(String args[]) {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		String uri = "hdfs://master:9000/b.txt";
		FileSystem fs = null;
		InputStream is = null;
		try {
			fs = FileSystem.get(URI.create(uri), conf);
			is = fs.open(new Path(uri));
			IOUtils.copyBytes(is, System.out, 1024, false);
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			try {
				IOUtils.closeStream(is);
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}
	}
}
