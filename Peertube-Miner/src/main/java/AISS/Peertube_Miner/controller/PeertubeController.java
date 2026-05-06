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
            @RequestParam(defaultValue = "10") int maxVideos) {

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
        // Usamos getName() en lugar de getDisplayName()
        vmChannel.setName(account.getName() != null ? account.getName() : "");
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
        vmVideo.setName(video.getName() != null ? video.getName() : "");
        vmVideo.setDescription(video.getDescription() != null ? video.getDescription() : "");
        vmVideo.setReleaseTime(video.getReleaseTime() != null ? video.getReleaseTime() : "");

        // Obtener comentarios del video
        CommentResponse commentResponse = peertubeService.getComments(String.valueOf(video.getId()), maxComments);
        if (commentResponse != null && commentResponse.getData() != null && !commentResponse.getData().isEmpty()) {
            List<VMComment> vmComments = commentResponse.getData().stream()
                    .map(this::convertToVMComment)
                    .collect(Collectors.toList());
            vmVideo.setComments(vmComments);
        }

        // Obtener captions del video
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
        vmComment.setText(comment.getText() != null ? comment.getText() : "");
        // Usamos getCreatedOn() en lugar de getCreatedAt()
        vmComment.setCreatedOn(comment.getCreatedOn() != null ? comment.getCreatedOn() : "");
        return vmComment;
    }

    private VMCaption convertToVMCaption(Caption caption) {
        VMCaption vmCaption = new VMCaption();
        vmCaption.setId(caption.getId() != null ? caption.getId() : UUID.randomUUID().toString());
        // language es String directamente, no tiene getLabel()
        vmCaption.setLanguage(caption.getLanguage() != null ? caption.getLanguage() : "unknown");
        vmCaption.setLink(caption.getLink() != null ? caption.getLink() : "");
        return vmCaption;
    }
}