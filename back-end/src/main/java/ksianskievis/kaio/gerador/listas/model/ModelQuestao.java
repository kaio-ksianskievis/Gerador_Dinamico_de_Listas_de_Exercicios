package ksianskievis.kaio.gerador.listas.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "questao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ModelQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // columnDefinition = "TEXT" garante que equações longas em LaTeX cabem no banco
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String alternativaA;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String alternativaB;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String alternativaC;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String alternativaD;

    @Column(nullable = false)
    private String alternativaCorreta;
}