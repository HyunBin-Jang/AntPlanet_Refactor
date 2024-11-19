package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SP500CrawlerService {

    public static List<String> getSP500Symbols() throws Exception {
        String url = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
        Document doc = Jsoup.connect(url).get();

        // S&P 500 테이블에서 Symbol 열 가져오기
        Element table = doc.select("table.wikitable").first();
        Elements rows = Objects.requireNonNull(table).select("tbody tr");

        List<String> symbols = new ArrayList<>();
        for (Element row : rows) {
            Elements columns = row.select("td");
            if (!columns.isEmpty()) {
                String symbol = columns.get(0).text();
                symbols.add(symbol);
            }
        }
        return symbols;
    }

    public static void main(String[] args) {
        try {
            List<String> sp500Symbols = getSP500Symbols();
            System.out.println("S&P 500 Symbols: " + sp500Symbols);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}