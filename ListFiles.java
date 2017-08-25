package org.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

class RegexExcludePathFilter implements PathFilter {
	private final String regex;

	public RegexExcludePathFilter(String regex) {
		this.regex = regex;
	}

	public boolean accept(Path path) {
		return !path.toString().matches(regex);
	}
}

public class ListFiles {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		// 查看某个文件Block在HDFS集群的位置：
		FileSystem fs = FileSystem.get(conf);
		FileStatus fst[] = fs.globStatus(
				new Path("hdfs://master:9000/ceshi/*"),
				new RegexExcludePathFilter(".*a1.txt"));
		// FileStatus[] fst= fs.globStatus(new
		// Path("hdfs://master:9000/user/hadoop/test/*"));
		Path[] listedPaths = FileUtil.stat2Paths(fst);
		for (Path p : listedPaths) {
			System.out.println(p);
		}
		fs.close();
	}
}
