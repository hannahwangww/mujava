package org.delete;

/**
 * Created by andilyliao on 16-8-11.
 */
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;

public class ElasticSearchCreate {

    private static String ServerIP = "127.0.0.1";// ElasticSearch server ip
    private static int ServerPort = 9300;// port
    private Client client;

    public static void main(String[] args) {

        try {
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "escluster2").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                    9300));

            DeleteResponse dResponse = client.prepareDelete("blog", "article", "11").execute()
                    .actionGet();

            if (dResponse.isFound()) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

            QueryBuilder qb1 = termQuery("title", "hibernate");


        } catch (Exception e) {
            e.printStackTrace();
        }

        deleteIndex("test");//删除名为test的索引库
    }

    // 删除索引库

    public static void deleteIndex(String indexName) {

        try {
            if (!isIndexExists(indexName)) {
                System.out.println(indexName + " not exists");
            } else {
                Settings settings = ImmutableSettings.settingsBuilder()
                        .put("cluster.name", "escluster2").build();
                TransportClient client = new TransportClient(settings);
                client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                        9300));

                DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(indexName)
                        .execute().actionGet();
                if (dResponse.isAcknowledged()) {
                    System.out.println("delete index "+indexName+"  successfully!");
                }else{
                    System.out.println("Fail to delete index "+indexName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建索引库
    public static void createIndex(String indexName) {
        try {
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "escluster2").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                    9300));

            // 创建索引库

            if (isIndexExists("indexName")) {
                System.out.println("Index  " + indexName + " already exits!");
            } else {
                CreateIndexRequest cIndexRequest = new CreateIndexRequest("indexName");
                CreateIndexResponse cIndexResponse = client.admin().indices().create(cIndexRequest)
                        .actionGet();
                if (cIndexResponse.isAcknowledged()) {
                    System.out.println("create index successfully！");
                } else {
                    System.out.println("Fail to create index!");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 判断索引是否存在 传入参数为索引库名称
    public static boolean isIndexExists(String indexName) {
        boolean flag = false;
        try {
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "escluster2").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                    9300));

            IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);

            IndicesExistsResponse inExistsResponse = client.admin().indices()
                    .exists(inExistsRequest).actionGet();

            if (inExistsResponse.isExists()) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

}