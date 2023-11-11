package edu.nau.cs.file.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(setterPrefix = "with")
public class ErrorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1537601011778437672L;

    @JsonProperty(value = "status")
    private int status;

    @JsonProperty(value = "path")
    private String path;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "time")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    @JsonProperty(value = "key")
    private List<String> keys;

}
