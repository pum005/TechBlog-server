package com.toy.techblog.yanolja.controller;

import com.toy.techblog.yanolja.domain.Yanolja;
import com.toy.techblog.yanolja.dto.YanoljaTop10Response;
import com.toy.techblog.yanolja.repository.YanoljaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class YanoljaController {

    private final YanoljaRepository yanoljaRepository;

    @GetMapping("/api/v1/yanolja/top-10")
    public List<YanoljaTop10Response> getTop10Wowa(){
        Pageable pageable = PageRequest.of(0, 10); // 0부터 10개를 가져옴
        Iterable<Yanolja> all = yanoljaRepository.findAll(pageable);

        List<YanoljaTop10Response> ret = new ArrayList<>();
        all.forEach((o) -> {
            ret.add(new YanoljaTop10Response(o));
        });

        return ret;
    }
}
