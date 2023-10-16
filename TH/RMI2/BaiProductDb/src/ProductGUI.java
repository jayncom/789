import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ProductGUI {
    private JFrame frame;
    private JTextField productCodeField;
    private JTextArea resultArea;
    private ProductInterface stub;

    public ProductGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblProductCode = new JLabel("Product Code:");
        lblProductCode.setBounds(20, 30, 100, 20);
        frame.getContentPane().add(lblProductCode);

        productCodeField = new JTextField();
        productCodeField.setBounds(120, 30, 150, 20);
        frame.getContentPane().add(productCodeField);
        productCodeField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(280, 30, 80, 20);
        frame.getContentPane().add(btnSearch);

        // Use JScrollPane to allow resizing of resultArea
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 70, 340, 180);
        frame.getContentPane().add(scrollPane);

        resultArea = new JTextArea();
        scrollPane.setViewportView(resultArea);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });
    }

    private void searchProduct() {
        String productCode = productCodeField.getText();
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1066);
            stub = (ProductInterface) registry.lookup("ProductLookup");

            List<Product> products = stub.findProductByCode(productCode);
            resultArea.setText("");
            for (Product product : products) {
                resultArea.append(product.toString() + "\n");
            }
        } catch (Exception e) {
            resultArea.setText("Error: " + e.toString());
            e.printStackTrace();
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ProductGUI productGUI = new ProductGUI();
        productGUI.display();
    }
}
