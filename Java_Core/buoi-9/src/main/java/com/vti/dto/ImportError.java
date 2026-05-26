package com.vti.dto;

import java.util.List;

public class ImportError {
    private String line;
    private List<String> message;

    public ImportError()
    {
    }

    public ImportError(String line, List<String> message) {
        this.line = line;
        this.message = message;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
