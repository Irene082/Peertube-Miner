package AISS.Peertube_Miner.exception;

public class VideoNotFoundException extends RuntimeException {
    private String videoId;
    public VideoNotFoundException(String videoId) {
        super("El video con id '" + videoId + "' no existe en PeerTube");
        this.videoId = videoId;
    }
    public String getVideoId() {
        return videoId;
    }
}