import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class UI_Party extends JPanel{
    private static ArrayList<Character> characters = null;
    private static JPanel button_panel = new JPanel();;
    private static ArrayList<JButton> button_list = new ArrayList<>();;
    public UI_Party() {
        //characters의 텍스트 파일을 읽어들여 첫 세팅하는 코드가 포함-Character 클래스에 구현?

        button_panel.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(button_panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(900, 800));
        JButton add_button = new JButton("+");
        add_button.setPreferredSize(new Dimension(900,100));

        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_character();
                button_list.remove(add_button);
                button_list.add(add_button);
                set_button_panel();
            }
        });
        if(button_list.size() == 0)
            button_list.add(add_button);
        set_button_panel();


        add(scrollPane);


    }
    private void add_character(){
        JButton character_button = new JButton();
        character_button.setLayout(new GridLayout(1, 3));
        JLabel party_num = new JLabel("party num");
        JLabel party_name = new JLabel("party name");

        character_button.add(party_num);
        character_button.add(party_name);
        character_button.setPreferredSize(new Dimension(900,100));

        button_list.add(character_button);
    }
    private void set_button_panel(){
        button_panel.removeAll();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        for(JButton button : button_list){
            button_panel.add(button, gridBagConstraints);
            gridBagConstraints.gridy++;
        }
        button_panel.revalidate();
        button_panel.repaint();
    }
}
