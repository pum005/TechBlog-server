package com.toy.techblog.wowa.controller;

import com.toy.techblog.wowa.domain.Wowa;
import com.toy.techblog.wowa.dto.WowaTop10Response;
import com.toy.techblog.wowa.repository.WowaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WowaController {

    private final WowaRepository wowaRepository;

    @GetMapping("/api/v1/wowa/top-10")
    public List<WowaTop10Response> getTop10Wowa(){
        Pageable pageable = PageRequest.of(0, 10); // 0부터 10개를 가져옴
        Iterable<Wowa> all = wowaRepository.findAll(pageable);

        List<WowaTop10Response> ret = new ArrayList<>();
        all.forEach((o) -> {
            ret.add(new WowaTop10Response(o));
        });

        return ret;
    }
}
