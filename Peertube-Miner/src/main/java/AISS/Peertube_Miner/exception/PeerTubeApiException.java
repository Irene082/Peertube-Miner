package AISS.Peertube_Miner.exception;

public class PeerTubeApiException extends RuntimeException {
    public PeerTubeApiException(String message) {
        super(message);
    }
    public PeerTubeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}