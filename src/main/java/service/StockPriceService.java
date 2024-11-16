package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockPriceService {

    @Value("${korea-investment.api.base-url}")
    private String baseUrl = "https://openapi.koreainvestment.com:9443";

    @Value("${korea-investment.api.app-key}")
    private String appKey;

    @Value("${korea-investment.api.app-secret}")
    private String appSecret;

    @Value("${korea-investment.api.access-token}")
    private String accessToken;

    private final RestTemplate restTemplate;

    public StockPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StockPriceResponse getStockPrice(String exchangeCode, String stockCode) {
        String url = baseUrl + "/uapi/overseas-price/v1/quotations/price";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer " + accessToken);
        headers.set("appKey", appKey);
        headers.set("appSecret", appSecret);
        headers.set("tr_id", "HHDFS00000300");

        // 쿼리 파라미터 설정
        Map<String, String> params = new HashMap<>();
        params.put("AUTH", accessToken);
        params.put("EXCD", exchangeCode);
        params.put("SYMB", stockCode);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<StockPriceResponse> response = restTemplate.exchange(
                url + "?AUTH={AUTH}&EXCD={EXCD}&SYMB={SYMB}",
                HttpMethod.GET,
                request,
                StockPriceResponse.class,
                params
        );
        return response.getBody();
    }
}
