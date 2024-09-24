//import javax.ws.rs.*;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@RestController
@Path("/devices")
public class DeviceController {
    @GET
    @RequestMapping("/devices/{device_id}/telemetry/latest")
    public Device get_telemetry(Device device) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("telemetry-data", "telemetry", "device_id=10");
        producer.send(record);
        producer.close();

        return device;
    }

    @GET
    @RequestMapping("/devices/{device_id}")
    public Device get_deviceinfo(Device device) {
        return device;
    }

    @GET
    @RequestMapping("/devices/{device_id}/status")
    public Device get_status(Device device) {
        return device;
    }

    @PUT
    @RequestMapping("/devices/{device_id}/status")
    public Device set_status(Device device) {
        return device;
    }

    @POST
    @RequestMapping("/devices/{device_id}/commands")
    public Device set_telemetry(Device device) {
        return device;
    }

    @GET
    @RequestMapping("/devices/{device_id}/telemetry")
    public Device get_historytelemetry(Device device) {
        return device;
    }
}
