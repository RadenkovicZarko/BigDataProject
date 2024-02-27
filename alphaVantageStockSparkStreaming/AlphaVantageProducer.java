package alphaVantageStockSparkStreaming;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;


public class AlphaVantageProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        Producer<String,String> producer = new KafkaProducer<>(properties);

        try{
            String alphaVantageApiKey="TXJ23ME4J8DEH3CW";
            String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&outputsize=full&apikey="+alphaVantageApiKey;
            String jsonString = fetchDataFromApi(apiUrl);
            System.out.println(jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode timeSeriesNode = jsonNode.get("Time Series (Daily)");


            timeSeriesNode.fields().forEachRemaining(entry -> {
                String date = entry.getKey();
                JsonNode data = entry.getValue();

                ObjectNode messageContent = objectMapper.createObjectNode();
                messageContent.put("date", date);
                messageContent.set("data", data);

                try {
                    String messageValue = objectMapper.writeValueAsString(messageContent);
                    ProducerRecord<String, String> record = new ProducerRecord<>("stockMSFT", messageValue);
                    System.out.println("Sending message: " + messageValue);
                    producer.send(record);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fetchDataFromApi(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder response = new StringBuilder();

        try(Scanner scanner = new Scanner(connection.getInputStream())){
            while(scanner.hasNextLine()){
                response.append(scanner.nextLine());
            }
        }
        return response.toString();
     }
}
