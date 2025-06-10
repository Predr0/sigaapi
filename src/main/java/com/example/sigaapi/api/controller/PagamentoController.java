import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.Model.Entity.Pagamento;
import com.example.sigaapi.api.dto.PagamentoDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.FuncionarioService;
import com.example.sigaapi.service.PagamentoService;
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
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;
    private final PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> get() {
        List<Pagamento> pagamentos = pagamentoService.getPagamentos();
        List<PagamentoDTO> dtoList = pagamentos.stream()
                .map(PagamentoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Pagamento converter(PagamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            pagamento.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            pagamento.setFuncionario(funcionario.orElse(null));
        }

        return pagamento;
    }
}
