package com.pdflibrary;

import com.pdflibrary.model.PdfFile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class S3Service {
    private final S3Client s3Client;
    private final String bucket;

    public S3Service(AwsConfig config) {
        this.bucket = config.getBucketName();
        this.s3Client = S3Client.builder()
                .region(Region.of(config.getRegion()))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    public List<PdfFile> listPdfs() {
        ListObjectsV2Response res = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucket).build());
        return res.contents().stream()
                .filter(o -> o.key().endsWith(".pdf"))
                .map(o -> new PdfFile(o.key(), o.lastModified().toString()))
                .collect(Collectors.toList());
    }

    public boolean downloadPdf(String nomeArquivo) {
        try {
            GetObjectRequest getReq = GetObjectRequest.builder().bucket(bucket).key(nomeArquivo).build();
            s3Client.getObject(getReq, Paths.get(nomeArquivo));
            return true;
        } catch (S3Exception e) {
            return false;
        }
    }
}
