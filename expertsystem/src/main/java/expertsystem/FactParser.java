package expertsystem;

public class FactParser{

    public FactRepository getFactRepository(){
        FactRepository factRepository = new FactRepository();
        
        Document doc = XMLParser.doc;
        NodeList nList = doc.getElementsByTagName("Fact");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
                    
            System.out.println("\nCurrent Element :" + nNode.getNodeName());//For test
                    
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    
                Element eElement = (Element) nNode;
                String id = eElement.getAttribute("id");
                String description = ((Element) eElement.getElementsByTagName("Description").item(0)).getAttribute("value");
                Fact fact = new Fact(id, description);
                
                System.out.println("Fact id : " + eElement.getAttribute("id")); //For test
                System.out.println("Description : " + ((Element) eElement.getElementsByTagName("Description").item(0)).getAttribute("value")); //for test
                Node evals = eElement.getElementsByTagName("Evals").item(0);
                NodeList selection = ((Element) evals).getElementsByTagName("Eval");

                for (int j = 0; j < selection.getLength(); j++) {
                    System.out.println(((Element) selection.item(j)).getAttribute("id"));//For test
                    System.out.println(((Element)selection.item(j)).getTextContent()); //For test
                    String value = ((Element) selection.item(j)).getAttribute("id");
                    Boolean state = ((Element)selection.item(j)).getTextContent().equals("true");
                    fact.setFactValueById(eval, value);
                }
            }
        }
    }

}