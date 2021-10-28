package me.givo.nationdbapiproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
public class CustomResponseDto {
    private String timestamp = LocalTime.now(ZoneId.of("Z")).toString();
    private String status;
    private String message;
    private String path;

    public CustomResponseDto(String status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
