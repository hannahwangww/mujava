package org.update;

/**
 * Created by andilyliao on 16-8-9.
 */

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticSearchUpdate {

    private static Client client;

    public static void main(String[] args) {

        try {
            // client startup
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "escluster2").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                    9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
        upMethod1();

    }

    public static void upMethod1() {
        try {
            // 方法1:创建一个UpdateRequest,然后将其发送给client.
            UpdateRequest uRequest = new UpdateRequest();
            uRequest.index("blog");
            uRequest.type("article");
            uRequest.id("22");
            uRequest.doc(jsonBuilder().startObject().field("content", "学习目标 掌握java泛型的产生意义ssss").endObject());
            client.update(uRequest).get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void upMethod2() {
        // 方法2:prepareUpdate() 使用doc更新索引
        try {
            client.prepareUpdate("blog", "article", "1")
                    .setDoc(jsonBuilder().startObject().field("content", "SVN与Git对比。。。").endObject()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void upMethod3() {
        // 方法3: 增加新的字段

        try {
            UpdateRequest updateRequest = new UpdateRequest("blog", "article", "1")
                    .doc(jsonBuilder().startObject().field("commet", "0").endObject());
            client.update(updateRequest).get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void upMethod4() {
        // 方法4：upsert 如果文档不存在则创建新的索引
        try {
            IndexRequest indexRequest = new IndexRequest("blog", "article", "10").source(jsonBuilder().startObject()
                    .field("title", "Git安装10").field("content", "学习目标 git。。。10").endObject());

            UpdateRequest uRequest2 = new UpdateRequest("blog", "article", "10").doc(
                    jsonBuilder().startObject().field("title", "Git安装").field("content", "学习目标 git。。。").endObject())
                    .upsert(indexRequest);
            client.update(uRequest2).get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
