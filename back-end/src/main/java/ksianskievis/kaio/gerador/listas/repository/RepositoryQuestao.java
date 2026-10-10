package ksianskievis.kaio.gerador.listas.repository;

import ksianskievis.kaio.gerador.listas.model.ModelQuestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryQuestao extends JpaRepository<ModelQuestao,UUID> {

}
