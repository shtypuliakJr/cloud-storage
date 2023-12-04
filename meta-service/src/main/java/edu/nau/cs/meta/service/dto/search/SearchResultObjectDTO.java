package edu.nau.cs.meta.service.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultObjectDTO {

    private String id;

    private String objectName;

    private Boolean isFolder;

    private int order;

}
