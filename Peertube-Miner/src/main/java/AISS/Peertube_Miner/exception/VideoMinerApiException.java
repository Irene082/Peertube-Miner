package AISS.Peertube_Miner.exception;

public class VideoMinerApiException extends RuntimeException {
    public VideoMinerApiException(String message) {
        super(message);
    }
    public VideoMinerApiException(String message, Throwable cause) {
        super(message, cause);
    }
}