package olympus.dao.ohmcalculator.model;

import java.util.ArrayList;
import java.util.List;

public class CryptoResponseBody {
    double pricePerCoin;
    List<String> listOfRewardsPerDay = new ArrayList<>();
    double estimatedReturn;

    public double getEstimatedReturn() {
        return estimatedReturn;
    }

    public void setEstimatedReturn(double estimatedReturn) {
        this.estimatedReturn = estimatedReturn;
    }

    public double getPricePerCoin() {
        return this.pricePerCoin;
    }

    public void setPricePerCoin(double pricePerCoin) {
        this.pricePerCoin = pricePerCoin;
    }
    public List<String> getListOfRewardsPerDay() {
        return listOfRewardsPerDay;
    }

    public void setListOfRewardsPerDay(List<String> listOfRewardsPerDay) {
        this.listOfRewardsPerDay = listOfRewardsPerDay;
    }
}
