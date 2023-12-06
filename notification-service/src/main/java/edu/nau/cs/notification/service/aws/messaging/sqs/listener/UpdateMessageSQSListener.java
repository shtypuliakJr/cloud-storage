package edu.nau.cs.notification.service.aws.messaging.sqs.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nau.cs.notification.service.aws.messaging.sqs.consumer.UpdateMessageConsumer;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class UpdateMessageSQSListener {

    private final UpdateMessageConsumer updateMessageConsumer;
    private final ObjectMapper objectMapper;

    @SqsListener("${notification.updates.queue.name}")
    public void onSyncUpdate(@NonNull String message) {
        log.info("New message: {}", message);
//        objectMapper.readValue(message, )
    }

}
