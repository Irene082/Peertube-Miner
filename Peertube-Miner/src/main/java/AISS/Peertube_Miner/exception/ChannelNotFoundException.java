package AISS.Peertube_Miner.exception;

public class ChannelNotFoundException extends RuntimeException {
    private String channelId;

    public ChannelNotFoundException(String channelId) {
        super("El channel '" + channelId + "' no existe en PeerTube");
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }
}