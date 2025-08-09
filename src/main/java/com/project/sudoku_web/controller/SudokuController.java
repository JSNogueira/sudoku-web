package com.project.sudoku_web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sudoku_web.model.Celula;
import com.project.sudoku_web.service.SudokuService;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    private List<Celula> celulas;

    @PostConstruct
    public void init() {
        celulas = sudokuService.preencherCelulas();
    }

    // @PostMapping("path")
    // public String postMethodName(@RequestBody String entity) {
    // return entity;
    // }

    @PostMapping("/validar")
    public ResponseEntity<Map<String, String>> validarTabuleiro(@RequestBody int[][] tabuleiro) {
       
        boolean valido = sudokuService.verificarErros(celulas, tabuleiro);
        boolean completo = sudokuService.verificarTabuleiroCompleto(tabuleiro);

        if (valido) {
            if(completo){
                return ResponseEntity.ok(Map.of("mensagem", "Tabuleiro completo"));
            } else {
                return ResponseEntity.ok(Map.of("mensagem", "Tabuleiro válido"));
            }
        } else {
            return ResponseEntity.ok(Map.of("mensagem", "Tabuleiro inválido"));
        }
    }

    @GetMapping("/new")
    public int[][] iniciarSudoku() {
        return sudokuService.iniciarTabuleiro(celulas);
    }
}
