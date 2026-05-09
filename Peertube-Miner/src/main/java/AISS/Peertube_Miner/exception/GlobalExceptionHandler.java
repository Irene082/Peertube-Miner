package AISS.Peertube_Miner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpClientError(HttpClientErrorException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", ex.getStatusCode().value());
        response.put("error", "Error en la petición a PeerTube");
        response.put("message", ex.getMessage());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpServerError(HttpServerErrorException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", ex.getStatusCode().value());
        response.put("error", "PeerTube está teniendo problemas");
        response.put("message", "El servidor de PeerTube respondió con error: " + ex.getMessage());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Map<String, Object>> handleResourceAccess(ResourceAccessException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 503);
        response.put("error", "Servicio no disponible");
        if (ex.getMessage().contains("PeerTube") || ex.getMessage().contains("peertube")) {
            response.put("message", "No se pudo conectar con PeerTube. ¿La instancia está disponible?");
        } else {
            response.put("message", "No se pudo conectar con VideoMiner. ¿El servicio está corriendo?");
        }
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Map<String, Object>> handleRestClient(RestClientException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 500);
        response.put("error", "Error al comunicarse con API externa");
        response.put("message", ex.getMessage());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PeerTubeApiException.class)
    public ResponseEntity<Map<String, Object>> handlePeerTubeApi(PeerTubeApiException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 502);
        response.put("error", "Error al obtener datos de PeerTube");
        response.put("message", ex.getMessage());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(VideoMinerApiException.class)
    public ResponseEntity<Map<String, Object>> handleVideoMinerApi(VideoMinerApiException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 502);
        response.put("error", "Error al enviar datos a VideoMiner");
        response.put("message", ex.getMessage());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAccountNotFound(AccountNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 404);
        response.put("error", "Account no encontrado");
        response.put("message", ex.getMessage());
        response.put("accountName", ex.getAccountName());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VideoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleVideoNotFound(VideoNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 404);
        response.put("error", "Video no encontrado");
        response.put("message", ex.getMessage());
        response.put("videoId", ex.getVideoId());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 500);
        response.put("error", "Error interno del servidor");
        response.put("message", ex.getMessage());
        response.put("path", "/api/peertube");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleChannelNotFound(ChannelNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 404);
        response.put("error", "Channel no encontrado");
        response.put("message", ex.getMessage());
        response.put("channelId", ex.getChannelId());
        response.put("path", "/peertube");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}