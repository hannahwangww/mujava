package org.mapping;

/**
 * Created by andilyliao on 16-8-11.
 */
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class CreateIndex {



    /**
     * 创建索引名称
     * @param indices 索引名称
     */
    public static void createCluterName(String indices){
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "els").build();
        TransportClient client = new TransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress("192.168.137.18",
                9300));
        client.admin().indices().prepareCreate(indices).execute().actionGet();
        client.close();
    }

    /**
     * 创建mapping(feid("indexAnalyzer","ik")该字段分词IK索引 ；feid("searchAnalyzer","ik")该字段分词ik查询；具体分词插件请看IK分词插件说明)
     * @param indices 索引名称；
     * @param mappingType 索引类型
     * @throws Exception
     */
    public static void createMapping(String indices,String mappingType)throws Exception{
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "els").build();
        TransportClient client = new TransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress("192.168.137.18",
                9300));
        new XContentFactory();
        XContentBuilder builder=XContentFactory.jsonBuilder()
                .startObject()
                .startObject(indices)
                .startObject("properties")
                .startObject("id").field("type", "integer").field("store", "yes").endObject()
                .startObject("kw").field("type", "string").field("store", "yes").field("indexAnalyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .startObject("edate").field("type", "date").field("store", "yes").field("indexAnalyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .endObject()
                .endObject()
                .endObject();
        PutMappingRequest mapping = Requests.putMappingRequest(indices).type(mappingType).source(builder);
        client.admin().indices().putMapping(mapping).actionGet();
        client.close();
    }
    public static void main(String[] args)throws Exception {
//        createMapping("lianan", "lianan");
        createCluterName("lianan");
    }

}
