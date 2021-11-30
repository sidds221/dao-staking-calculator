package olympus.dao.ohmcalculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Slf4j
@Validated
@Service
public class RewardRate {
    private static final String OHM_URL = "https://api.thegraph.com/subgraphs/name/drondin/olympus-graph";
    private static final String KLIMA_URL = "https://api.thegraph.com/subgraphs/name/drondin/olympus-graph";
    private static final String OHM_QUERY = "{\"query\":\"{ protocolMetrics(first: 1, orderBy: timestamp, orderDirection: desc) { ohmPrice\\n nextEpochRebase\\n runwayCurrent\\n }\\n}\\n\"}";
    private static final String KLIMA_QUERY = "{\"query\":\"{ protocolMetrics(first: 1, orderBy: timestamp, orderDirection: desc) { ohmPrice\\n nextEpochRebase\\n }\\n}\\n\"}";

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    public static double pricePerCoin_ohm = 0;
    public static int currentRunway = 0;

    public Double getOhmRewardRate() {
        headers.add("content-type", "application/json"); // just modified graphql into json

        ResponseEntity<Object> response = restTemplate.postForEntity(OHM_URL, new HttpEntity<>(OHM_QUERY, headers), Object.class);
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
        LinkedHashMap<String, Object> protocolMetrics = (LinkedHashMap<String, Object>) map.get("data");

        ArrayList nextRewardPercentage = (ArrayList) protocolMetrics.get("protocolMetrics");
        LinkedHashMap dataMap = (LinkedHashMap) nextRewardPercentage.get(0);

        this.pricePerCoin_ohm = Double.parseDouble((String) dataMap.get("ohmPrice"));
        this.currentRunway = (int)(Double.parseDouble((String) dataMap.get("runwayCurrent")));
        return Double.parseDouble((String) dataMap.get("nextEpochRebase"));
    }

    public Double getKlimaRewardRate() {
        return 0.56;
    }

    public Double getTimeRewardRate() {
        return 0.6189;
    }
}
