package olympus.dao.ohmcalculator.model;

public class ProtocolMetrics {
    String nextEpochRebase;
    String ohmPrice;

    public String getOhmPrice() {
        return ohmPrice;
    }

    public void setOhmPrice(String ohmPrice) {
        this.ohmPrice = ohmPrice;
    }

    public ProtocolMetrics(String nextEpochRebase, String ohmPrice) {
        this.nextEpochRebase = nextEpochRebase;
        this.ohmPrice = ohmPrice;
    }

    public String getNextEpochRebase() {
        return nextEpochRebase;
    }

    public void setNextEpochRebase(String nextEpochRebase) {
        this.nextEpochRebase = nextEpochRebase;
    }
}