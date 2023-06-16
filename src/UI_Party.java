import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class UI_Party extends JPanel{
    public UI_Party(){
        setLayout(new FlowLayout());
        JButton logout = new JButton("로그아웃");

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_Login.logout();
                UI_Main.redraw_main_panel(new UI_Character());
            }
        });
        add(logout);
        if(Party.get_party_name() == null)
            add(new Not_exist_party_panel());
        else
            add(new Exist_party_panel());


    }
    private static class Exist_party_panel extends JPanel{
        public Exist_party_panel(){
            Party.getDay();
            ArrayList<String> members = Party.getMember();
            ArrayList<String> available_days = Party.getAvailable_day();

            JPanel panel = new JPanel();

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JButton exit_party = new JButton("파티 나가기");
            exit_party.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Party.exit_party();
                    UI_Main.redraw_main_panel(new UI_Party());
                }
            });
            panel.add(exit_party);

            JPanel checkbox_panel = new JPanel(new FlowLayout());

            JLabel id_label = new JLabel(UI_Login.getUser().getId());
            checkbox_panel.add(id_label);
            int user_index = 0;
            for(int i = 0; i < members.size(); i++)
                if(members.get(i).equals(UI_Login.getUser().getId()))
                    user_index = i;

            JCheckBox[] days_checkbox = new JCheckBox[7];
            String[] week = {":수", ":목", ":금", ":토", ":일", ":월", ":화"};
            char[] user_available_day = available_days.get(user_index).toCharArray();
            //debug
            //System.out.println(members.get(user_index) + " : " + new String(user_available_day));

            for(int i = 0; i < 7; i++){
                days_checkbox[i] = new JCheckBox(week[i],
                        (user_available_day[i] == '1'));
                checkbox_panel.add(days_checkbox[i]);

            }

            JButton apply_button = new JButton("업로드");
            apply_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder day = new StringBuilder(7);
                    for(JCheckBox jCheckBox : days_checkbox){
                        if(jCheckBox.isSelected())
                            day.append("1");
                        else
                            day.append("0");
                    }
                    Party.setDay(day.toString());
                    JOptionPane.showMessageDialog(null,
                            "일정이 변경되었습니다.",
                            "일정 변경 완료",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            checkbox_panel.add(apply_button);

            panel.add(checkbox_panel);

            for(int i = 0; i < members.size(); i++){
                if(members.get(i).equals(UI_Login.getUser().getId()))
                    continue;
                JPanel member_info_panel = new JPanel(new FlowLayout());
                JLabel member_id = new JLabel(members.get(i));
                member_info_panel.add(member_id);

                JCheckBox[] member_checkboxs = new JCheckBox[7];

                char[] available_day = available_days.get(i).toCharArray();

                //System.out.println(members.get(i) + " : " + new String(available_day));
                for(int j = 0; j < 7; j++){
                    member_checkboxs[j] = new JCheckBox(week[j],
                            (available_day[j] == '1'));
                    member_checkboxs[j].setEnabled(false);
                    member_info_panel.add(member_checkboxs[j]);
                }
                panel.add(member_info_panel);
            }



            add(panel);
        }
    }
    private static class Not_exist_party_panel extends JPanel{

        public Not_exist_party_panel(){
            setLayout(new FlowLayout());

            JButton create_party = new JButton("파티 생성");
            JButton join_party = new JButton("파티 참가");

            create_party.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String party_name = JOptionPane.showInputDialog(null, "파티명 입력", null);
                    if(Party.create_party(party_name) == -1)
                        JOptionPane.showMessageDialog(null,
                                "이미 존재하는 파티명입니다.",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                    UI_Main.redraw_main_panel(new UI_Party());
                }
            });
            join_party.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String party_name = JOptionPane.showInputDialog(null, "파티명 입력", null);
                    if(Party.join_party(party_name) == -1)
                        JOptionPane.showMessageDialog(null,
                                "존재하지 않는 파티명입니다.",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                    UI_Main.redraw_main_panel(new UI_Party());

                }
            });

            add(create_party);
            add(join_party);
        }
    }

}
