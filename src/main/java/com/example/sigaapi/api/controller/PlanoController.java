import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Plano;
import com.example.sigaapi.api.dto.PlanoDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.PlanoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
