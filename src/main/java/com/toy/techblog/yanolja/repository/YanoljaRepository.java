package com.toy.techblog.yanolja.repository;

import com.toy.techblog.yanolja.domain.Yanolja;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface YanoljaRepository extends ElasticsearchRepository<Yanolja, String>{
}
