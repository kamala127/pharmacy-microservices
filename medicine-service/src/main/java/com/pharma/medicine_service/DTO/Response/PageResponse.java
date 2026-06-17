package com.pharma.medicine_service.DTO.Response;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResponse <T>(

        List<T> content,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize,
        boolean first,
        boolean last
) {
}
