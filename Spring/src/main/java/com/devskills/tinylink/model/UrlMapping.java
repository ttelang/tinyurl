package com.devskills.tinylink.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {
    @Id
    @NotBlank(message = "Short URL cannot be blank")
    @Size(max=6, message = "Short URL must not exceed 6 characters")
    private String shortUrl;

    @NotBlank(message = "Long URL cannot be blank")
    private String longUrl;
}
