package olympus.dao.ohmcalculator.service;

import olympus.dao.ohmcalculator.model.Data;
import olympus.dao.ohmcalculator.model.QueryForOlympus;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RewardRate {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.thegraph.com/subgraphs/name/drondin/olympus-graph";
    QueryForOlympus queryForOlympus = new QueryForOlympus();

    public String getRewardPercentage() {
        queryForOlympus.setRequestUrl("{\\n  protocolMetrics(first: 1, orderBy: timestamp, orderDirection: desc) {nextEpochRebase\\n  }\\n}\\n");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<QueryForOlympus> requestEntity = new HttpEntity<>(queryForOlympus, requestHeaders);

        Data dataResponseEntity = restTemplate.postForObject(url, HttpMethod.POST, Data.class);

        System.out.println(dataResponseEntity.getProtocolMetrics().getNextEpochRebase());

        return dataResponseEntity.getProtocolMetrics().getNextEpochRebase();
    }
}
