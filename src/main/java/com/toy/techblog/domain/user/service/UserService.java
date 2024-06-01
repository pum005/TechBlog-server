package com.toy.techblog.domain.user.service;

import com.toy.techblog.domain.user.dto.UserLoginResponseDTO;
import com.toy.techblog.domain.user.entity.User;
import com.toy.techblog.domain.user.repository.UserRepository;
import com.toy.techblog.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;
    //    @Value("${kakao.logout-redirect-uri}")
    private String kakaoLogoutRedirectUri = "http://localhost:8080/api/oauth/logout";
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${spring.security.oauth2.client.registration.naver.clientId}")
    private String naverClientId;
    @Value("${spring.security.oauth2.client.registration.naver.clientSecret}")
    private String naverSecretId;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;
//    private final GetUser getUser;
    @Autowired
    private RestTemplate restTemplate;

    // 소셜 로그인
    @Transactional
    public UserLoginResponseDTO oauthLogin(String privateAccess, HttpServletResponse response) {

        Optional<User> byPrivateAccess = userRepository.findByPrivateAccess(privateAccess);

        if (byPrivateAccess.isEmpty()) {
            throw new NullPointerException();
        }

        User user = byPrivateAccess.get();

        // 로그인 성공
        String accessToken = jwtUtil.createAccessJwt(user.getUserId(), secretKey); // 토큰 발급해서 넘김
        String refreshToken = jwtUtil.createRefreshToken(user.getUserId(), secretKey); // 리프레시 토큰 발급해서 넘김

        // create a cookie
        Cookie cookie = new Cookie("refreshToken", refreshToken);

        // expires in 7 days
        cookie.setMaxAge(14 * 24 * 60 * 60);

        // optional properties
        cookie.setSecure(false); // 이거 https 적용해서 서버로 올리면 true로 바꿔야한다. 지금은 로컬에서 테스트라서 false로 해놓음
        cookie.setHttpOnly(true); // http only로 설정해서 javascript로 접근 못하도록 막음
        cookie.setPath("/");

        // add cookie to response
        response.addCookie(cookie);


        Map<String, Object> result = new HashMap<>();

        UserLoginResponseDTO build = UserLoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .accountType(user.getAccountType())
                .uesrId(user.getUserId())
                .createCount(user.getCreateCount()).build();

        if (user.getAccountType().equals("kakao")) {
            result.put("message", "카카오 로그인 성공");
        } else if (user.getAccountType().equals("naver")) {
            result.put("message", "네이버 로그인 성공");
        }

        return build;
    }

//    // 소셜 로그아웃
//    public Map<String, Object> socialLogout(HttpServletRequest request, HttpServletResponse response) {
//        User user = getUser.getUser();
//        Long userId = user.getUserId();
//
//        HttpSession session = request.getSession();
//        session.setAttribute("userId", userId);
//
//        Map<String, Object> result = new HashMap<>();
//
//        if (user.getAccountType().equals("kakao")) {
//
//            String logoutUrl = "https://kauth.kakao.com/oauth/logout";
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(logoutUrl)
//                    .queryParam("client_id", kakaoClientId)
//                    .queryParam("logout_redirect_uri", kakaoLogoutRedirectUri);
//
//            result.put("logoutUrl", builder.toUriString());
//        } else if (user.getAccountType().equals("naver")) {
//
//            String logoutUrl = "https://nid.naver.com/oauth2.0/token";
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(logoutUrl)
//                    .queryParam("client_id", naverClientId)
//                    .queryParam("service_provider", "NAVER")
//                    .queryParam("client_secret", naverSecretId)
//                    .queryParam("access_token", user.getAccessToken())
//                    .queryParam("grant_type", "delete");
//
//            ResponseEntity<String> res = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null, String.class);
//
//            clearCookies(request, response);
//            result.put("naver", "성공");
//
//            redisTemplate.delete(String.valueOf(userId));
//        }
//        return result;
//    }
//
//    // 로그아웃
//    @Transactional
//    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
//
//        // 세션에서 유저 ID 꺼내기
//        HttpSession session = request.getSession();
//        Long userId = (Long) session.getAttribute("userId");
//
//        redisTemplate.delete(String.valueOf(userId));
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "로그아웃 성공");
//
//        clearCookies(request, response);
//
//        return result;
//    }

    // 쿠키 날리기
    private void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // 쿠키의 유효 기간을 0으로 설정하여 즉시 만료시킵니다.
                cookie.setValue(null);
                cookie.setPath("/");
                response.addCookie(cookie); // 무효화된 쿠키를 응답에 추가합니다.
            }
        }


    }
}