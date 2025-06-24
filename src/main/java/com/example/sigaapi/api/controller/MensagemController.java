import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Mensagem;
import com.example.sigaapi.api.dto.MensagemDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final AlunoService alunoService;
    private final MensagemService mensagemService;

    @GetMapping
    public ResponseEntity<List<MensagemDTO>> get() {
        List<Mensagem> mensagens = mensagemService.getMensagens();
        List<MensagemDTO> dtoList = mensagens.stream()
                .map(MensagemDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Mensagem> mensagem = mensagemService.getMensagemById(id);
        if (!mensagem.isPresent()) {
            return new ResponseEntity<>("Mensagem não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(MensagemDTO.create(mensagem.get()));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody MensagemDTO dto) {
        try {
            Mensagem mensagem = converter(dto);
            mensagem = mensagemService.salvar(mensagem);
            return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody MensagemDTO dto) {
        if (!mensagemService.getMensagemById(id).isPresent()) {
            return new ResponseEntity<>("Mensagem não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Mensagem mensagem = converter(dto);
            mensagem.setId(id);
            mensagemService.salvar(mensagem);
            return ResponseEntity.ok(mensagem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
