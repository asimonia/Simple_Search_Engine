package search;

import java.util.HashSet;
import java.util.Set;

public class AnyMethod implements SearchStrategy {

    /*
If the strategy is ANY, the program should print lines containing at least one word from the query.
Query:

Erick Dwight webb@gmail.com
Result:

Erick Harrington harrington@gmail.com
Erick Burgess
Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
     */

    private final Dataset dataset;

    public AnyMethod(Dataset dataset) {
        this.dataset = dataset;
    }

    @Override
    public void search(String input) {

        String[] tokens = input.toUpperCase().split("\\s+");
        Set<Integer> indexes = new HashSet<>();

        for (String token : tokens) {
            if (dataset.getInvertedIndex().get(token) != null) {
                indexes.addAll(dataset.getInvertedIndex().get(token));
            }
        }

        if (!indexes.isEmpty()) {
            System.out.println(indexes.size() + " persons found:");
            for (int lineNum : indexes) {
                String[] line = dataset.getPeopleList().get(lineNum);
                System.out.println(String.join(" ", line));
            }
        } else {
            System.out.println("No matching people found.");
        }
    }
}
