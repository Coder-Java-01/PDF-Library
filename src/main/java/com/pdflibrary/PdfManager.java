package com.pdflibrary;

import com.pdflibrary.model.PdfFile;
import java.util.List;

public class PdfManager {
    private final S3Service s3Service;

    public PdfManager(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public void listPdfs() {
        List<PdfFile> arquivos = s3Service.listPdfs();
        arquivos.forEach(System.out::println);
    }

    public void filterPdfs(String termo) {
        List<PdfFile> arquivos = s3Service.listPdfs();
        arquivos.stream()
                .filter(pdf -> pdf.getName().toLowerCase().contains(termo.toLowerCase()))
                .forEach(System.out::println);
    }

    public void downloadPdf(String nome) {
        boolean sucesso = s3Service.downloadPdf(nome);
        if (sucesso) {
            System.out.println("Download concluído: " + nome);
        } else {
            System.out.println("PDF não encontrado: " + nome);
        }
    }
}
