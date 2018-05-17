package expertsystem;

import java.util.Map;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Scanner;

public class ESProvider {

    private Scanner reader = new Scanner(System.in);
    private String input;
    private Map<String, Boolean> matches;

    private RuleRepository ruleRepository;
    private FactRepository factRepository;

    public ESProvider(FactParser factParser, RuleParser ruleParser){
        
        this.ruleRepository = ruleParser.getRuleRepository();
        this.factRepository = factParser.getFactRepository();
    }

    private void collectAnswers(){
        this.matches = new HashMap<>();
        Iterator<Question> iterator = this.ruleRepository.getIterator();
        while(iterator.hasNext()){
            
            Question currentQuestion = iterator.next();
            boolean wrongInput = true;
            System.out.println(currentQuestion.getQuestion());

            while(wrongInput){

                input = reader.nextLine();
                try{
                    matches.put(currentQuestion.getId(), currentQuestion.getEvaluatedAnswer(input));
                    wrongInput = false;
                }catch(InvalidParameterException e){
                    System.out.println(e);
                    wrongInput = true;
                }
            }
            
        }
    }

    private boolean getAnswerByQuestion(String questionID){
        return true;
    }

    private String evaluate(){
        return null;
    }   
}