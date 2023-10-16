import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class CustomerGUI {
    private JTextField customerCodeField;
    private JButton searchButton;
    private JTextArea resultTextArea;
    private JPanel mainPanel;

    public CustomerGUI() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerCode = customerCodeField.getText();

                try {
                    Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1088);
                    CustomerLookupInterface customerLookup = (CustomerLookupInterface) registry
                            .lookup("CustomerLookup");

                    List<Customer> customers = customerLookup.findCustomerByCode(customerCode);

                    if (customers.isEmpty()) {
                        resultTextArea.setText("Không tìm thấy khách hàng với mã " + customerCode);
                    } else {
                        StringBuilder result = new StringBuilder("Thông tin khách hàng:\n");
                        for (Customer customer : customers) {
                            result.append(customer.toString()).append("\n");
                        }
                        resultTextArea.setText(result.toString());
                    }
                } catch (Exception ex) {
                    resultTextArea.setText("Lỗi khi tìm kiếm khách hàng: " + ex.toString());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Customer Search");
        frame.setContentPane(new CustomerGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
