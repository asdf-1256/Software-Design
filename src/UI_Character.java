import jdk.nashorn.internal.scripts.JD;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
public class UI_Character extends JPanel{
    /*
    private static ArrayList<Character> characters = null;
    private static JPanel button_panel = new JPanel();;
    private static ArrayList<JButton> button_list = new ArrayList<>();;
    public UI_Character() {
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
        JLabel character_job = new JLabel("character job");
        JLabel character_name = new JLabel("character name");
        JLabel character_level = new JLabel("character level");

        character_button.add(character_job);
        character_button.add(character_name);
        character_button.add(character_level);
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
    }*/

    private static JTextField character_name_field = new JTextField(13);
    public UI_Character(){
        setLayout(new FlowLayout());

        add(character_name_field);
        JButton search_button = new JButton("검색");
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Character character = new Character(character_name_field.getText());
                if(character.getName() == null){
                    JOptionPane.showMessageDialog(null,
                            "캐릭터가 존재하지 않습니다",
                            "Not Found Character",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new resultDialog((JFrame)getTopLevelAncestor(), character);
            }
        });

        add(search_button);
    }
    private static class resultDialog extends JDialog{
        public resultDialog(JFrame parent, Character character){
            super(parent, "캐릭터 조회", true);

            setSize(1000,1000);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            JLabel character_name = new JLabel("캐릭터명 : " + character.getName());
            JLabel character_job = new JLabel("직업 : " + character.getJob());
            JLabel character_level = new JLabel("캐릭터 레벨 : " + character.getItem_level());

            add(character_name);
            add(character_job);
            add(character_level);

            ArrayList<Engrave> engraves = character.getEngraves();

            for(Engrave engraving : engraves){

                JLabel engraving_name = new JLabel(engraving.getName());
                engraving_name.setToolTipText(engraving.getDescription());

                add(engraving_name);
            }
            ToolTipManager.sharedInstance().setInitialDelay(0);

            JButton exit_button = new JButton("닫기");
            exit_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(exit_button);
            setVisible(true);
        }
    }
}
