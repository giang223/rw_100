package com.vti.dto;

import java.util.List;

public class ImportError<T> {
    private T csv;
    private List<String> message;

    public ImportError()
    {
    }

    public ImportError(T line, List<String> message) {
        this.csv = line;
        this.message = message;
    }

    public T getCsv() {
        return csv;
    }

    public void setCsv(T csv) {
        this.csv = csv;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
