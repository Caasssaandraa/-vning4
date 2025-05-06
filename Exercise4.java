import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Exercise4 {
	private Graph<Node> graph = new ListGraph<>();

    public void loadLocationGraph(String fileName){
      try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
         //Läs inledande lång rad med noder och lägg in i en String[]
         String nodeLine = bReader.readLine();
         String[] nodeLineArray = nodeLine.split(";");

         for (int i = 0; i < nodeLineArray.length; i += 3) {
            graph.add(new Location(nodeLineArray[i], Double.parseDouble(nodeLineArray[i + 1]), Double.parseDouble(nodeLineArray[i + 2])));
         }

         Set<Node> nodeSet = graph.getNodes();
         String line;
         while ((line = bReader.readLine()) != null) {
            String[] nodeInfo = line.split(";");
            Node nodeFrom = null;
            Node nodeTo = null;
            String name = nodeInfo[2];
            int weight = Integer.parseInt(nodeInfo[3]);

            for (Node node : nodeSet) {
               if (node.getName().equals(nodeInfo[0].trim())) {
                  nodeFrom = node;
               } else if (node.getName().equals(nodeInfo[1].trim())) {
                  nodeTo = node;
               }
            }

            graph.connect(nodeFrom, nodeTo, name, weight);
         }

         //Därefter är varje line en connection, vi kan återigen dela upp infon i en String[]
         //namn på från-nod; namn på till-nod; edge namnet; vikt;
         //Hitta från-nod i graph genom att jämföra .getName()
         //Hitta till-noden på samma sätt
         //Använd connect för att sätta ihop dessa
         //Repetera tills filen är tom
      } catch (FileNotFoundException e) {
         System.err.println("File not found: " + fileName);
      } catch (IOException e) {
         e.printStackTrace();
      }
    }

    public SortedMap<Integer, SortedSet<Record>> getAlsoLiked(Record item) {
      SortedMap<Integer, SortedSet<Record>> result = new TreeMap<>();
      //Gå genom nodens edges
      //För varje person/node gå genom edges
      //Om noden inte existerar i temp lägg till den (computeIfAbsent()) tillsammans med resultatet från getPopularity
       return result;
    }

    public int getPopularity(Record item) {
      //Hämta en collection med edges från noden med hjälv av getEdgesFrom
      //Returnera antalet
	
       return graph.getEdgesFrom(item).size();
    }

    public SortedMap<Integer, Set<Record>> getTop5() {
      SortedMap<Integer, Set<Record>> result = new TreeMap<>();
      //Gå genom grafen
      //Om noden är en record lägg till med resultatet från getPopularity som key
      //Ta bort överflödiga noder 
      return result;
    }

    public void loadRecommendationGraph(String fileName) {
      try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
         String line;
         while ((line = bReader.readLine()) != null) {
            String[] nodeInfo = line.split(";");
            Person person = new Person(nodeInfo[0]);
            Record record = new Record(nodeInfo[1], nodeInfo[2]);
            graph.add(person);
            graph.add(record);

            graph.connect(person, record, null, 0);
         }
         //personNamn; titel på skiva; skivans artist;
         //Dela upp linen i en String[]
         //Skapa en ny Person och lägg in i graph
         //Skapa en ny Record och lägg in i graph
         //Repetera
         //Behöver nog ändra på edge-konstruktorn eftersom namnet och vikten ska vara blanka
      } catch (FileNotFoundException e) {
         System.err.println("FIle not found: " + fileName);
      } catch (IOException e) {
         e.printStackTrace();
      }
    }

}
