import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class UI_Engraving extends JPanel {
    private static JPanel engraving_pannel = new JPanel();
    private static JTextField API_field = new JTextField(10);
    private static final String[] engravings = {"각성","강령술","강화 방패","결투의 대가","구슬동자",
            "굳은 의지","급소 타격","기습의 대가","긴급구조","달인의 저력","돌격대장",
            "마나의 흐름","마나 효율 증가","바리케이드","번개의 분노","부러진 뼈","분쇄의 주먹",
            "불굴","선수필승","속전속결","슈퍼 차지","승부사","시선 집중","실드 관통","아드레날린",
            "안정된 상태","약자 무시","여신의 가호","에테르 포식자","예리한 둔기","원한","위기 모면",
            "저주받은 인형","전문의","정기 흡수","정밀 단도","중갑 착용","질량 증가","최대 마나 증가",
            "추진력","타격의 대가","탈출의 명수","폭발물 전문가","광기","광전사의 비기","전투 태세",
            "고독한 기사","분노의 망치","중력 수련","심판자","축복의 오라","처단자","포식자","오의 강화",
            "초심","극의 : 체술","충격 단련","역천지체","세맥타통","절정","절제","일격필살","오의난무",
            "강화 무기","핸드거너","화력 강화","포격 강화","죽음의 습격","두 번째 동료","아르데타인의 기술",
            "진화의 유산","사냥의 시간","피스메이커","절실한 구원","진실된 용맹","넘치는 교감","상급 소환사",
            "황후의 은총","황제의 칙령","점화","환류","잔재된 기운","버스트","멈출 수 없는 충동","완벽한 억제",
            "갈증","달의 소리","만개","회귀","질풍노도","이슬비"};
    private static final String[] debuff = {"공격력 감소", "공격속도 감소", "방어력 감소", "이동속도 감소"};
    private static final Integer[] engraving_levels = {0, 1, 2, 3};
    private static final Integer[] engraving_books = {3, 6, 9, 12};
    private static final String[] combat_effect = {"치명", "특화", "신속", "제압", "인내", "숙련"};
    private static ArrayList<JComboBox> target_engraving_names = new ArrayList<>();
    private static ArrayList<JComboBox> target_engraving_levels = new ArrayList<>();
    private static ArrayList<JComboBox> equip_engraving_names = new ArrayList<>();
    private static ArrayList<JComboBox> equip_engraving_levels = new ArrayList<>();

    private static ArrayList<JComboBox> ability_stone_names = new ArrayList<>();
    private static ArrayList<JTextField> ability_stone_levels = new ArrayList<>();
    private static ArrayList<JTextField> qualitys = new ArrayList<>();
    private static ArrayList<JComboBox> combat_effects = new ArrayList<>();

    public UI_Engraving(){
        engraving_pannel.setLayout(new BorderLayout());
        JPanel api_panel = new JPanel();
        api_panel.setLayout(new FlowLayout());

        JButton save_button = new JButton("API 키 저장");

        api_panel.add(API_field);
        api_panel.add(save_button);

        engraving_pannel.add(api_panel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel target_panel = new JPanel(new GridBagLayout());

        for(int i = 0; i < 6; i++){
            JPanel panel = new JPanel(new FlowLayout());
            JComboBox<String> target_engraving_name = new JComboBox<>(engravings);
            JComboBox<Integer> target_engraving_level = new JComboBox<>(engraving_levels);

            panel.add(new JLabel("목표 각인" + (i + 1)));
            panel.add(target_engraving_name);
            panel.add(new JLabel("목표 레벨"));
            panel.add(target_engraving_level);

            target_engraving_names.add(target_engraving_name);
            target_engraving_levels.add(target_engraving_level);

            target_panel.add(panel, gbc);
            gbc.gridy++;
        }
        engraving_pannel.add(target_panel, BorderLayout.WEST);




        JPanel equip_panel = new JPanel(new GridBagLayout());
        gbc.gridy = 0;
        for(int i = 0; i < 2; i++){
            JPanel panel = new JPanel(new FlowLayout());
            JComboBox<String> equip_engraving_name = new JComboBox<>(engravings);
            JComboBox<Integer> equip_engraving_level = new JComboBox<>(engraving_books);

            panel.add(new JLabel("각인서" + (i + 1)));
            panel.add(equip_engraving_name);
            panel.add(new JLabel("각인 수치"));
            panel.add(equip_engraving_level);

            equip_engraving_names.add(equip_engraving_name);
            equip_engraving_levels.add(equip_engraving_level);

            equip_panel.add(panel, gbc);
            gbc.gridy++;
        }

        engraving_pannel.add(new JLabel("어빌리티 스톤"));
        gbc.gridy++;

        for(int i = 0; i < 2; i++){
            JPanel panel = new JPanel(new FlowLayout());
            JComboBox<String> equip_engraving_name = new JComboBox<>(engravings);
            JTextField textField = new JTextField(3);

            panel.add(new JLabel("증가 각인" + (i + 1)));
            panel.add(equip_engraving_name);
            panel.add(new JLabel("각인 수치"));
            panel.add(textField);

            ability_stone_names.add(equip_engraving_name);
            ability_stone_levels.add(textField);

            equip_panel.add(panel, gbc);
            gbc.gridy++;
        }
        JPanel debuff_panel = new JPanel(new FlowLayout());
        JComboBox<String> equip_debuff_name = new JComboBox<>(debuff);
        JTextField textField = new JTextField(3);

        debuff_panel.add(new JLabel("감소 각인"));
        debuff_panel.add(equip_debuff_name);
        debuff_panel.add(new JLabel("각인 수치"));
        debuff_panel.add(textField);

        ability_stone_names.add(equip_debuff_name);
        ability_stone_levels.add(textField);

        equip_panel.add(debuff_panel, gbc);

        engraving_pannel.add(equip_panel, BorderLayout.CENTER);

        gbc.gridy = 0;
        JPanel quality_panel = new JPanel(new GridBagLayout());
        String[] accessory = {"목걸이", "귀걸이1", "귀걸이2", "반지1", "반지2"};
        for(int i = 0; i < 5; i++){
            JPanel panel = new JPanel(new FlowLayout());
            JTextField quality = new JTextField(3);

            if(i == 0){
                JComboBox<String> combat_effect_box1 = new JComboBox<>(combat_effect);
                JComboBox<String> combat_effect_box2 = new JComboBox<>(combat_effect);

                panel.add(new JLabel(accessory[i]));
                panel.add(new JLabel("품질"));
                panel.add(quality);
                panel.add(new JLabel("특성"));
                panel.add(combat_effect_box1);
                panel.add(combat_effect_box2);

                combat_effects.add(combat_effect_box1);
                combat_effects.add(combat_effect_box2);
            }
            else{
                JComboBox<String> combat_effect_box = new JComboBox<>(combat_effect);

                panel.add(new JLabel(accessory[i]));
                panel.add(new JLabel("품질"));
                panel.add(quality);
                panel.add(new JLabel("특성"));
                panel.add(combat_effect_box);

                combat_effects.add(combat_effect_box);
            }
            quality_panel.add(panel, gbc);
            gbc.gridy++;
        }
        engraving_pannel.add(quality_panel, BorderLayout.EAST);



        JButton calculate_button = new JButton("계산하기");
        calculate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UI의 combobox와 textfield를 인자로 전달받아야함.
                ArrayList<Accessory> result = Engraving.getResult();
            }
        });
        engraving_pannel.add(calculate_button, BorderLayout.SOUTH);

        add(engraving_pannel);


    }
}
