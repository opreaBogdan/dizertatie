package disertation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class DataGethererThread {

//    @Scheduled(fixedRate = 120000)
    public void scheduleTaskWithFixedRate() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv", true));

            RestTemplate restTemplate = new RestTemplate();
            for (Map.Entry<String, StockData> entry : Constants.STOCK_DATA.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getValue().getSymbol());

                String fooResourceUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + entry.getValue().getSymbol() + "&outputsize=full&apikey=SP8DOIB1TKHU27FC";
                ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
                try {
                    JsonNode json = new ObjectMapper().readTree(response.getBody());
                    Iterator<JsonNode> nodes = json.get("Time Series (Daily)").elements();
                    while (nodes.hasNext()) {
                        JsonNode node = nodes.next();
                        sb.append(",").append(node.get("1. open").asDouble()).append(",").append(node.get("2. high").asDouble()).append(",").append(node.get("3. low").asDouble()).append(",").append(node.get("4. close").asDouble());
                    }
                    sb.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                bw.write(sb.toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public static void initializeFromFile() {
        Path pathToFile = Paths.get("data.csv");
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String companySymbol = attributes[0];
                for (StockData data : Constants.STOCK_DATA.values()) {
                    if (!data.getSymbol().equals(companySymbol))
                        continue;

                    List<Double> open = new ArrayList<>();
                    List<Double> high = new ArrayList<>();
                    List<Double> low = new ArrayList<>();
                    List<Double> close = new ArrayList<>();

                    for (int i = 1; i < attributes.length; i += 4) {
                        open.add(Double.parseDouble(attributes[i]));
                        high.add(Double.parseDouble(attributes[i + 1]));
                        low.add(Double.parseDouble(attributes[i + 2]));
                        close.add(Double.parseDouble(attributes[i + 3]));
                    }

                    data.setOpen(open);
                    data.setHigh(high);
                    data.setLow(low);
                    data.setClose(close);
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
