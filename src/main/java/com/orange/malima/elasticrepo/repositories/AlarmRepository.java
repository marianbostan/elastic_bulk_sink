package com.orange.malima.elasticrepo.repositories;

import com.google.gson.Gson;
import com.orange.malima.elasticrepo.entities.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface AlarmRepository  extends ElasticsearchRepository<Alarm, String> {}

