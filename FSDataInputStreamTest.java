package org.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FSDataInputStreamTest {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		String uri = "hdfs://master:9000/b.txt";
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FSDataInputStream in = null;
		try {
			in = fs.open(new Path(uri));
			in.seek(0);// 也有seek(int
						// pos)，getPos()方法，还有一个用户不使用的方法：seekToNewSource(int pos)
			// 该方法在读取失败的时候，hadoop系统调用从副本中读取数据，
			// 因此hadoop有了副本基本是稳定可靠的；读取可以seek，后面的写不可以，hadoop只能顺序写，不能随机写
			IOUtils.copyBytes(in, System.out, 4096, true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				IOUtils.closeStream(in);
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}
	}
}
