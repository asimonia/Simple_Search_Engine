package search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Dataset {

    private final List<String[]> peopleList = new ArrayList<>();
    private final Map<String, List<Integer>> invertedIndex = new HashMap<>();

    public void enter(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                addPerson(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(System.getProperty("user.dir"));
            e.printStackTrace();
        }
        createIndex();
    }

    public void addPerson(String lineToParse) {
        String[] record = lineToParse.split("\\s+");
        peopleList.add(record);
    }

    public void createIndex() {
        int k = 0;

        for (String[] record : peopleList) {
            for (String word : record) {
                List<Integer> list;
                if (!invertedIndex.containsKey(word.toUpperCase())) {
                    list = new ArrayList<>();
                } else {
                    list = invertedIndex.get(word.toUpperCase());
                }
                list.add(k);
                invertedIndex.put(word.toUpperCase(), list);
            }
            k++;
        }
    }

    public List<String[]> getPeopleList() {
        return peopleList;
    }

    public Map<String, List<Integer>> getInvertedIndex() {
        return invertedIndex;
    }
}
