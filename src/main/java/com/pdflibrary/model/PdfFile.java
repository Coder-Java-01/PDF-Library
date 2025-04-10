package com.pdflibrary.model;

public class PdfFile {
    private final String name;
    private final String uploadedAt;

    public PdfFile(String name, String uploadedAt) {
        this.name = name;
        this.uploadedAt = uploadedAt;
    }

    public String getName() {
        return name;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    @Override
    public String toString() {
        return "PDF: " + name + " (Enviado em: " + uploadedAt + ")";
    }
}
