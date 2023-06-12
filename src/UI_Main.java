import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;
public class UI_Main extends JFrame {
    private static JPanel main_panel = null;
    private static JPanel button_panel = null;
    public UI_Main(){

        Font font = UIManager.getFont("Label.font");
        font = font.deriveFont(20.0f);
        UIManager.put("Label.font", font);

        font = UIManager.getFont("Button.font");
        font = font.deriveFont(14.0f);
        UIManager.put("Button.font", font);

        font = UIManager.getFont("ComboBox.font");
        font = font.deriveFont(14.0f);
        UIManager.put("ComboBox.font", font);
        font = UIManager.getFont("TextField.font");
        font = font.deriveFont(14.0f);
        UIManager.put("TextField.font", font);

        main_panel = new JPanel(new BorderLayout());

        JButton character_button = new JButton("Character");
        JButton engraving_button = new JButton("Engraving");
        JButton laid_button = new JButton("Laid");

        character_button.addActionListener(e -> redraw_main_panel(new UI_Character()));
        engraving_button.addActionListener(e -> redraw_main_panel(new UI_Engraving()));
        laid_button.addActionListener(e -> {
            if(!UI_Login.getLoginstate()) {
                try {
                    redraw_main_panel(new UI_Login());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else
                redraw_main_panel(new UI_Party());
        });

        button_panel = new JPanel(new FlowLayout());
        button_panel.add(character_button);
        button_panel.add(engraving_button);
        button_panel.add(laid_button);

        main_panel.add(button_panel,BorderLayout.SOUTH);
        main_panel.add(new UI_Character(), BorderLayout.CENTER);

        add(main_panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1024, 900));
        setVisible(true);
        setResizable(true);
    }
    public static void main(String[]args){
        new UI_Main();
    }
    public static void redraw_main_panel(JPanel panel){
        main_panel.removeAll();
        main_panel.add(button_panel, BorderLayout.SOUTH);
        main_panel.add(panel, BorderLayout.CENTER);
        main_panel.revalidate();
        main_panel.repaint();
        return;
    }
}
