import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.FuncionarioDTO;
import com.example.sigaapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> get() {
        List<Funcionario> funcionarios = funcionarioService.getFuncionarios();
        List<FuncionarioDTO> dtoList = funcionarios.stream()
                .map(FuncionarioDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Funcionario converter(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Funcionario.class);
    }
}
