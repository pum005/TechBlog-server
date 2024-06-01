package com.toy.techblog.yanolja.dto;

import com.toy.techblog.yanolja.domain.Yanolja;
import lombok.Data;

@Data
public class YanoljaTop10Response {
    private String title;
    private String url;
    private String date;
    private String content;
    private String image;
    private String author;
    private String profile;

    public YanoljaTop10Response(Yanolja yanolja) {
        this.title = yanolja.getTitle();
        this.url = yanolja.getUrl();
        this.date = yanolja.getDate();
        this.content = yanolja.getContent();
        this.image = yanolja.getImage();
        this.author = yanolja.getAuthor();
        this.profile = yanolja.getProfile();
    }
}
