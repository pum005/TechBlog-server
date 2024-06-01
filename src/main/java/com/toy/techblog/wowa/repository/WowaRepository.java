package com.toy.techblog.wowa.repository;

import com.toy.techblog.wowa.domain.Wowa;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WowaRepository  extends ElasticsearchRepository<Wowa, String>{
}
