package olympus.dao.ohmcalculator.model;

import lombok.Data;

@Data
public class CryptoRequestBody {
    double currentNumberOfCoins;
    int timeInYears;
    double percentageIncrease;

    public CryptoRequestBody(double numberOfCoins, int timeInYears, double percentageIncrease) {
        this.currentNumberOfCoins = numberOfCoins;
        this.timeInYears = timeInYears;
        this.percentageIncrease = percentageIncrease;
    }


    public double getCurrentNumberOfCoins() {
        return currentNumberOfCoins;
    }

    public void setCurrentNumberOfCoins(double currentNumberOfCoins) {
        this.currentNumberOfCoins = currentNumberOfCoins;
    }

    public int getTimeInYears() {
        return timeInYears;
    }

    public void setTimeInYears(int timeInYears) {
        this.timeInYears = timeInYears;
    }

    public double getPercentageIncrease() {
        return percentageIncrease;
    }

    public void setPercentageIncrease(double percentageIncrease) {
        this.percentageIncrease = percentageIncrease;
    }
}
