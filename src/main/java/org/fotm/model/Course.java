package org.fotm.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class Course {
    private String name;
    private Map<String, LocalDateTime> sections;
}
