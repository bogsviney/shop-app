package com.nazarov.shop.service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class PasswordEncoder {

    private static final String O_SOLE_MIO =
                    "Faster than a bullet, terrifying scream\n" +
                    "Enraged and full of anger, he's half man and half machine\n" +
                    "Rides the Metal Monster, breathing smoke and fire\n" +
                    "Closing in with vengeance soaring high";

    public String encryptPassword(String rarePassword) {
        String sha256hex = Hashing.sha256()
                .hashString(rarePassword + O_SOLE_MIO, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}