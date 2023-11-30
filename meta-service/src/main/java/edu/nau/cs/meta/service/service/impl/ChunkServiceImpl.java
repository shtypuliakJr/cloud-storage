package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.ChunkObjectDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import edu.nau.cs.meta.service.entity.FileObject;
import edu.nau.cs.meta.service.exception.CsChunkDoesNotExistsException;
import edu.nau.cs.meta.service.exception.CsFileObjectDoesNotExistsException;
import edu.nau.cs.meta.service.mapper.ChunkMapper;
import edu.nau.cs.meta.service.repository.ChunkRepository;
import edu.nau.cs.meta.service.repository.FileObjectRepository;
import edu.nau.cs.meta.service.service.ChunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChunkServiceImpl implements ChunkService {

    private final FileObjectRepository fileObjectRepository;
    private final ChunkRepository chunkRepository;
    private final ChunkMapper chunkMapper;

    @Override
    public ChunkObjectDTO getChunkData(String userId, String chunkId) {
        return chunkRepository.findById(chunkId)
                .map(chunkMapper::mapChunkToDTO)
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId, chunkId));
    }

    @Transactional
    @Override
    public ChunkObjectDTO saveChunkData(String userId, ChunkObjectDTO chunkObjectDTO) {
        Chunk chunk = chunkMapper.mapChunkToEntity(chunkObjectDTO);
        FileObject fileObject = fileObjectRepository.findById(chunkObjectDTO.getFileObjectId())
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId, chunkObjectDTO.getFileObjectId()));
        chunk.setFileObject(fileObject);

        return chunkMapper.mapChunkToDTO(chunkRepository.save(chunk));
    }

    @Transactional
    @Override
    public ChunkObjectDTO editChunkData(String userId, String chunkId, ChunkObjectDTO chunkObjectDTO) {
        return chunkRepository.findById(chunkId)
                .map(chunk -> {
                    chunk.setChunkOrder(chunkObjectDTO.getChunkOrder());
                    chunk.setChunkSize(chunkObjectDTO.getChunkSize());
                    chunk.setChunkChecksum(chunkObjectDTO.getChunkChecksum());
                    chunk.setUpdatedAt(LocalDateTime.now());
                    return chunk;
                })
                .map(chunkRepository::saveAndFlush)
                .map(chunkMapper::mapChunkToDTO)
                .orElseThrow(() -> new CsChunkDoesNotExistsException(userId, chunkId));
    }

    @Override
    public void deleteChunkData(String userId, String chunkId) {
        chunkRepository.deleteById(chunkId);

    }

    @Override
    public void deleteChunksData(String userId, List<String> chunkIds) {
        chunkRepository.deleteAllById(chunkIds);
    }

}
