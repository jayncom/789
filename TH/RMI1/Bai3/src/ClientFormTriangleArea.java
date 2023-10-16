import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientFormTriangleArea extends JFrame {
    private JTextField sideAField;
    private JTextField sideBField;
    private JTextField sideCField;
    private JButton calculateButton;
    private JTextArea resultArea;
    private TriangleAreaInterface triangleArea;

    public ClientFormTriangleArea() {
        initializeUI();
        initializeRMI();
    }

    private void initializeUI() {
        setTitle("Triangle Area Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Cạnh a:"));
        sideAField = new JTextField();
        panel.add(sideAField);

        panel.add(new JLabel("Cạnh b:"));
        sideBField = new JTextField();
        panel.add(sideBField);

        panel.add(new JLabel("Cạnh c:"));
        sideCField = new JTextField();
        panel.add(sideCField);

        calculateButton = new JButton("Tính diện tích");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTriangleArea();
            }
        });
        panel.add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        add(panel);
    }

    private void initializeRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            triangleArea = (TriangleAreaInterface) registry.lookup("TriangleArea");
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo RMI: " + e.toString());
            e.printStackTrace();
        }
    }

    private void calculateTriangleArea() {
        try {
            double sideA = Double.parseDouble(sideAField.getText());
            double sideB = Double.parseDouble(sideBField.getText());
            double sideC = Double.parseDouble(sideCField.getText());

            double area = triangleArea.calculateArea(sideA, sideB, sideC);
            resultArea.setText("Diện tích tam giác: " + area);
        } catch (NumberFormatException ex) {
            resultArea.setText("Nhập các cạnh tam giác hợp lệ.");
        } catch (Exception ex) {
            resultArea.setText("Lỗi khi tính diện tích tam giác: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientFormTriangleArea form = new ClientFormTriangleArea();
                form.setVisible(true);
            }
        });
    }
}
