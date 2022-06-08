package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/greet")
public class GreetingController {

    private static final Map<String, String> GREETINGS_BY_LANGUAGE = new HashMap<>();

    static {
        GREETINGS_BY_LANGUAGE.put("english", "Hello!");
    }

    @GetMapping("/{lang}")
    public ResponseEntity<String> greetInEnglish(@PathVariable String lang) {
        String lowerCaseLang = lang.toLowerCase();
        log.info("Greeting request for lang: {}. Search term: {}", lang, lowerCaseLang);

        if (isNotAKnownLanguage(lowerCaseLang)) {
            return ResponseEntity.status(404)
                    .build();
        }

        return ResponseEntity.ok(GREETINGS_BY_LANGUAGE.get(lowerCaseLang));
    }

    private boolean isNotAKnownLanguage(String lang) {
        if (lang == null) {
            return true;
        }

        return !GREETINGS_BY_LANGUAGE.containsKey(lang);
    }
}
