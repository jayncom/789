import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPrimeForm extends JFrame {
    private JTextField numberField;
    private JButton checkButton;
    private JTextArea resultArea;
    private PrimeCheckInterface primeCheck;

    public ClientPrimeForm() {
        initializeUI();
        initializeRMI();
    }

    private void initializeUI() {
        setTitle("Prime Number Checker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        numberField = new JTextField();
        panel.add(numberField);

        checkButton = new JButton("Check Prime");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPrime();
            }
        });
        panel.add(checkButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        add(panel);
    }

    private void initializeRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            primeCheck = (PrimeCheckInterface) registry.lookup("PrimeCheck");
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo RMI: " + e.toString());
            e.printStackTrace();
        }
    }

    private void checkPrime() {
        try {
            String inputNumber = numberField.getText();
            long numberToCheck = Long.parseLong(inputNumber);

            boolean isPrime = primeCheck.isPrime(numberToCheck);
            if (isPrime) {
                resultArea.setText(numberToCheck + " là số nguyên tố.");
            } else {
                resultArea.setText(numberToCheck + " không phải là số nguyên tố.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Nhập một số nguyên hợp lệ.");
        } catch (Exception ex) {
            resultArea.setText("Lỗi khi kiểm tra số nguyên tố: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientPrimeForm form = new ClientPrimeForm();
                form.setVisible(true);
            }
        });
    }
}
