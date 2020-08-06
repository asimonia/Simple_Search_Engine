package search;

public class SearchContext {

    private SearchStrategy method;

    public void setMethod(SearchStrategy method) {
        this.method = method;
    }

    public void search(String input) {
        this.method.search(input);
    }
}
