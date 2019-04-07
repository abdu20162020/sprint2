package sw2project.Controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import sw2project.Controller.Entities.Quiz;
import sw2project.Controller.Entities.quistion;
import sw2project.Controller.Entities.Repository.QuistionEntitiesRepository;
import sw2project.Controller.Entities.Repository.QuizEntitiesRepository;

@RestController
@CrossOrigin(origins = "*")
public class QuizController {
	@Autowired
	private QuizEntitiesRepository quizRepo;
	@Autowired
	private QuistionEntitiesRepository quistionRepo;
	public QuizController(QuizEntitiesRepository quizRepo) {
		this.quizRepo = quizRepo;
	}
	public QuizController(){}

	@RequestMapping("/addquiz")
	@ResponseBody
	public int addquiz(@RequestBody Quiz quiz) {
		
	Quiz q=	quizRepo.save(quiz);		
			if(q.getID()>0){
			return q.getID();			
		}
		return -1;
		
	}
	@RequestMapping("/addmcq")
	@ResponseBody
	public int addmcq(@RequestBody quistion mcq) {
		
		quistion q=	quistionRepo.save(mcq);		
				if(q.getID()>0){
				return q.getID();			
			}
			return -1;
			
		}
	@RequestMapping("/quiz")
	@ResponseBody
	public ArrayList<Quiz> quizes() {
		Iterable<Quiz> quizes = quizRepo.findAll();
		ArrayList<Quiz> quizess = new ArrayList<Quiz>();

		for (Quiz b : quizes) {
			quizess.add(b);
		}
		return quizess;
	}
	@RequestMapping(value = "/deletequiz/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deletequiz(@PathVariable("id") int id){
		Quiz qq=quizRepo.findOne(id);
		
		if(qq!=null){
			quizRepo.delete(qq);
			return true;
		}
		return false;
		
		
	}
	@RequestMapping(value = "/deletequistion/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deletequistion(@PathVariable("id") int id){
		Iterable<quistion> quistions=quistionRepo.findAll();
		ArrayList<quistion> allquistion = new ArrayList<quistion>();

		for (quistion b : quistions) {
			allquistion.add(b);
		}
		ArrayList<quistion> filterquistion = new ArrayList<quistion>();
		for (quistion b : allquistion) {
		if(b.getQuiz_id()==id){
			filterquistion.add(b);
			
		}
		}
		if(filterquistion.size()==0){
			
			return false;
			
		}	
		for(quistion b : filterquistion) {
			quistionRepo.delete(b);
			
		}
		return true;
	}
}
	