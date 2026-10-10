package ksianskievis.kaio.gerador.listas.controller;


import ksianskievis.kaio.gerador.listas.dto.QuestaoDTO;
import ksianskievis.kaio.gerador.listas.model.ModelQuestao;
import ksianskievis.kaio.gerador.listas.repository.RepositoryQuestao;
import ksianskievis.kaio.gerador.listas.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerQuestao {
    @Autowired
    private AIService aiService;

    @GetMapping("/gerar/questao")
    private ResponseEntity<QuestaoDTO> gerarQuestao(){
        return  ResponseEntity.status(HttpStatus.OK).body(aiService.gerarQuestaoAI());
    }
}
