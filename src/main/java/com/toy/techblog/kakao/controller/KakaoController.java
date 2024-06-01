package com.toy.techblog.kakao.controller;

import com.toy.techblog.kakao.domain.Kakao;
import com.toy.techblog.kakao.dto.KakaoTop10Response;
import com.toy.techblog.kakao.repository.KakaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoRepository kakaoRepository;

    @GetMapping("/api/v1/kakao/top-10")
    public List<KakaoTop10Response> getTop10Wowa(){
        Pageable pageable = PageRequest.of(0, 10); // 0부터 10개를 가져옴
        Iterable<Kakao> all = kakaoRepository.findAll(pageable);

        List<KakaoTop10Response> ret = new ArrayList<>();
        all.forEach((o) -> {
            ret.add(new KakaoTop10Response(o));
        });

        return ret;
    }
}
