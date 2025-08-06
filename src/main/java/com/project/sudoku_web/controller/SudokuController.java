package com.project.sudoku_web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sudoku_web.model.Celula;
import com.project.sudoku_web.service.SudokuService;

import jakarta.annotation.PostConstruct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String helloWorld(){
        return sudokuService.helloWorld("Noga");
    }

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        return entity;
    }

    @GetMapping("/new")
    public int[][] iniciarSudoku(){
        return sudokuService.iniciarTabuleiro(celulas);
    }
}
