package com.vacflix.backend.vacflixbackend.service;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.*;
import java.nio.charset.Charset;

public class ApiAuth {

    //TODO: make env better?
    private final Environment env;

    //originals
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    //TODO: check if this works
//    private static final HttpTransport HTTP_TRANSPORT = new HttpTransport() {
//        @Override
//        protected LowLevelHttpRequest buildRequest(String s, String s1) throws IOException {
//            return null;
//        }
//    };
//    private static final JsonFactory JSON_FACTORY = new JsonFactory() {
//        @Override
//        public JsonParser createJsonParser(InputStream inputStream) throws IOException {
//            return null;
//        }
//
//        @Override
//        public JsonParser createJsonParser(InputStream inputStream, Charset charset) throws IOException {
//            return null;
//        }
//
//        @Override
//        public JsonParser createJsonParser(String s) throws IOException {
//            return null;
//        }
//
//        @Override
//        public JsonParser createJsonParser(Reader reader) throws IOException {
//            return null;
//        }
//
//        @Override
//        public JsonGenerator createJsonGenerator(OutputStream outputStream, Charset charset) throws IOException {
//            return null;
//        }
//
//        @Override
//        public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
//            return null;
//        }
//    };


    public ApiAuth(Environment env) {
        this.env = env;
    }

    public YouTube getYoutube(){
            return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("YouTubeVideoInfo")
                    .setYouTubeRequestInitializer(new YouTubeRequestInitializer(this.env.getProperty("youtube.apikey"))).build();
        }

}
