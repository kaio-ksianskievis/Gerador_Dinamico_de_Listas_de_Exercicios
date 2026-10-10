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

    @Column(nullable = false)
    private  String texto;

    @Column(nullable = false)
    private  String alternativas_a;

    @Column(nullable = false)
    private  String alternativas_b;

    @Column(nullable = false)
    private  String alternativas_c;

    @Column(nullable = false)
    private  String alternativas_d;

    @Column(nullable = false)
    private  String alternativa_correta;

}
