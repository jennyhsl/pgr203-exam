package no.kristiania.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpClientTest {

    @Test
    void shouldDoSomething() {
        assertEquals(200, 100+100);
    }


    @Test
    void shouldReturnStatusCode() throws IOException {
        Assertions.assertEquals(200, new HttpClient("httpbin.org", 80, "/html").getStatusCode());
        assertEquals(404, new HttpClient("httpbin.org", 80, "/no-such-page").getStatusCode());
    }


    @Test
    void shouldReturnHeaders() throws IOException {
        HttpClient client = new HttpClient("httpbin.org", 80, "/html");
        assertEquals("text/html; charset=utf-8",client.getHeader("Content-Type"));
    }


    @Test
    void shouldReadContentLength() throws IOException {
        HttpClient client = new HttpClient("httpbin.org", 80, "/html");
        assertTrue(client.getMessageBody().startsWith("<!DOCTYPE html>"),
                "Expected HTML: " + client.getMessageBody());
    }
}
