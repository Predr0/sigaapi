import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Mensagem;
import com.example.sigaapi.api.dto.MensagemDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.MensagemService;
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
@RequestMapping("/api/v1/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final AlunoService alunoService;
    private final MensagemService mensagemService;

    @GetMapping()
    public ResponseEntity<List<MensagemDTO>> get() {
        List<Mensagem> mensagens = mensagemService.getMensagens();
        List<MensagemDTO> dtoList = mensagens.stream()
                .map(MensagemDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Mensagem converter(MensagemDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Mensagem mensagem = modelMapper.map(dto, Mensagem.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            mensagem.setAluno(aluno.orElse(null));
        }

        return mensagem;
    }
}
