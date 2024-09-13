package com.example.Rest_API.entity;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="employ_entry")
@Data

public class Employee {
    @Id
    private ObjectId id;
    private String name;
    private String job;
}
