    package com.example.sigaapi.api.controller;

    import com.example.sigaapi.Model.Entity.Aluno;
    import com.example.sigaapi.api.dto.AlunoDTO;
    import com.example.sigaapi.exception.RegraNegocioException;
    import com.example.sigaapi.service.AlunoService;
    import lombok.RequiredArgsConstructor;
    import org.modelmapper.ModelMapper;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/api/v1/alunos")
    @RequiredArgsConstructor
    public class AlunoController {

        private final AlunoService service;

        @GetMapping()
        public ResponseEntity<List<AlunoDTO>> get() {
            List<Aluno> alunos = service.getAlunos();
            List<AlunoDTO> dtoList = alunos.stream()
                    .map(AlunoDTO::create)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }

        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Aluno> aluno = service.getAlunoById(id);
            if (!aluno.isPresent()) {
                return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(aluno.map(AlunoDTO::create));
        }

        @PostMapping()
        public ResponseEntity post(@RequestBody AlunoDTO dto) {
            Aluno aluno = converter(dto);
            aluno = service.salvar(aluno);
            return new ResponseEntity(aluno, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AlunoDTO dto) {
            if (!service.getAlunoById(id).isPresent()) {
                return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
            }
            Aluno aluno = converter(dto);
            aluno.setId(id);
            service.salvar(aluno);
            return ResponseEntity.ok(aluno);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity excluir(@PathVariable("id") Long id) {
            Optional<Aluno> aluno = service.getAlunoById(id);
            if (!aluno.isPresent()) {
                return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
            }
            try {
                service.excluir(aluno.get());
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        public Aluno converter(AlunoDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(dto, Aluno.class);
        }
    }
