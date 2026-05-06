package AISS.Peertube_Miner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import AISS.Peertube_Miner.service.PeertubeService;
import AISS.Peertube_Miner.model.*;
import AISS.Peertube_Miner.model.videominer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/peertube")
public class PeertubeController {

    private final PeertubeService peertubeService;
    private final RestTemplate restTemplate;

    @Value("${videominer.uri}")
    private String videoMinerUri;

    @Autowired
    public PeertubeController(PeertubeService peertubeService, RestTemplate restTemplate) {
        this.peertubeService = peertubeService;
        this.restTemplate = restTemplate;
    }

    // ========== GET para pruebas (NO envía a VideoMiner) ==========
    @GetMapping("/{id}")
    public ResponseEntity<VideoResponse> getChannelVideos(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) {

        VideoResponse videos = peertubeService.getVideos(id, maxVideos);
        return ResponseEntity.ok(videos);
    }

    // ========== POST que envía a VideoMiner ==========
    @PostMapping("/{id}")
    public ResponseEntity<VMChannel> processChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) {

        // 1. Obtener datos de PeerTube
        Account account = peertubeService.getAccount(id);
        VideoResponse videoResponse = peertubeService.getVideos(id, maxVideos);

        // 2. Convertir a VMChannel
        VMChannel vmChannel = convertToVMChannel(account, videoResponse, maxComments);

        // 3. Enviar a VideoMiner
        VMChannel createdChannel = restTemplate.postForObject(videoMinerUri, vmChannel, VMChannel.class);

        return ResponseEntity.ok(createdChannel);
    }

    // ========== Métodos de conversión ==========
    private VMChannel convertToVMChannel(Account account, VideoResponse videoResponse, int maxComments) {

        VMChannel vmChannel = new VMChannel();
        vmChannel.setId(String.valueOf(account.getId()));
        vmChannel.setName(account.getDisplayName());
        // Account NO tiene description ni createdAt, usamos valores por defecto
        vmChannel.setDescription("");
        vmChannel.setCreatedTime("");

        if (videoResponse != null && videoResponse.getData() != null) {
            List<VMVideo> vmVideos = new ArrayList<>();

            for (Video video : videoResponse.getData()) {
                VMVideo vmVideo = convertToVMVideo(video, maxComments);
                vmVideos.add(vmVideo);
            }

            vmChannel.setVideos(vmVideos);
        }

        return vmChannel;
    }

    private VMVideo convertToVMVideo(Video video, int maxComments) {

        VMVideo vmVideo = new VMVideo();
        vmVideo.setId(String.valueOf(video.getId()));
        vmVideo.setName(video.getName());
        vmVideo.setDescription(video.getDescription() != null ? video.getDescription() : "");
        vmVideo.setReleaseTime(video.getPublishedAt() != null ? video.getPublishedAt() : "");

        // Obtener comentarios del video (si tiene)
        if (video.getComments() != null && video.getComments() > 0) {
            CommentResponse commentResponse = peertubeService.getComments(String.valueOf(video.getId()), maxComments);
            if (commentResponse != null && commentResponse.getData() != null) {
                List<VMComment> vmComments = commentResponse.getData().stream()
                        .map(this::convertToVMComment)
                        .collect(Collectors.toList());
                vmVideo.setComments(vmComments);
            }
        }

        // Obtener captions del video (si tiene)
        CaptionResponse captionResponse = peertubeService.getCaptions(String.valueOf(video.getId()));
        if (captionResponse != null && captionResponse.getData() != null && !captionResponse.getData().isEmpty()) {
            List<VMCaption> vmCaptions = captionResponse.getData().stream()
                    .map(this::convertToVMCaption)
                    .collect(Collectors.toList());
            vmVideo.setCaptions(vmCaptions);
        }

        return vmVideo;
    }

    private VMComment convertToVMComment(Comment comment) {
        VMComment vmComment = new VMComment();
        vmComment.setId(String.valueOf(comment.getId()));
        vmComment.setText(comment.getText());
        vmComment.setCreatedOn(comment.getCreatedAt());
        return vmComment;
    }

    private VMCaption convertToVMCaption(Caption caption) {
        VMCaption vmCaption = new VMCaption();
        // Caption no tiene ID propio, generamos uno
        vmCaption.setId(UUID.randomUUID().toString());
        // Obtener el nombre del idioma
        if (caption.getLanguage() != null && caption.getLanguage().getLabel() != null) {
            vmCaption.setLanguage(caption.getLanguage().getLabel());
        } else {
            vmCaption.setLanguage("unknown");
        }
        vmCaption.setLink(caption.getFileUrl() != null ? caption.getFileUrl() : "");
        return vmCaption;
    }
}