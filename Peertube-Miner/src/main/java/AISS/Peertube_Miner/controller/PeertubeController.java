package AISS.Peertube_Miner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import AISS.Peertube_Miner.exception.*;
import AISS.Peertube_Miner.service.PeertubeService;
import AISS.Peertube_Miner.model.*;
import AISS.Peertube_Miner.model.videominer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peertube")
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

    // ========== GET para pruebas ==========
    @GetMapping("/{channelName}")
    public ResponseEntity<VMChannel> getChannel(
            @PathVariable String channelName,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) {

        // 1. Obtener canal
        Channel channel = peertubeService.getChannel(channelName);
        if (channel == null) {
            throw new ChannelNotFoundException(channelName);
        }

        // 2. Obtener videos del canal
        VideoResponse videoResponse = peertubeService.getChannelVideos(channelName, maxVideos);

        // 3. Obtener el ownerAccount (user) del canal
        Account ownerAccount = null;
        if (channel.getOwnerAccount() != null && channel.getOwnerAccount().getName() != null) {
            ownerAccount = peertubeService.getAccount(channel.getOwnerAccount().getName());
        }

        // 4. Convertir a VMChannel
        VMChannel vmChannel = convertToVMChannel(channel, videoResponse, ownerAccount, maxComments);

        return ResponseEntity.ok(vmChannel);
    }

    // ========== POST que envía a VideoMiner ==========
    @PostMapping("/{channelName}")
    public ResponseEntity<VMChannel> processChannel(
            @PathVariable String channelName,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) {

        // 1. Obtener canal
        Channel channel = peertubeService.getChannel(channelName);
        if (channel == null) {
            throw new ChannelNotFoundException(channelName);
        }

        // 2. Obtener videos del canal
        VideoResponse videoResponse = peertubeService.getChannelVideos(channelName, maxVideos);

        // 3. Obtener el ownerAccount (user) del canal
        Account ownerAccount = null;
        if (channel.getOwnerAccount() != null && channel.getOwnerAccount().getName() != null) {
            ownerAccount = peertubeService.getAccount(channel.getOwnerAccount().getName());
        }

        // 4. Convertir a VMChannel
        VMChannel vmChannel = convertToVMChannel(channel, videoResponse, ownerAccount, maxComments);

        // 5. Enviar a VideoMiner
        try {
            VMChannel createdChannel = restTemplate.postForObject(videoMinerUri, vmChannel, VMChannel.class);
            return ResponseEntity.ok(createdChannel);
        } catch (RestClientException e) {
            throw new VideoMinerApiException("No se pudo enviar el canal a VideoMiner: " + e.getMessage(), e);
        }
    }

    // ========== Métodos de conversión ==========
    private VMChannel convertToVMChannel(Channel channel, VideoResponse videoResponse, Account ownerAccount, int maxComments) {

        VMChannel vmChannel = new VMChannel();
        vmChannel.setId(String.valueOf(channel.getId()));
        vmChannel.setName(channel.getName() != null ? channel.getName() : "");
        vmChannel.setDescription(channel.getDescription() != null ? channel.getDescription() : "");
        vmChannel.setCreatedTime(channel.getCreatedTime() != null ? channel.getCreatedTime() : "");

        if (videoResponse != null && videoResponse.getData() != null) {
            List<VMVideo> vmVideos = new ArrayList<>();

            for (Video video : videoResponse.getData()) {
                VMVideo vmVideo = convertToVMVideo(video, ownerAccount, maxComments);
                vmVideos.add(vmVideo);
            }

            vmChannel.setVideos(vmVideos);
        }

        return vmChannel;
    }

    private VMVideo convertToVMVideo(Video video, Account ownerAccount, int maxComments) {

        VMVideo vmVideo = new VMVideo();
        vmVideo.setId(String.valueOf(video.getId()));
        vmVideo.setName(video.getName() != null ? video.getName() : "");
        vmVideo.setDescription(video.getDescription() != null ? video.getDescription() : "");
        vmVideo.setReleaseTime(video.getReleaseTime() != null ? video.getReleaseTime() : "");

        // Añadir el user (ownerAccount) al video
        if (ownerAccount != null) {
            VMUser vmUser = new VMUser();
            vmUser.setId(String.valueOf(ownerAccount.getId()));
            vmUser.setName(ownerAccount.getDisplayName() != null ? ownerAccount.getDisplayName() : ownerAccount.getName());
            vmUser.setUser_link(ownerAccount.getUrl());

            // Extraer picture_link del primer avatar
            if (ownerAccount.getAvatars() != null && !ownerAccount.getAvatars().isEmpty()) {
                // Asumiendo que avatars es una lista de objetos con fileUrl
                // Si tienes una clase Avatar, úsala. Si no, trata con Map
                Object firstAvatar = ownerAccount.getAvatars().get(0);
                if (firstAvatar instanceof java.util.Map) {
                    String pictureUrl = (String) ((java.util.Map) firstAvatar).get("fileUrl");
                    vmUser.setPicture_link(pictureUrl);
                }
            }
            vmVideo.setUser(vmUser);
        }

        // Comentarios
        CommentResponse commentResponse = peertubeService.getComments(String.valueOf(video.getId()), maxComments);
        if (commentResponse != null && commentResponse.getData() != null && !commentResponse.getData().isEmpty()) {
            List<VMComment> vmComments = commentResponse.getData().stream()
                    .map(this::convertToVMComment)
                    .collect(Collectors.toList());
            vmVideo.setComments(vmComments);
        } else {
            vmVideo.setComments(new ArrayList<>());
        }

        // Captions
        CaptionResponse captionResponse = peertubeService.getCaptions(String.valueOf(video.getId()));
        if (captionResponse != null && captionResponse.getData() != null && !captionResponse.getData().isEmpty()) {
            List<VMCaption> vmCaptions = captionResponse.getData().stream()
                    .map(this::convertToVMCaption)
                    .collect(Collectors.toList());
            vmVideo.setCaptions(vmCaptions);
        } else {
            vmVideo.setCaptions(new ArrayList<>());
        }

        return vmVideo;
    }

    private VMComment convertToVMComment(Comment comment) {
        VMComment vmComment = new VMComment();
        vmComment.setId(String.valueOf(comment.getId()));
        vmComment.setText(comment.getText() != null ? comment.getText() : "");
        vmComment.setCreatedOn(comment.getCreatedOn() != null ? comment.getCreatedOn() : "");
        return vmComment;
    }

    private VMCaption convertToVMCaption(Caption caption) {
        VMCaption vmCaption = new VMCaption();
        vmCaption.setId(caption.getId() != null ? caption.getId() : UUID.randomUUID().toString());
        vmCaption.setLanguage(caption.getLanguage() != null ? caption.getLanguage() : "unknown");
        vmCaption.setLink(caption.getLink() != null ? caption.getLink() : "");
        return vmCaption;
    }
}
//Pruebas pendientes