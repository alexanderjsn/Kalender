import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GUI extends JFrame {
    public GUI() {
        JFrame frame = new JFrame("Kalender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1800, 2200);
        frame.setVisible(true);


        // Container för labels så att de är i en grid av 1,7
        JPanel ContainerDates = new JPanel();
        ContainerDates.setLayout(new GridLayout(1, 7));


        // Dagens datum
        LocalDate today = LocalDate.now();
        // hämtar veckodagen av datumet (16e = torsdag - värde 4(-1))
        DayOfWeek currentDay = today.getDayOfWeek();

        // tar dagens datum och subtraherar med förgående dagar i veckan/3 dagar ( minus dagens datum ) för att hämta måndag
        LocalDate Monday = today.minusDays(currentDay.getValue() - 1);


        //loop för 7 dagar i veckan som slutar vid söndag
        for (int i = 0; i < 7; i++) {
            LocalDate dates = Monday.plusDays(i);
            // hämtar ut datumens format i String så det kan sättas i en label
            String formattedDate = dates.format(DateTimeFormatter.ofPattern("EEEE MMM dd"));
            // skapar en label till containern ( 7 )
            JLabel dayLabel = new JLabel(formattedDate);
            // sätter fonts
            dayLabel.setFont(new Font("Arial", Font.BOLD, 15));
            // lägger till en label för varje loop
            ContainerDates.add(dayLabel);
            if (dates.equals(today)){
                dayLabel.setText(formattedDate + " (today)");
                dayLabel.setForeground(Color.decode("#75B09C"));
            }

        }

        //lägger till container för alla dagar i frame
        frame.add(ContainerDates, BorderLayout.NORTH);


        //container för paneler
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new FlowLayout());

        // skapar sju paneler från metoden
        for (int i = 0; i < 7; i++){
            containerPanel.add(createPanel());
        }
        // lägger till alla skapade paneler i containern
        frame.add(containerPanel, BorderLayout.CENTER);
    }

    //Metod som skapar sju paneler
    private static JPanel createPanel() {

        //yttre panel
        JPanel yttrePanel = new JPanel();
        yttrePanel.setLayout(new FlowLayout());
        //knapp
        JButton button = new JButton("Lägg till aktivitet");
        button.setPreferredSize(new Dimension(150,20));
        button.setBackground(Color.decode("#A0CA92"));
        //textfield
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150,20));

        //lägger till knapp, textfield
        yttrePanel.add(textField);
        yttrePanel.add(button);


        //panel egenskaper
        yttrePanel.setPreferredSize(new Dimension(200, 750));
        yttrePanel.setBackground(Color.decode("#75B09C"));
        yttrePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#E2E4D8"), 2));

        // inre panel där text läggs på
        JPanel inrePanel = new JPanel();
        inrePanel.setBackground(Color.decode("#DDE6DD"));
        inrePanel.setPreferredSize(new Dimension(200,1150));
        inrePanel.setLayout(new BoxLayout(inrePanel, BoxLayout.Y_AXIS));
        yttrePanel.add(inrePanel);


        //action listener som lägger till användarens input inuti den imbäddade panelen
        button.addActionListener(e -> {
            // skapar en string med texten som skrivs in, hämtar textfield med getter metod
            String newText = textField.getText();
            // skapar en label med String texten
            JLabel newEvent = new JLabel(newText);
            newEvent.setFont(new Font("Arial", Font.BOLD, 20));
            // lägger till labeln på panelen
            inrePanel.add(newEvent);
            // berättar för UI att uppdatera panelen
            inrePanel.revalidate();

        });
        return yttrePanel;
    }
}
