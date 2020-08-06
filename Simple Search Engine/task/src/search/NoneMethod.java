package search;

import java.util.HashSet;
import java.util.Set;

public class NoneMethod implements SearchStrategy {

/*
If the strategy is NONE, the program should print lines that do not contain words from the query at all:
Query:

djo@gmail.com ERICK
Result:

Katie Jacobs
Myrtle Medina
Rene Webb webb@gmail.com
 */
    private Dataset dataset;

    public NoneMethod(Dataset dataset) {
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

        // count the list
        int count = dataset.getPeopleList().size() - indexes.size();

        if (count != 0) {
            System.out.println(count + " persons found:");
            for (int i = 0; i < dataset.getPeopleList().size(); i++) {
                if (!indexes.contains(i)) {
                    System.out.println(String.join(" ", dataset.getPeopleList().get(i)));
                }
            }
        } else {
            System.out.println("No matching people found.");
        }

    }
}
