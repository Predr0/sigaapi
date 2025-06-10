import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Evento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.EventoDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.EventoService;
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
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;
    private final EventoService eventoService;

    @GetMapping()
    public ResponseEntity<List<EventoDTO>> get() {
        List<Evento> eventos = eventoService.getEvento();
        List<EventoDTO> dtoList = eventos.stream()
                .map(EventoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Evento converter(EventoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Evento evento = modelMapper.map(dto, Evento.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            evento.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            evento.setFuncionario(funcionario.orElse(null));
        }

        return evento;
    }
}
