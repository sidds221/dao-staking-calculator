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
    RewardRate rewardRate = new RewardRate();

    @PostMapping("/ohm-rewards")
    public CryptoResponseBody ohmCalculator(@RequestBody CryptoRequestBody cryptoRequestBody) {
        double numberOfCoins = cryptoRequestBody.getCurrentNumberOfCoins();
        double percentageIncrease = rewardRate.getOhmRewardRate();
        double pricePerCoin = rewardRate.pricePerCoin_ohm;
        int runwayCurrent = rewardRate.currentRunway;

        return calculateRewards(numberOfCoins, percentageIncrease, runwayCurrent, pricePerCoin);
    }

    @GetMapping("/ohm-rewards/{numberOfCoins}")
    public CryptoResponseBody getOhmCalculator(
            @PathVariable double numberOfCoins
    ) {
        return calculateRewards(numberOfCoins, rewardRate.getOhmRewardRate(), rewardRate.currentRunway, rewardRate.pricePerCoin_ohm);
    }

    @GetMapping("/klima-rewards/{numberOfCoins}")
    public CryptoResponseBody getKlimaCalculator(
            @PathVariable double numberOfCoins
    ) {
        return calculateRewards(numberOfCoins, rewardRate.getKlimaRewardRate(), 365, 1500);
    }

    @GetMapping("/time-rewards/{numberOfCoins}")
    public CryptoResponseBody getTimeCalculator(
            @PathVariable double numberOfCoins
    ) {
        return calculateRewards(numberOfCoins, rewardRate.getTimeRewardRate(), 365,7800);
    }

    private CryptoResponseBody calculateRewards(double numberOfCoins,  double percentageIncrease, int runwayCurrent, double pricePerCoin) {
        List<String> listOfRewards = new ArrayList<>();

        for(int i = 1; i <= runwayCurrent * 3; i ++) {
            if(i % 3 == 0 && i != 3) {
                listOfRewards.add("day"+i/3 +"   "+numberOfCoins);
            }
            double nextReward = (numberOfCoins * percentageIncrease) / 100;
            numberOfCoins += nextReward;
        }

        CryptoResponseBody cryptoResponseBody = new CryptoResponseBody();

        cryptoResponseBody.setListOfRewardsPerDay(listOfRewards);
        cryptoResponseBody.setPricePerCoin(pricePerCoin);
        cryptoResponseBody.setEstimatedReturn(pricePerCoin * numberOfCoins);
        return cryptoResponseBody;
    }
}
