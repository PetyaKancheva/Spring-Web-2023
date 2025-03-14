package com.example.demo.service;

import com.example.demo.model.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAll();
    void send(CommentDTO commentDTO);
    void delete(CommentDTO commentDTO);
}
