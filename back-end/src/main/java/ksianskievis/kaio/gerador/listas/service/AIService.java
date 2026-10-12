package ksianskievis.kaio.gerador.listas.service;

import ksianskievis.kaio.gerador.listas.dto.QuestaoDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder chatBuilder) {
        this.chatClient = chatBuilder.build();
    }

    // Agora o método recebe o assunto dinamicamente
    public QuestaoDTO gerarQuestaoAI(String assunto) {

        String instrucaoSistema = """
            Você é um professor universitário de Cálculo B.
            Gere uma questão de múltipla escolha inédita.
            
            Regras obrigatórias:
            1. Formate a matemática estritamente em LaTeX.
            2. Use DUPLA barra invertida para todos os comandos LaTeX (exemplo: \\\\lim, \\\\frac, \\\\int).
            3. Retorne APENAS um objeto JSON e rigorosamente mais nada.
            4. Garanta que a questão tenha um nível de dificuldade intermediário.
            
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
                // O assunto escolhido é injetado diretamente no prompt do utilizador
                .user("Gere uma questão sobre o seguinte tema: " + assunto)
                .call()
                .entity(QuestaoDTO.class);
    }
}