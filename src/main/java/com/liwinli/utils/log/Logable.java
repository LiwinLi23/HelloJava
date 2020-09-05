package com.liwinli.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Logable {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public void info(String str, Object... params) {
        log.info(str + " - " + Thread.currentThread().getName(), params);
    }

    public void info(StringBuilder sb) {
        log.info(sb.toString() + " - " + Thread.currentThread().getName());
    }

    public void warn(String str, Object... params) {
        log.warn(str + " - " + Thread.currentThread().getName(), params);
    }

    public void warn(StringBuilder sb) {
        log.warn(sb.toString() + " - " + Thread.currentThread().getName());
    }

    public void debug(String text, Object... params) {
        log.info(text + " - " + Thread.currentThread().getName(), params);
    }

    public void debug(StringBuilder sb) {
        log.debug(sb.toString() + " - " + Thread.currentThread().getName());
    }

    public void error(StringBuilder sb) {
        log.error(sb.toString() + " - " + Thread.currentThread().getName());
    }

    public void error(String text, Object... params) {
        log.error(text + " - " + Thread.currentThread().getName(), params);
    }
}
