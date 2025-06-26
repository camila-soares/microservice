package com.microservice.commons.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude( JsonInclude.Include.NON_NULL )
@NoArgsConstructor
@AllArgsConstructor
public class PageableDTO {


    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_INDEX = 0;
    private static final String DEFAULT_SORT_DIRECTION = "ASC";
    private static final String SORT_FIELD_DEFAULT = "id";

    @Builder.Default
    private int pageSize = DEFAULT_PAGE_SIZE;

    @Builder.Default
    private int pageNumber = DEFAULT_PAGE_INDEX;

    @Builder.Default
    private String sortDirection = DEFAULT_SORT_DIRECTION;

    @Builder.Default
    private String sortField = SORT_FIELD_DEFAULT;
}
