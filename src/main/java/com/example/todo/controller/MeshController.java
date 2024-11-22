package com.example.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.MeshDto;
import com.example.todo.enums.statusEnum;
import com.example.todo.service.impl.MeshServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MeshController {

    private final MeshServiceImpl service;

    @GetMapping("/iteracao2")
    public String iteracao2() {
        return "Ola de iteracao2";
    }

    @GetMapping()
    public ResponseEntity<List<MeshDto>> findAll() {
        try {
            List<MeshDto> list = service.getAll();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    public ResponseEntity<MeshDto> create(@RequestBody MeshDto dto) {
        try {
            MeshDto meshDto = service.create(dto);
            return ResponseEntity.ok().body(meshDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody MeshDto dto, @PathVariable Long id) {
        try {
            MeshDto meshDto = service.update(id, dto);
            return ResponseEntity.ok(meshDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody statusEnum status) {
        try {
            service.updateStatus(id, status);
            return new ResponseEntity<>("Update Status Result", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
