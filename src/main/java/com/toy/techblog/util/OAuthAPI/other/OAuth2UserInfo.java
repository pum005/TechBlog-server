package com.toy.techblog.util.OAuthAPI.other;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

    String getImagePath();
}