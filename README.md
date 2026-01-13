AI Chatbot Backend (Spring Boot)

An AI-powered chatbot backend built using Spring Boot, integrated with Google Gemini API, and supporting real-time chat using WebSockets.
The application is Dockerized and deployed on Render for cloud access.

Features

- AI-powered responses using Google Gemini API

- Real-time bidirectional communication using WebSockets

- REST-based API call to Gemini using RestTemplate

- Secure API key handling using Environment Variables

- Dockerized for consistent deployment

- Deployed on Render Cloud Platform

Tech Stack

- Java 21

- Spring Boot

- Spring WebSocket

- RestTemplate

- Google Gemini API

- Docker

- Render

- Maven

Architecture Overview

1. Client establishes a WebSocket connection with the backend

2. Messages are sent to the server over WebSocket

3. Backend calls the Gemini API using RestTemplate

4. Gemini response is parsed and sent back to the client in real time

5. Gemini API Integration

6. The Gemini API is accessed using an API key

7. API calls are made via RestTemplate

8. The API key is not hardcoded in the source code

9. The API key is injected using environment variables for security


application.properties configuration:
gemini.api.key=${GEMINI_API_KEY}


WebSocket Configuration

- WebSocket is configured using WebSocketConfigurer

- Enables real-time chat communication

- Supports persistent client-server connections

WebSocket endpoint example:
/chat


Dockerization

- The project is containerized using Docker

- Ensures consistent runtime environment

- Simplifies deployment across platforms

Deployment

- The application is deployed on Render

- Environment variables such as GEMINI_API_KEY are configured via the Render dashboard

- Uses HTTPS and secure WebSocket (wss://)

How to Run Locally

Set Environment Variable : 
GEMINI_API_KEY=your_api_key_here

(Windows PowerShell)
setx GEMINI_API_KEY "your_api_key_here"
