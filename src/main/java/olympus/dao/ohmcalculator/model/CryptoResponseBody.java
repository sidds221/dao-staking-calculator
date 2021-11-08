package olympus.dao.ohmcalculator.model;

import java.util.ArrayList;
import java.util.List;

public class CryptoResponseBody {
    List<String> listOfRewardsPerDay = new ArrayList<>();

    public List<String> getListOfRewardsPerDay() {
        return listOfRewardsPerDay;
    }

    public void setListOfRewardsPerDay(List<String> listOfRewardsPerDay) {
        this.listOfRewardsPerDay = listOfRewardsPerDay;
    }
}
