package com.digitalnomads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

       testLog();
    }

    public static void testLog(){
        log.info("Тестирует лог");
    }
}