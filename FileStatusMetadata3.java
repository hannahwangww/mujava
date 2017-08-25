package org.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileStatusMetadata3 {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		// 查看某个文件Block在HDFS集群的位置：
		String uri = "hdfs://master:9000/b.txt";
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FileStatus fst = fs.getFileStatus(new Path(uri));
		BlockLocation blks[] = fs.getFileBlockLocations(fst, 0, fst.getLen());
		int blklen = blks.length;
		for (int i = 0; i < blklen; i++) {
			String hosts[] = blks[i].getHosts();
			System.out.println("block_" + i + "在：" + hosts[0]);
		}
		fs.close();
	}
}
