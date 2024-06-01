package com.toy.techblog.yanolja.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "yanolja")
@Getter
public class Yanolja {
    @Id
    private String id;
    private String title;
    private String url;
    private String date;
    private String content;
    private String image;
    private String author;
    private String profile;
}
