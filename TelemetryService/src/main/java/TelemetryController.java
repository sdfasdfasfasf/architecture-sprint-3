import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@RestController
@Path("/telemetry")
public class TelemetryController {

    @GET
    @RequestMapping("/telemetry/{device_id}/telemetry/latest")
    public Telemetry get_telemetry(Telemetry telemetry) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "telemetry-consumers");
        props.put("enable.auto.commit", "true");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("telemetry-data"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }

        return telemetry;
    }

    @POST
    @RequestMapping("/telemetry/{device_id}/commands")
    public Telemetry set_telemetry(Telemetry telemetry) {
        return telemetry;
    }

    @GET
    @RequestMapping("/telemetry/{device_id}/telemetry")
    public Telemetry get_historytelemetry(Telemetry telemetry) {
        return telemetry;
    }

    public Telemetry get_temperature(Telemetry telemetry) {
        return telemetry;
    }

    public Telemetry set_temperature(Telemetry telemetry) {
        return telemetry;
    }
}
