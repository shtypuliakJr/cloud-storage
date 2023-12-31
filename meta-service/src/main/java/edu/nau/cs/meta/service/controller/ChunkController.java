package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.dto.ChunkObjectDTO;
import edu.nau.cs.meta.service.service.ChunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.meta.service.constants.Endpoint.CHUNKS;
import static edu.nau.cs.meta.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT + CHUNKS)
public class ChunkController {

    private final ChunkService chunkService;
    private final String userId = USER_ID;

    @GetMapping("/{chunkId}")
    public ResponseEntity<ChunkObjectDTO> getChunkData(@PathVariable String chunkId) {
        return ResponseEntity.ok(chunkService.getChunkData(userId, chunkId));
    }

    @PostMapping
    public ResponseEntity<ChunkObjectDTO> saveChunkData(@RequestBody ChunkObjectDTO chunkObjectDTO) {
        return ResponseEntity.ok(chunkService.saveChunkData(userId, chunkObjectDTO));
    }

    @PutMapping("/{chunkId}")
    public ResponseEntity<ChunkObjectDTO> editChunkData(@PathVariable String chunkId, @RequestBody ChunkObjectDTO chunkObjectDTO) {
        return ResponseEntity.ok(chunkService.editChunkData(userId, chunkId, chunkObjectDTO));
    }

    @DeleteMapping("/{chunkId}")
    public ResponseEntity<Void> deleteChunkData(@PathVariable String chunkId) {
        chunkService.deleteChunkData(userId, chunkId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChunksData(@RequestParam List<String> chunkIds) {
        chunkService.deleteChunksData(userId, chunkIds);
        return ResponseEntity.noContent().build();
    }

}
