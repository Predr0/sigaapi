    package com.example.sigaapi.service;

    import com.example.sigaapi.Model.Entity.Mensagem;
    import com.example.sigaapi.Model.Repository.MensagemRepository;
    import com.example.sigaapi.exception.RegraNegocioException;
    import jakarta.transaction.Transactional;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Objects;
    import java.util.Optional;

    @Service
    public class MensagemService {
        private MensagemRepository repository;

        public MensagemService(MensagemRepository repository) {
            this.repository = repository;
        }

        public List<Mensagem> getMensagens() {
            return repository.findAll();
        }

        public Optional<Mensagem> getMensagemById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Mensagem salvar(Mensagem mensagem) {
            validar(mensagem);
            return repository.save(mensagem);
        }

        @Transactional
        public void excluir(Mensagem mensagem) {
            Objects.requireNonNull(mensagem.getId());
            repository.delete(mensagem);
        }

        public void validar(Mensagem mensagem) {
            if (mensagem.getRemetente() == null || mensagem.getRemetente().trim().equals("")) {
                throw new RegraNegocioException("Remetente inválido");
            }
            if (mensagem.getDestinatario() == null || mensagem.getDestinatario().trim().equals("")) {
                throw new RegraNegocioException("Destinatário inválido");
            }
            if (mensagem.getData() == null || mensagem.getData().trim().equals("")) {
                throw new RegraNegocioException("Data inválida");
            }
            if (mensagem.getConteudo() == null || mensagem.getConteudo().trim().equals("")) {
                throw new RegraNegocioException("Conteúdo inválido");
            }
        }
    }
