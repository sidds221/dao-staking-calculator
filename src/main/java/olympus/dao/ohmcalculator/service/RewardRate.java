package olympus.dao.ohmcalculator.service;

import com.google.common.collect.ImmutableMap;
import graphql.ExecutionResult;
import graphql.servlet.GraphQLInvocationInputFactory;
import graphql.servlet.GraphQLQueryInvoker;
import graphql.servlet.GraphQLSingleInvocationInput;
import graphql.servlet.internal.GraphQLRequest;
import olympus.dao.ohmcalculator.model.Data;
import olympus.dao.ohmcalculator.model.QueryForOlympus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RewardRate {
    private GraphQLInvocationInputFactory invocationInputFactory;

    private GraphQLQueryInvoker queryInvoker;

    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.thegraph.com/subgraphs/name/drondin/olympus-graph";

    private static final String QUERY = "{\"variables\":{},\"query\":\"{\\n  _meta {\\n    block {\\n      number\\n      __typename\\n    }\\n    __typename\\n  }\\n  protocolMetrics(first: 1, orderBy: timestamp, orderDirection: desc) {\\n    timestamp\\n    ohmCirculatingSupply\\n    sOhmCirculatingSupply\\n    totalSupply\\n    ohmPrice\\n    marketCap\\n    totalValueLocked\\n    treasuryMarketValue\\n    nextEpochRebase\\n    nextDistributedOhm\\n    __typename\\n  }\\n}\\n\"}";

    QueryForOlympus queryForOlympus = new QueryForOlympus();

    public Optional<String> getData() {

        Map<String, Object> variables = new HashMap<>();

        GraphQLRequest request = new GraphQLRequest(QUERY, variables, null);
        GraphQLSingleInvocationInput invocationInput = invocationInputFactory.create(request);
        ExecutionResult result = queryInvoker.query(invocationInput);

        /*
         * Of course result.getData() can be null here - see also result.isDataPresent()
         * - but data/error handling's left to you
         */
        Optional<String> company = Optional.ofNullable(result.getData());
        return company;
    }

//    public String getRewardPercentage() {
//        queryForOlympus.setRequestUrl("{\\n  protocolMetrics(first: 1, orderBy: timestamp, orderDirection: desc) {nextEpochRebase\\n  }\\n}\\n");
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<QueryForOlympus> requestEntity = new HttpEntity<>(queryForOlympus, requestHeaders);
//
//        Data dataResponseEntity = restTemplate.postForObject(url, HttpMethod.POST, Data.class);
//
//        System.out.println(dataResponseEntity.getProtocolMetrics().getNextEpochRebase());
//
//        return dataResponseEntity.getProtocolMetrics().getNextEpochRebase();
//    }
}
