package org.hdfs;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FSDataOutputStreamTest {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		String uriin = "/home/hadoop/access.log";
		InputStream in = new BufferedInputStream(new FileInputStream(uriin));
		String uri = "hdfs://master:9000/a.log";
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FSDataOutputStream out = null;
		try {
			out = fs.create(new Path(uri), new Progressable() {
				@Override
				public void progress() {
					System.out.println(".");
				}
			});
			IOUtils.copyBytes(in, out, 4096, true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				IOUtils.closeStream(out);
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}
	}
}
