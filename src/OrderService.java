import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(long orderId) {
        orders.removeIf(order -> order.getId() == orderId);
    }

    public Order findOrderById(long orderId) {
        return orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public List<Order> findOrdersByCustomer(long customerId) {
        return orders.stream()
                .filter(order -> order.getCustomer().getId() == customerId)
                .collect(Collectors.toList());
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.stream()
                .filter(order -> order.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Order> findOrdersContainingProduct(long productId) {
        return orders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getId() == productId))
                .collect(Collectors.toList());
    }

    public double calculateOrderTotal(long orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            return order.getTotalPrice();
        }
        System.out.println("Замовлення з ID " + orderId + " не знайдено.");
        return 0.0;
    }

    public Set<Customer> findCustomersWithOrders() {
        return orders.stream()
                .map(Order::getCustomer)
                .collect(Collectors.toSet());
    }

    public List<Order> sortOrdersByTotalPrice() {
        return orders.stream()
                .sorted(Comparator.comparingDouble(Order::getTotalPrice))
                .collect(Collectors.toList());
    }

    public Map<OrderStatus, Long> countOrdersByStatus() {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
    }

    public void printAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Список замовлень порожній.");
        } else {
            orders.forEach(System.out::println);
        }
    }
}
