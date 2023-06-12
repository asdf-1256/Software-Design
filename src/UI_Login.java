import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
public class UI_Login extends JPanel {
    private static boolean isLogined = false;
    private static User user = null;

    private static JTextField id_field;
    private static JPasswordField pw_field;

    public static boolean getLoginstate(){
        return isLogined;
    }
    public static User getUser(){
        return user;
    }
    public static void logout(){
        user = null;
    }
    public UI_Login() throws SQLException{
        setLayout(new BorderLayout());

        JPanel id_pw_panel = new JPanel();
        id_pw_panel.setLayout(new BoxLayout(id_pw_panel, BoxLayout.Y_AXIS));

        id_field = new JTextField(15);
        pw_field = new JPasswordField(15);

        JPanel id_panel = new JPanel(new FlowLayout());
        JPanel pw_panel = new JPanel(new FlowLayout());

        id_panel.add(new JLabel("ID : "));
        id_panel.add(id_field);

        pw_panel.add(new JLabel("PW : "));
        pw_panel.add(pw_field);

        id_pw_panel.add(id_panel);
        id_pw_panel.add(pw_panel);

        add(id_pw_panel, BorderLayout.CENTER);

        JButton login = new JButton("로그인");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //로그인 성공시 정보 바꾸기
                //다시 그리는건 Main
                //로그인 실패시 modal 팝업 띄우는 과정을 거침
                User result = null;
                try {
                    result = Login.login(id_field.getText(), new String(pw_field.getPassword()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(result == null)
                    JOptionPane.showMessageDialog(null,
                            "ID 혹은 비밀번호가 잘못됨",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                else{
                    //로그인 성공하면 성공했다는 안내창이 뜨도록 해보기
                    user = result;
                    isLogined = true;
                    UI_Main.redraw_main_panel(new UI_Party());
                }
            }
        });

        add(login, BorderLayout.EAST);

        JButton signup = new JButton("회원가입");
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignupDialog((JFrame) getTopLevelAncestor());
            }
        });
        add(signup, BorderLayout.SOUTH);
    }
    private static class SignupDialog extends JDialog{
        private JTextField id_field;
        private JPasswordField pw_field;
        //private LoginPanel parent_panel;

        public SignupDialog(JFrame parent){
            super(parent, "회원가입", true);
            //this.parent_panel = parent_panel;

            setSize(500, 500);

            setLayout(new BorderLayout());

            JPanel id_pw_panel = new JPanel();
            id_pw_panel.setLayout(new BoxLayout(id_pw_panel, BoxLayout.Y_AXIS));

            id_field = new JTextField(15);
            pw_field = new JPasswordField(15);

            JPanel id_panel = new JPanel(new FlowLayout());
            JPanel pw_panel = new JPanel(new FlowLayout());

            id_panel.add(new JLabel("ID : "));
            id_panel.add(id_field);

            pw_panel.add(new JLabel("PW : "));
            pw_panel.add(pw_field);

            id_pw_panel.add(id_panel);
            id_pw_panel.add(pw_panel);

            add(id_pw_panel, BorderLayout.CENTER);

            JButton signup = new JButton("회원가입");
            signup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(Login.existUser(id_field.getText()))
                           JOptionPane.showMessageDialog(null,
                                   "해당 id가 이미 존재합니다",
                                   "signup failed",
                                   JOptionPane.ERROR_MESSAGE);
                        else {
                            Login.signup(id_field.getText(), new String(pw_field.getPassword()));
                            dispose();
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            add(signup, BorderLayout.EAST);

            setVisible(true);
        }
    }
}
