import java.awt.*;
import javax.swing.*;
public class UI_Main extends JFrame {
    private JPanel main_panel = null;
    private JPanel button_panel = null;
    public UI_Main(){

        main_panel = new JPanel(new BorderLayout());

        JButton character_button = new JButton("Character");
        JButton engraving_button = new JButton("Engraving");
        JButton laid_button = new JButton("Laid");

        character_button.addActionListener(e -> redraw_main_panel(new UI_Party()));
        engraving_button.addActionListener(e -> redraw_main_panel(new UI_Engraving()));
        laid_button.addActionListener(e -> redraw_main_panel(new UI_Login()));

        button_panel = new JPanel(new FlowLayout());
        button_panel.add(character_button);
        button_panel.add(engraving_button);
        button_panel.add(laid_button);

        main_panel.add(button_panel,BorderLayout.SOUTH);
        main_panel.add(new UI_Party(), BorderLayout.CENTER);

        add(main_panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1024, 900));
        setVisible(true);
        setResizable(false);
    }
    public static void main(String[]args){
        new UI_Main();
    }
    private void redraw_main_panel(JPanel panel){
        main_panel.removeAll();
        main_panel.add(button_panel, BorderLayout.SOUTH);
        main_panel.add(panel, BorderLayout.CENTER);
        main_panel.revalidate();
        main_panel.repaint();
        return;
    }
}
