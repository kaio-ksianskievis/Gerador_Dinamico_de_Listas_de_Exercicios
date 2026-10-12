package ksianskievis.kaio.gerador.listas.dto;

import jakarta.validation.constraints.NotBlank;
import ksianskievis.kaio.gerador.listas.model.ModelQuestao;

public record QuestaoDTO(

        @NotBlank(message = "O texto da pergunta não pode estar vazio")
        String textoPergunta,

        @NotBlank(message = "A alternativa A é obrigatória")
        String alternativaA,

        @NotBlank(message = "A alternativa B é obrigatória")
        String alternativaB,

        @NotBlank(message = "A alternativa C é obrigatória")
        String alternativaC,

        @NotBlank(message = "A alternativa D é obrigatória")
        String alternativaD,

        @NotBlank(message = "A alternativa correta deve ser informada")
        String alternativaCorreta
) {
    public QuestaoDTO(ModelQuestao q) {
        this(
                q.getTexto(),
                q.getAlternativaA(),
                q.getAlternativaB(),
                q.getAlternativaC(),
                q.getAlternativaD(),
                q.getAlternativaCorreta()
        );
    }
}