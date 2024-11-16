package service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPriceResponse {

    @JsonProperty("stck_prpr")
    private String currentPrice; // 현재가

    @JsonProperty("stck_hgpr")
    private String highPrice; // 고가

    @JsonProperty("stck_lwpr")
    private String lowPrice; // 저가

    @JsonProperty("stck_oprc")
    private String openPrice; // 시가

    @JsonProperty("hts_kor_isnm")
    private String stockName; // 주식 이름

    @JsonProperty("mktc")
    private String marketCode; // 시장 코드
}
