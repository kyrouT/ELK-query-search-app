package Project.app;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import Project.tools.*;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;

public class IndexService {

    static RestHighLevelClient client;

    public IndexService(RestHighLevelClient client) {
        IndexService.client = client;
    }


    public ArrayList<String> ViewIndexOurs(String indexName, String indexValue, int logNumber) {
        ArrayList<String> result = new ArrayList<String>(); 
        SearchRequest searchRequest = new SearchRequest(); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(logNumber);
		searchSourceBuilder.query(QueryBuilders.termQuery(indexName,indexValue));
		searchRequest.source(searchSourceBuilder);
		try {
			SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
			SearchHit[] values = searchResponse.getHits().getHits();
			if(values.length > 0) {
				for(SearchHit s : values) {
					System.out.println(s.getSourceAsString());
                    result.add(s.getSourceAsString()); ;
				}	
			} else {
				System.out.println("No results found!");
			}
		} catch (IOException e) {
            System.out.println(
                "Error"
            );
			e.printStackTrace();
		}
        return result;
    }
}

