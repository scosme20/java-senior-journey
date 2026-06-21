import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    // Representa um funcionário de forma imutável.
    public record Employee(
            String id,
            String name,
            int age,
            String email,
            BigDecimal salary,      // Valor monetário
            Position position,
            LocalDate admissionDate // Data de admissão
    ) {

        // Regras básicas de validação.
        public Employee {

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            if (age < 18) {
                throw new IllegalArgumentException("Employee must be adult");
            }

            if (salary.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Salary cannot be negative");
            }

            if (!email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }

            if (position == null) {
                throw new IllegalArgumentException("Position cannot be null");
            }

            if (admissionDate == null || admissionDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Invalid admission date");
            }
        }
    }

    // Cargos disponíveis no sistema.
    enum Position {
        ADMIN,
        DEVELOPER,
        MANAGER
    }

    public static void main(String[] args) {

        Employee employee = new Employee(
                "2468b",
                "Carlos",
                31,
                "carlos@gmail.com",
                new BigDecimal("4500.00"),
                Position.ADMIN,
                LocalDate.of(2024, 1, 15)
        );

        System.out.println(employee.name());
        System.out.println(employee.position());
        System.out.println(employee.salary());
        System.out.println(employee.admissionDate());
    }
}