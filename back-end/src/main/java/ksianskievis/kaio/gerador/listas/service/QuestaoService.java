package ksianskievis.kaio.gerador.listas.service;

import jakarta.persistence.EntityNotFoundException;
import ksianskievis.kaio.gerador.listas.dto.QuestaoDTO;
import ksianskievis.kaio.gerador.listas.model.ModelQuestao;
import ksianskievis.kaio.gerador.listas.repository.RepositoryQuestao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final RepositoryQuestao questaoRepository;

    // Retorna a própria Questão salva (com o UUID gerado!)
    public void adicionarQuestao(QuestaoDTO questaoDTO) {
        ModelQuestao entidade = new ModelQuestao();
        entidade.setTexto(questaoDTO.textoPergunta());
        entidade.setAlternativaA(questaoDTO.alternativaA());
        entidade.setAlternativaB(questaoDTO.alternativaB());
        entidade.setAlternativaC(questaoDTO.alternativaC());
        entidade.setAlternativaD(questaoDTO.alternativaD());
        entidade.setAlternativaCorreta(questaoDTO.alternativaCorreta());

        questaoRepository.save(entidade);
    }

    // Busca a entidade viva no banco, altera e salva
    public ModelQuestao atualizarQuestao(UUID id, QuestaoDTO questaoNova) {
        ModelQuestao entidadeExistente = questaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada com o ID: " + id));

        entidadeExistente.setTexto(questaoNova.textoPergunta());
        entidadeExistente.setAlternativaA(questaoNova.alternativaA());
        entidadeExistente.setAlternativaB(questaoNova.alternativaB());
        entidadeExistente.setAlternativaC(questaoNova.alternativaC());
        entidadeExistente.setAlternativaD(questaoNova.alternativaD());
        entidadeExistente.setAlternativaCorreta(questaoNova.alternativaCorreta());
        return questaoRepository.save(entidadeExistente);
    }

    // Verifica se a questão existe antes de tentar deletar
    public void deletarQuestao(UUID id) {
        if (!questaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Não é possível deletar. Questão não encontrada com o ID: " + id);
        }
        questaoRepository.deleteById(id);
    }
}