import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private final List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            prefix.clear();
            prefix.add(1);
        } else {
            int lastProduct = prefix.get(prefix.size() - 1);
            prefix.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size() - 1;
        if (k > n) {
            return 0;
        }
        int totalProduct = prefix.get(n);
        int divisor = prefix.get(n - k);
        return totalProduct / divisor;
    }
}
