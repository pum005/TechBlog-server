package com.toy.techblog.kakao.dto;

import com.toy.techblog.kakao.domain.Kakao;
import lombok.Data;

@Data
public class KakaoTop10Response {
    private String title;
    private String url;
    private String date;
    private String content;
    private String image;
    private String author;

    public KakaoTop10Response(Kakao kakao) {
        this.title = kakao.getTitle();
        this.url = kakao.getUrl();
        this.date = kakao.getDate();
        this.content = kakao.getContent();
        this.image = kakao.getImage();
        this.author = kakao.getAuthor();
    }
}
