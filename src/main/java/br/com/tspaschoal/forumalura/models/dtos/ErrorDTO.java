package br.com.tspaschoal.forumalura.models.dtos;

public class ErrorDTO {
    private String field;
    private String errorMessage;

    public ErrorDTO(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getField() {
        return field;
    }
}
