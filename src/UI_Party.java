import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class UI_Party extends JPanel{
    /*
    private static ArrayList<Character> characters = null;
    private static JPanel button_panel = new JPanel();
    private static ArrayList<JButton> button_list = new ArrayList<>();
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
    }*/

    public UI_Party(){
        setLayout(new FlowLayout());
        JButton logout = new JButton("로그아웃");

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(logout);

        add(new Exist_party_panel());
        add(new Not_exist_party_panel());

    }
    private static class Exist_party_panel extends JPanel{
        public Exist_party_panel(){
            JButton exit_party = new JButton("파티 나가기");
            exit_party.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            add(exit_party);
        }
    }
    private static class Not_exist_party_panel extends JPanel{
        private static final int PARTY_CREATE = 0;
        private static final int PARTY_JOIN = 0;

        public Not_exist_party_panel(){
            setLayout(new FlowLayout());

            JButton create_party = new JButton("파티 생성");
            JButton join_party = new JButton("파티 참가");

            create_party.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Party_Dialog((JFrame) getTopLevelAncestor(), PARTY_CREATE);
                }
            });
            join_party.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Party_Dialog((JFrame) getTopLevelAncestor(), PARTY_JOIN);
                }
            });

            add(create_party);
            add(join_party);
        }
        //따로 dialog만들지 말고 바로 그냥 변수에 리턴시킬 수 있도록 하는 방법 찾아보기
        private class Party_Dialog extends JDialog{
            private JTextField party_field;

            public Party_Dialog(JFrame parent, int flag){
                super(parent, "파티", true);
                party_field = new JTextField(15);

                setSize(500, 500);

                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

                add(party_field);

                if(flag == PARTY_CREATE){
                    JButton create_button = new JButton("생성");
                    create_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    add(create_button);
                }
                else if(flag == PARTY_JOIN){
                    JButton join_button = new JButton("참가");
                    join_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    add(join_button);
                }

                setVisible(true);
            }
        }

    }

}
