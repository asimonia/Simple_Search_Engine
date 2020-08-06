package search;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private final Dataset dataset;


    public Menu(Dataset dataset) {
        this.dataset = dataset;
    }

    public void start() {

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");

            String item = scanner.nextLine();

            switch (item) {
                case "1":

                    System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();

                    System.out.println("\nEnter a name or email to search all suitable people.");
                    String input = scanner.nextLine();

                    SearchContext searchContext = new SearchContext();

                    switch (strategy) {
                        case "ALL":
                            searchContext.setMethod(new AllMethod(dataset));
                            searchContext.search(input);
                            break;
                        case "ANY":
                            searchContext.setMethod(new AnyMethod(dataset));
                            searchContext.search(input);
                            break;
                        case "NONE":
                            searchContext.setMethod(new NoneMethod(dataset));
                            searchContext.search(input);
                            break;
                    }

                    break;
                case "2":
                    System.out.println("=== List of people ===");

                    for (String[] record : dataset.getPeopleList()) {
                        System.out.println(String.join(" ", record));
                    }
                    break;
                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Incorrect option! Try again");
            }
        }

    }
}
