package edu.nau.cs.meta.service.service.resolver;

import edu.nau.cs.meta.service.entity.FileObject;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangesResolver {

    private final SqsTemplate sqsTemplate;

    public void notifyChunkChanges(FileObject fileObject) {
        sqsTemplate.send(to -> to.payload("hello world").delaySeconds(10));
    }

    public void notifyChunkChanges(String message) {
        sqsTemplate.send(to -> to.payload(message).delaySeconds(1));
    }

}
