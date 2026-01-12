package com.ai.Chatbot.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;

@Component
public class GeminiWebSocketHandler extends TextWebSocketHandler {
    @Value("${gemini.api.key}")
    private String apiKey;

    private final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-pro:generateContent?key=";


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userInput = message.getPayload().trim().toLowerCase();
        System.out.println("Original User Input: " + userInput);
        String response = callGeminiForAnswer(userInput);
        session.sendMessage(new TextMessage(response));
    }

    private String callGeminiForAnswer(String userInput){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String jsonPayload = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + userInput + "\" }]}]}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);
            String url = GEMINI_URL + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            System.out.println("Sent JSON to Gemini "+ jsonPayload);
            System.out.println("Raw response from Gemini "+response.getBody());
            return extractText(response.getBody());
        }
        catch(Exception e){
            System.out.println("API call failed" + e.getMessage());
            return "{\"error\":\"API call failed\"}";
        }
    }

    private String extractText(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray candidates = obj.optJSONArray("candidates");
            if (candidates != null && candidates.length() > 0) {
                JSONObject firstCandidate = candidates.getJSONObject(0);
                JSONObject content = firstCandidate.getJSONObject("content");
                JSONArray parts = content.getJSONArray("parts");
                if (parts.length() > 0) {
                    return parts.getJSONObject(0).optString("text", "(No text found)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "(Could not parse response)";
    }
}
