package ksianskievis.kaio.gerador.listas.controller;

import jakarta.persistence.EntityNotFoundException;
import ksianskievis.kaio.gerador.listas.dto.QuestaoDTO;
import ksianskievis.kaio.gerador.listas.model.ModelQuestao;
import ksianskievis.kaio.gerador.listas.repository.RepositoryQuestao;
import ksianskievis.kaio.gerador.listas.service.AIService;
import ksianskievis.kaio.gerador.listas.service.QuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // O import correto do RequestBody está aqui dentro!

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/questoes") // Prefixo global para todas as rotas
@RequiredArgsConstructor
public class ControllerQuestao {

    // O "final" é OBRIGATÓRIO para o @RequiredArgsConstructor funcionar!
    private final AIService aiService;
    private final RepositoryQuestao questaoRepository;
    private final QuestaoService questaoService;

    // Rota: GET /api/questoes/gerar
    @GetMapping("/gerar")
    public ResponseEntity<QuestaoDTO> gerarQuestao() {
        QuestaoDTO entidade = aiService.gerarQuestaoAI("Cálculo B, integrais duplas ou derivadas parciais");
        questaoService.adicionarQuestao(entidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(entidade);
    }

    // Rota: GET /api/questoes
    @GetMapping
    public ResponseEntity<List<QuestaoDTO>> mostrarTodas() {
        List<QuestaoDTO> lista = questaoRepository.findAll().stream()
                .map(QuestaoDTO::new)
                .toList();
        return ResponseEntity.ok(lista); // Atalho moderno para status 200 OK
    }

    // Rota: GET /api/questoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<QuestaoDTO> mostrarQuestao(@PathVariable UUID id) {
        ModelQuestao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada."));

        return ResponseEntity.ok(new QuestaoDTO(questao));
    }

    // Rota: POST /api/questoes
    @PostMapping
    public ResponseEntity<QuestaoDTO> criarQuestao(@RequestBody QuestaoDTO novaQuestao) {
        questaoService.adicionarQuestao(novaQuestao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaQuestao);
    }

    // Rota: PUT /api/questoes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<QuestaoDTO> atualizarQuestao(@PathVariable UUID id, @RequestBody QuestaoDTO novaQuestao) {
        QuestaoDTO questaoAtualizada =  new QuestaoDTO(questaoService.atualizarQuestao(id, novaQuestao));
        return ResponseEntity.ok(questaoAtualizada);
    }

    // Rota: DELETE /api/questoes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaQuestao(@PathVariable UUID id) {
        questaoService.deletarQuestao(id);
        return ResponseEntity.noContent().build();
    }
}