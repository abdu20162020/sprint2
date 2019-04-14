package sw2project.Controller.Entities.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import antlr.collections.List;
import sw2project.Controller.Entities.Quiz;
import sw2project.Controller.Entities.quistion;

public interface QuizEntitiesRepository extends CrudRepository<Quiz,Integer> {
	

	Quiz findByTitle(String title);
	


}
