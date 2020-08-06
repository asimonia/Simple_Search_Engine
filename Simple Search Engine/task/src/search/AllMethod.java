package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllMethod implements SearchStrategy {
    /*
If the strategy is ALL, the program should print lines containing all words from the query.
Query:

Harrington Erick
Result:

Erick Harrington harrington@gmail.com
     */

    private Dataset dataset;

    public AllMethod(Dataset dataset) {
        this.dataset = dataset;
    }

    @Override
    public void search(String input) {
        String[] tokens = input.toUpperCase().split("\\s+");
        List<Integer> indexes = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        List<Integer> dupes = new ArrayList<>();

        for (String token : tokens) {
            if (dataset.getInvertedIndex().get(token) != null) {
                indexes.addAll(dataset.getInvertedIndex().get(token));
            }
        }

        for (Integer index : indexes) {
            if (!set.add(index)) {
                dupes.add(index);
            }
        }

        if (!dupes.isEmpty()) {
            System.out.println(dupes.size() + " persons found:");
            for (int lineNum : dupes) {
                String[] line = dataset.getPeopleList().get(lineNum);
                System.out.println(String.join(" ", line));
            }
        } else {
            System.out.println("No matching people found.");
        }
    }
}
