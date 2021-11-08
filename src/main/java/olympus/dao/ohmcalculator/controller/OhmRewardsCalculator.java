package olympus.dao.ohmcalculator.controller;

import olympus.dao.ohmcalculator.model.CryptoRequestBody;
import olympus.dao.ohmcalculator.model.CryptoResponseBody;
import olympus.dao.ohmcalculator.service.RewardRate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/staking")
public class OhmRewardsCalculator {
    RewardRate rewardRate;

    public OhmRewardsCalculator(RewardRate rewardRate){
        this.rewardRate = rewardRate;
    }

    @PostMapping("/ohm-rewards")
    public CryptoResponseBody ohmCalculator(@RequestBody CryptoRequestBody cryptoRequestBody) {
        double numberOfCoins = cryptoRequestBody.getCurrentNumberOfCoins();
        int frequencyPerDay = cryptoRequestBody.getFrequencyOfRewardsPerDay();
        int timeInYears = cryptoRequestBody.getTimeInYears();
        double percentageIncrease = cryptoRequestBody.getPercentageIncrease();

        //System.out.println(this.rewardRate.getRewardPercentage());

        return calculatingOhms(numberOfCoins, frequencyPerDay, timeInYears, percentageIncrease);
    }

    @GetMapping("/ohm-rewards/{numberOfCoins}/{frequencyPerDay}/{timeInYears}/{percentageIncrease}")
    public CryptoResponseBody getOhmCalculator(
            @PathVariable double numberOfCoins,
            @PathVariable int frequencyPerDay,
            @PathVariable int timeInYears,
            @PathVariable double percentageIncrease
    ) {

        return calculatingOhms(numberOfCoins, frequencyPerDay, timeInYears, percentageIncrease);
    }

    private CryptoResponseBody calculatingOhms(double numberOfCoins, int frequencyPerDay, int timeInYears, double percentageIncrease) {
        List<String> listOfRewards = new ArrayList<>();

        for(int i = 1; i <= 365 * frequencyPerDay * timeInYears; i ++) {
            if(i % 3 == 0 && i != 3) {
                listOfRewards.add("day"+i/3 +"   "+numberOfCoins);
            }
            double nextReward = (numberOfCoins * percentageIncrease) / 100;
            numberOfCoins += nextReward;
        }

        CryptoResponseBody cryptoResponseBody = new CryptoResponseBody();
        cryptoResponseBody.setListOfRewardsPerDay(listOfRewards);
        return cryptoResponseBody;
    }
}
