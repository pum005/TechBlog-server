package com.toy.techblog.kakao.repository;

import com.toy.techblog.kakao.domain.Kakao;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KakaoRepository extends ElasticsearchRepository<Kakao, String>{
}
