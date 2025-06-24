import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Plano;
import com.example.sigaapi.api.dto.PlanoDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.PlanoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final AlunoService alunoService;
    private final PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> get() {
        List<Plano> planos = planoService.getPlanos();
        List<PlanoDTO> dtoList = planos.stream()
                .map(PlanoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Plano> plano = planoService.getPlanoById(id);
        if (!plano.isPresent()) {
            return new ResponseEntity<>("Plano não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(PlanoDTO.create(plano.get()));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody PlanoDTO dto) {
        try {
            Plano plano = converter(dto);
            plano = planoService.salvar(plano);
            return new ResponseEntity<>(plano, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody PlanoDTO dto) {
        if (!planoService.getPlanoById(id).isPresent()) {
            return new ResponseEntity<>("Plano não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Plano plano = converter(dto);
            plano.setId(id);
            planoService.salvar(plano);
            return ResponseEntity.ok(plano);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Plano converter(PlanoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Plano plano = modelMapper.map(dto, Plano.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            plano.setAluno(aluno.orElse(null));
        }

        return plano;
    }
}
