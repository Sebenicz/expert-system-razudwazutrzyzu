package expertsystem;

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class RuleParser extends XMLParser{

    RuleRepository ruleRepository;

    public RuleParser(){
        getRuleRepository();
    }

    public RuleRepository getRuleRepository(){
        RuleRepository ruleRepository = new RuleRepository();
        
        Document doc = XMLParser.doc;
        NodeList nList = doc.getElementsByTagName("Rule");
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
                    
            System.out.println("\nCurrent Element :" + nNode.getNodeName()); //for test
                    
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element eElement = (Element) nNode;

                String questionID = eElement.getAttribute("id");
                String questionName = eElement.getElementsByTagName("Question").item(0).getTextContent();

                ruleRepository.addQuestion(question);

                System.out.println("Rule id : " + eElement.getAttribute("id")); //for test
                System.out.println("Question : " + eElement.getElementsByTagName("Question").item(0).getTextContent()); //for test
                Node nAnswer = eElement.getElementsByTagName("Answer").item(0);
                NodeList selection = ((Element) nAnswer).getElementsByTagName("Selection");
                for (int j = 0; j < selection.getLength(); j++) {

                    Answer answer = new Answer();

                    Boolean state = ((Element) selection.item(j)).getAttribute("value"); //for test

                    if(xElement.getElementsByTagName("SingleValue").getLength() > 0){
                    String valueName = ((Element) xElement.getElementsByTagName("SingleValue").item(0)).getAttribute("value");
                    Value value = new SingleValue(valueName ,state);
                    answer.addValue(value);

                    }else{

                    String valueName = ((Element) xElement.getElementsByTagName("MultipleValue").item(0)).getAttribute("value");

                    Value value = new MultipleValue(getValueList(valueName),state);
                    answer.addValue(value);
                    }
                }
                Question question = new Question(questionID, questionName, answer);
            }
        }
    }

    private List<String> getValueList(String valueName){
        List<String> valueList = new List<String>();

        String[] splitedValue = valueName.split(",");
        for(int i = 0; i < splitedValue.length; i++){
            valueList.add(splitedValue[i]);
        }
        return valueList;
    }
    
}