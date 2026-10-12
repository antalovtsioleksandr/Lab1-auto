import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ініціалізація тестових даних\n");

        Customer customer1 = new Customer(1L, "Славік Олександрович", "slavik@example.com");
        Customer customer2 = new Customer(2L, "Єва Олегівна", "evchikb@example.com");
        Customer customer3 = new Customer(3L, "Настя Михайлівна", "nastyamops@example.com");

        Product laptop = new Product(101L, "MacBook Pro", ProductCategory.ELECTRONICS, 2500.00);
        Product mouse = new Product(102L, "Бездротова миша", ProductCategory.ELECTRONICS, 50.00);
        Product tshirt = new Product(103L, "Футболка", ProductCategory.CLOTHING, 20.00);
        Product book = new Product(104L, "Край самоцвітів", ProductCategory.BOOKS, 45.00);
        Product lamp = new Product(105L, "Настільна лампа", ProductCategory.HOME, 35.00);

        Order order1 = new Order(1001L, customer1, Arrays.asList(laptop, mouse), OrderStatus.NEW);
        Order order2 = new Order(1002L, customer2, Arrays.asList(tshirt, lamp), OrderStatus.PROCESSING);
        Order order3 = new Order(1003L, customer1, Arrays.asList(book), OrderStatus.SHIPPED);
        Order order4 = new Order(1004L, customer3, Arrays.asList(laptop, tshirt, book), OrderStatus.DELIVERED);

        OrderService service = new OrderService();

        System.out.println("1. Додавання замовлень");
        service.addOrder(order1);
        service.addOrder(order2);
        service.addOrder(order3);
        service.addOrder(order4);
        service.printAllOrders();

        System.out.println("\n2. Пошук замовлення за ID");
        System.out.println("Шукаємо ID=1002: " + service.findOrderById(1002L));

        System.out.println("\n3. Пошук замовлень клієнта");
        System.out.println("Клієнт Славік:");
        service.findOrdersByCustomer(1L).forEach(System.out::println);

        System.out.println("\n4. Пошук за статусом");
        System.out.println("Статус DELIVERED:");
        service.findOrdersByStatus(OrderStatus.DELIVERED).forEach(System.out::println);

        System.out.println("\n5. Пошук за наявністю продукту");
        System.out.println("Продукт 'MacBook Pro':");
        service.findOrdersContainingProduct(101L).forEach(System.out::println);

        System.out.println("\n6. Розрахунок вартості замовлення");
        System.out.printf("Вартість замовлення ID=1004: $%.2f\n", service.calculateOrderTotal(1004L));

        System.out.println("\n7. Клієнти, що мають замовлення");
        service.findCustomersWithOrders().forEach(System.out::println);

        System.out.println("\n8. Сортування замовлень за вартістю");
        service.sortOrdersByTotalPrice().forEach(System.out::println);

        System.out.println("\n9. Підрахунок замовлень за статусами");
        service.countOrdersByStatus().forEach((status, count) ->
                System.out.println("Статус " + status + ": " + count + " шт.")
        );

        System.out.println("\n10. Видалення замовлення");
        System.out.println("Видаляємо замовлення ID=1001...");
        service.removeOrder(1001L);
        System.out.println("Замовлення після видалення:");
        service.printAllOrders();
    }
}
