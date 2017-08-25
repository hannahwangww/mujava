package org.hdfs;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
public class MyHDFS {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		FileSystem fs = null;
		FSDataInputStream in = null;
		FSDataOutputStream out = null;
		try {
			fs = FileSystem.get(conf);
			fs.mkdirs(new Path("hdfs://master:9000/testhdfs1"));//创建文件夹
			fs.copyFromLocalFile(new Path("/home/hadoop/derby.log"), new Path(
					"hdfs://master:9000/testhdfs1/derby.log"));//上传文件到规定文件夹下
			FileStatus fst = fs.getFileStatus(new Path(
					"hdfs://master:9000/testhdfs1/derby.log"));
			if (!fst.isDir()) {
				System.out.println("这是个文件");
			}
			System.out.println("路径：" + fst.getPath());
			System.out.println("长度：" + fst.getLen());
			System.out.println("文件修改日期："
					+ new Timestamp(fst.getModificationTime()).toString());
			System.out.println("上次文件访问日期："
					+ new Timestamp(fst.getAccessTime()).toString());
			System.out.println("文件备份数：" + fst.getReplication());
			System.out.println("文件块大小：" + fst.getBlockSize());
			System.out.println("文件所有者：" + fst.getOwner());
			System.out.println("文件所在分组：" + fst.getGroup());
			System.out.println("文件权限：" + fst.getPermission().toString());
			
			fst = fs.getFileStatus(new Path("hdfs://master:9000/testhdfs1"));
			if (fst.isDir()) {
				System.out.println("这是个目录");
			}
			System.out.println("目录路径：" + fst.getPath());
			System.out.println("目录长度：" + fst.getLen());
			System.out.println("目录修改日期："
					+ new Timestamp(fst.getModificationTime()).toString());
			System.out.println("上次目录访问日期："
					+ new Timestamp(fst.getAccessTime()).toString());
			System.out.println("目录备份数：" + fst.getReplication());
			System.out.println("目录块大小：" + fst.getBlockSize());
			System.out.println("目录所有者：" + fst.getOwner());
			System.out.println("目录所在分组：" + fst.getGroup());
			System.out.println("目录权限：" + fst.getPermission().toString());
			
			
			in = fs.open(new Path(
					"hdfs://master:9000/testhdfs1/derby.log"));
			in.seek(0);
			out = fs.create(new Path(
					"hdfs://master:9000/testhdfs1/derby1.log"), new Progressable() {
				@Override
				public void progress() {
					System.out.println(".");
				}
			});
			IOUtils.copyBytes(in, out, 4096, true);
			fs.delete(new Path("hdfs://master:9000/testhdfs1"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				IOUtils.closeStream(in);
				IOUtils.closeStream(out);
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}
	}
}
