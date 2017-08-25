package org.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

public class SeqReader {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		String uriin = "hdfs://master:9000/";
		FileSystem fs = FileSystem.get(URI.create(uriin), conf);
		Path path = new Path("test.seq");
		SequenceFile.Reader r = null;
		try {
			r = new SequenceFile.Reader(fs, path, conf);
			Writable k = (Writable) ReflectionUtils.newInstance(
					r.getKeyClass(), conf);
			Writable v = (Writable) ReflectionUtils.newInstance(
					r.getValueClass(), conf);
			while (r.next(k, v)) {
				System.out.println(k + "\t" + v);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		} finally {
			IOUtils.closeStream(r);
			try {
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}
	}
}
