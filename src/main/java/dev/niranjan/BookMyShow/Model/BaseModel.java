package dev.niranjan.BookMyShow.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto Generation of id.
    @Column(name = "ID") //For Setting Column name for id in DB.
    private int id;
    @Column(name = "CREATED AT")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}