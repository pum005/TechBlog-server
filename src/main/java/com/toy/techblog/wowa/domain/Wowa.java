package com.toy.techblog.wowa.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "wowa")
@Getter
public class Wowa {
    @Id
    private String id;
    private String title;
    private String url;
    private String date;
    private String content;
    private String image;
}
