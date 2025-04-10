package com.pdflibrary;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AwsConfig config = new AwsConfig();
        S3Service s3Service = new S3Service(config);
        PdfManager pdfManager = new PdfManager(s3Service);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao PDF-Library CLI");
        while (true) {
            System.out.println("\n1. Listar PDFs\n2. Filtrar PDFs por nome\n3. Baixar PDF\n4. Sair");
            System.out.print("Escolha uma opção: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    pdfManager.listPdfs();
                    break;
                case "2":
                    System.out.print("Digite o termo para filtrar: ");
                    String filtro = scanner.nextLine();
                    pdfManager.filterPdfs(filtro);
                    break;
                case "3":
                    System.out.print("Digite o nome do PDF para baixar: ");
                    String nome = scanner.nextLine();
                    pdfManager.downloadPdf(nome);
                    break;
                case "4":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
