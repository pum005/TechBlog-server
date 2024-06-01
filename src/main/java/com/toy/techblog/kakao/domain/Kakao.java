package com.toy.techblog.kakao.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "kakao")
@Getter
public class Kakao {
    @Id
    private String id;
    private String title;
    private String url;
    private String date;
    private String content;
    private String image;
    private String author;
}
