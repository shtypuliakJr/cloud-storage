package edu.nau.cs.file.service.service.get.impl;

import edu.nau.cs.file.service.dto.FileObjectDTO;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.get.GetFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFileServiceImpl implements GetFileService {

    private final AwsS3Service awsS3Service;
    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileObjectDTO getFile(String userId, String fileId) {
//        awsS3Service.getObject(, bucket);
        return null;
    }

    @Override
    public List<FileObjectDTO> getFiles(List<String> fileIds) {
        return null;
    }

    @Override
    public FileObjectDTO getArchivedFiles(String userId, List<String> fileIds) {
        return null;
    }

    @Override
    public FileObjectDTO getArchivedFileChunks(String fileId, List<String> chunkIds) {
        return null;
    }

}

