import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;
public class UI_Character extends JPanel{

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
