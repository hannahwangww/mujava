package org.get;

/**
 * Created by andilyliao on 16-8-9.
 */

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.builders.ShapeBuilder;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class ElasticSearchGet {

    public static void main(String[] args) {
        // client startup
        try {
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "escluster2").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                    9300));

            QueryBuilder qb1 = termQuery("title", "hibernate");

            QueryStringQueryBuilder st=queryString("aaa aaa");
            st.field("xxx").field("xxxx");
            QueryBuilder qb2= multiMatchQuery("git", "title","content");

            QueryBuilder bq=boolQuery().must(qb1).must(qb2).must(st);

            FilterBuilder filterBuilder = FilterBuilders.andFilter(FilterBuilders
                    .existsFilter("title").filterName("exist"), FilterBuilders
                    .termFilter("title", "elastic"));

            // Using the geo shape query
            QueryBuilder queryBuilder = QueryBuilders.geoShapeQuery("location", ShapeBuilder
                    .newCircleBuilder().center(10, 100));

            SearchResponse response = client.prepareSearch("blog").setTypes("article").addHighlightedField("title")
                    .setQuery(bq)
                    .setPostFilter(filterBuilder)
                    .setHighlighterPreTags("<1>", "<2>")
                    .setHighlighterPostTags("</1>", "</2>").addSort(SortBuilders.fieldSort("title"))
                    .addSort("_score", SortOrder.DESC).setFrom(10)
                    .setSize(20).execute()
                    .actionGet();

            SearchHits hits = response.getHits();
            if (hits.totalHits() > 0) {
                for (SearchHit hit : hits) {
                    System.out.println("score:"+hit.getScore()+":\t"+hit.getSource());// .get("title")
                }
            } else {
                System.out.println("搜到0条结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
