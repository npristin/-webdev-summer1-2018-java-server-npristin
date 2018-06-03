package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import webdev.models.EssayExamQuestion;

public interface EssayExamQuestionRepository extends CrudRepository<EssayExamQuestion, Integer> {
}
