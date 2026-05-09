package AISS.Peertube_Miner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import AISS.Peertube_Miner.exception.PeerTubeApiException;
import AISS.Peertube_Miner.model.*;

@Service
public class PeertubeService {

    private final RestTemplate restTemplate;

    @Value("${Peertube-Miner.baseuri}")
    private String baseUri;

    @Autowired
    public PeertubeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtener account (usuario) por nombre
    public Account getAccount(String accountName) {
        try {
            String url = baseUri + "/accounts/" + accountName;
            return restTemplate.getForObject(url, Account.class);
        } catch (RestClientException e) {
            throw new PeerTubeApiException("Error al obtener el account '" + accountName + "' desde PeerTube: " + e.getMessage(), e);
        }
    }

    // Obtener videos de un account
    public VideoResponse getVideos(String accountName, int maxVideos) {
        try {
            String url = baseUri + "/accounts/" + accountName + "/videos?count=" + maxVideos;
            return restTemplate.getForObject(url, VideoResponse.class);
        } catch (RestClientException e) {
            throw new PeerTubeApiException("Error al obtener los videos de '" + accountName + "': " + e.getMessage(), e);
        }
    }

    // Obtener comentarios de un video
    public CommentResponse getComments(String videoId, int maxComments) {
        try {
            String url = baseUri + "/videos/" + videoId + "/comment-threads?count=" + maxComments;
            return restTemplate.getForObject(url, CommentResponse.class);
        } catch (RestClientException e) {
            throw new PeerTubeApiException("Error al obtener comentarios del video " + videoId + ": " + e.getMessage(), e);
        }
    }

    // Obtener captions de un video
    public CaptionResponse getCaptions(String videoId) {
        try {
            String url = baseUri + "/videos/" + videoId + "/captions";
            return restTemplate.getForObject(url, CaptionResponse.class);
        } catch (RestClientException e) {
            // Los subtítulos no son críticos, devolvemos null en lugar de lanzar excepción
            return null;
        }
    }

    // ========== NUEVOS MÉTODOS PARA CHANNEL ==========

    // Obtener un canal por su ID
    public Channel getChannel(String channelId) {
        try {
            String url = baseUri + "/video-channels/" + channelId;
            return restTemplate.getForObject(url, Channel.class);
        } catch (RestClientException e) {
            throw new PeerTubeApiException("Error al obtener el channel '" + channelId + "' desde PeerTube: " + e.getMessage(), e);
        }
    }

    // Obtener videos de un canal
    public VideoResponse getChannelVideos(String channelId, int maxVideos) {
        try {
            String url = baseUri + "/video-channels/" + channelId + "/videos?count=" + maxVideos;
            return restTemplate.getForObject(url, VideoResponse.class);
        } catch (RestClientException e) {
            throw new PeerTubeApiException("Error al obtener los videos del channel '" + channelId + "': " + e.getMessage(), e);
        }
    }
}