package com.arino.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InfoUtil {

    private static int port;

    @Value("${server.port}")
    public void setPort(int port) {
        InfoUtil.port = port;
    }

    private static void localAddress() {
        log.info("App运行地址: http://localhost:{}/", port);
    }

    public static void log() {
        localAddress();
    }
}
