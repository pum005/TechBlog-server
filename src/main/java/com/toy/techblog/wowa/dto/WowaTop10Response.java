package com.toy.techblog.wowa.dto;

import com.toy.techblog.wowa.domain.Wowa;
import lombok.Data;

@Data
public class WowaTop10Response {
    private String title;
    private String url;
    private String date;
    private String content;
    private String image;

    public WowaTop10Response(Wowa wowa) {
        this.title = wowa.getTitle();
        this.url = wowa.getUrl();
        this.date = wowa.getDate();
        this.content = wowa.getContent();
        this.image = wowa.getImage();
    }
}
