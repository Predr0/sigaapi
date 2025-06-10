import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.Model.Entity.Modalidade;
import com.example.sigaapi.api.dto.ModalidadeDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.FuncionarioService;
import com.example.sigaapi.service.ModalidadeService;
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
@RequestMapping("/api/v1/modalidades")
@RequiredArgsConstructor
public class ModalidadeController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;
    private final ModalidadeService modalidadeService;

    @GetMapping
    public ResponseEntity<List<ModalidadeDTO>> get() {
        List<Modalidade> modalidades = modalidadeService.getModalidades();
        List<ModalidadeDTO> dtoList = modalidades.stream()
                .map(ModalidadeDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Modalidade converter(ModalidadeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Modalidade modalidade = modelMapper.map(dto, Modalidade.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            modalidade.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            modalidade.setFuncionario(funcionario.orElse(null));
        }

        return modalidade;
    }
}
