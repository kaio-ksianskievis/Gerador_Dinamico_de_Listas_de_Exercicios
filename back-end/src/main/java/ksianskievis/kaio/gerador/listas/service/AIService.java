package ksianskievis.kaio.gerador.listas.service;

import ksianskievis.kaio.gerador.listas.dto.QuestaoDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder chatBuilder){
        this.chatClient = chatBuilder.build();
    }

    public QuestaoDTO gerarQuestaoAI(){
        String instrucaoSistema = """
            Você é um professor universitário de Cálculo B.
            Gere uma questão de múltipla escolha.
            Regras obrigatórias:
            1. Formate a matemática estritamente em LaTeX.
            2. Use DUPLA barra invertida para todos os comandos LaTeX (exemplo: \\\\lim, \\\\frac, \\\\int).
            3. Retorne APENAS um objeto JSON e rigorosamente mais nada.
            4. Não foque toda vez em um conteúdo apena
            
            O formato exato do JSON deve ser:
            {
              "textoPergunta": "...",
              "alternativaA": "...",
              "alternativaB": "...",
              "alternativaC": "...",
              "alternativaD": "...",
              "alternativaCorreta": "..."
            }
            """;
        return this.chatClient.prompt()
                .system(instrucaoSistema)
                .user("Gere uma questão sobre integrais definidas ou derivadas parciais.")
                .call()
                .entity(QuestaoDTO.class);
    }

}
