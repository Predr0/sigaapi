import com.example.sigaapi.Model.Entity.Acompanhamento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.AcompanhamentoDTO;
import com.example.sigaapi.service.AcompanhamentoService;
import com.example.sigaapi.service.FuncionarioService;
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
@RequestMapping("/api/v1/acompanhamentos")
@RequiredArgsConstructor
public class AcompanhamentoController {

    private final FuncionarioService funcionarioService;
    private final AcompanhamentoService acompanhamentoService;

    @GetMapping()
    public ResponseEntity<List<AcompanhamentoDTO>> get() {
        List<Acompanhamento> acompanhamentos = acompanhamentoService.getAcompanhamentos();
        List<AcompanhamentoDTO> dtoList = acompanhamentos.stream()
                .map(AcompanhamentoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Acompanhamento converter(AcompanhamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Acompanhamento acompanhamento = modelMapper.map(dto, Acompanhamento.class);

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            acompanhamento.setFuncionario(funcionario.orElse(null));
        }

        return acompanhamento;
    }
}
