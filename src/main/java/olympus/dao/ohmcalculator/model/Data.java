package olympus.dao.ohmcalculator.model;

public class Data {
    public Data(ProtocolMetrics protocolMetrics) {
        this.protocolMetrics = protocolMetrics;
    }

    ProtocolMetrics protocolMetrics;

    public ProtocolMetrics getProtocolMetrics() {
        return protocolMetrics;
    }

    public void setProtocolMetrics(ProtocolMetrics protocolMetrics) {
        this.protocolMetrics = protocolMetrics;
    }
}
