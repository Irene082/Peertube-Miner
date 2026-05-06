package AISS.Peertube_Miner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
        String url = baseUri + "/accounts/" + accountName;
        return restTemplate.getForObject(url, Account.class);
    }

    // Obtener videos de un account
    public VideoResponse getVideos(String accountName, int maxVideos) {
        String url = baseUri + "/accounts/" + accountName + "/videos?count=" + maxVideos;
        return restTemplate.getForObject(url, VideoResponse.class);
    }

    // Obtener comentarios de un video
    public CommentResponse getComments(String videoId, int maxComments) {
        String url = baseUri + "/videos/" + videoId + "/comment-threads?count=" + maxComments;
        return restTemplate.getForObject(url, CommentResponse.class);
    }

    // Obtener captions de un video
    public CaptionResponse getCaptions(String videoId) {
        String url = baseUri + "/videos/" + videoId + "/captions";
        return restTemplate.getForObject(url, CaptionResponse.class);
    }
}