package com.orange.malima.elasticrepo;

import com.google.gson.Gson;
import com.orange.malima.elasticrepo.configuration.ElasticSearchConfiguration;
import com.orange.malima.elasticrepo.entities.Alarm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchConfiguration.class)
public class ElasticrepoApplicationTests {
	 @Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	ElasticsearchOperations operations;

	public static final String ALARM_INDEX_NAME = "alarms";
	public static final String ALARM_INDEX_TYPE = "alarm";

	@Test
	public void contextLoads() {

		int counter = 0;
		try {
			if (!elasticsearchTemplate.indexExists(ALARM_INDEX_NAME)) {
				elasticsearchTemplate.createIndex(ALARM_INDEX_NAME);
			}
			Gson gson = new Gson();
			List<IndexQuery> queries = new ArrayList<IndexQuery>();
			List<Alarm> cars = createTestData();
			for (Alarm car : cars) {
				IndexQuery indexQuery = new IndexQuery();
				indexQuery.setId(car.getId().toString());
				indexQuery.setSource(gson.toJson(car));
				indexQuery.setIndexName(ALARM_INDEX_NAME);
				indexQuery.setType(ALARM_INDEX_TYPE);
				queries.add(indexQuery);
				counter++;
			}

			System.out.println("bulkIndex counter : " + counter);

			if (queries.size() > 0) {
				elasticsearchTemplate.bulkIndex(queries);
			}
			elasticsearchTemplate.refresh(ALARM_INDEX_NAME);
			System.out.println("bulkIndex completed.");

			elasticsearchTemplate.count(new NativeSearchQuery())

			System.out.println( operations.count(new CriteriaQuery(new Criteria())));

//			List<Alarm>  alarms = operations.queryForList(new CriteriaQuery(new Criteria()), Alarm.class);
//
//			alarms.stream().forEach(System.out::println);

		} catch (Exception e) {
			System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
			throw e;
		}


	}
	private List<Alarm> createTestData() {
		List<Alarm> alarms = new ArrayList<Alarm>();
		alarms.add( new Alarm("1", "comment1" ,"NEW", 1));
		alarms.add( new Alarm("2", "comment2" ,"NEW", 2));

		return alarms;
	}
}

