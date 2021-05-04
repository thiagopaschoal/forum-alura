package br.com.tspaschoal.forumalura.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ErrorDTO {
    private String field;
    private String errorMessage;
}
