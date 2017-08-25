package org.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class SeqWrite {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		String uriin = "hdfs://master:9000/";
		FileSystem fs = FileSystem.get(URI.create(uriin), conf);
		Path path = new Path("test.seq");
		SequenceFile.Writer w = null;
		IntWritable k = new IntWritable();
		Text v = new Text();
		try {
			w = SequenceFile.createWriter(fs, conf, path, k.getClass(),
					v.getClass());
			for (int i = 0; i < 100; i++) {
				k.set(i);
				v.set("abc");
				w.append(k, v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(w);
			try {
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}
	}
}
