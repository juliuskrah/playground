package com.juliuskrah.websocket;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.runtime.Micronaut;
import io.micronaut.scheduling.annotation.Scheduled;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnError;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;

@ServerWebSocket("/notify/{topic}")
public class Application {

    private WebSocketBroadcaster broadcaster;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    public Application(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @OnOpen
    public void onOpen(String topic, WebSocketSession session) {
        String msg = "Inside topic [" + topic + "]!";
        log.info(msg);
        broadcaster.broadcastSync(msg, isValid(topic, session));
    }

    @OnMessage
    public void onMessage(String topic, String message, WebSocketSession session) {
        String msg = "Recieved message: [" + message + "] ";
        log.info(msg);
        broadcaster.broadcastSync(msg, isValid(topic, session));
    }

    @OnClose
    public void onClose(String topic, WebSocketSession session) {
        String msg = "Client disconnected from topic: [" + topic + "]!";
        log.info(msg);
        broadcaster.broadcastSync(msg, isValid(topic, session));
    }

    @OnError
    public void onError(Exception ex) {
        log.error("Error observed", ex);
    }

    private Predicate<WebSocketSession> isValid(String topic, WebSocketSession session) {
        return s -> true;
    }

    @Scheduled(fixedRate = "30s")
    void everyFiveMinutes() {
        var msg = "A new random number " + Math.random();
        broadcaster.broadcastSync(msg, s -> true);
    }
}
