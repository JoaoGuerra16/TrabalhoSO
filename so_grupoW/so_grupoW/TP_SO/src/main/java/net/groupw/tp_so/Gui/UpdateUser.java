package net.groupw.tp_so.Gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import net.groupw.tp_so.Files.Log;
import net.groupw.tp_so.Files.UserCRUD;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class UpdateUser extends JFrame {
    private ArrayList<Log> logs;
    private JPanel UpdateUserPanel;
    private JTextField nameField;
    private JTextField newNameField;
    private JPasswordField newPasswordField;
    private JButton updateButton;
    private JComboBox newRoleField;
    private JLabel imageLogo;

    public UpdateUser(ArrayList<Log> logs) {
        setContentPane(UpdateUserPanel);
        setTitle("Update");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon icon = new ImageIcon("src/main/java/net/groupw/tp_so/assets/logo.png");
        setIconImage(icon.getImage());
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performUpdateUser();
            }
        });

    }

    public void performUpdateUser() {
        String username = nameField.getText();
        String newUsername = newNameField.getText();
        String password = new String(newPasswordField.getPassword());
        String role = new String(String.valueOf(newRoleField.getSelectedItem()));

        // Aqui você colocaria a lógica de verificação do login
        UserCRUD.updateUser(username, newUsername, password, role, 1);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        UpdateUserPanel = new JPanel();
        UpdateUserPanel.setLayout(new FormLayout("fill:20px:noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:20px:noGrow", "center:50px:noGrow,top:5dlu:noGrow,center:30px:noGrow,top:4dlu:noGrow,center:17px:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:5dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:5dlu:noGrow,center:43px:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:5dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow,top:5dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow"));
        UpdateUserPanel.setPreferredSize(new Dimension(450, 500));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        CellConstraints cc = new CellConstraints();
        UpdateUserPanel.add(spacer1, cc.xywh(5, 3, 1, 23, CellConstraints.FILL, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        UpdateUserPanel.add(spacer2, cc.xywh(1, 3, 1, 23, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        UpdateUserPanel.add(panel1, cc.xywh(3, 1, 1, 2));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Roboto Light", Font.BOLD, 24, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16751002));
        label1.setText("Update user");
        panel1.add(label1, cc.xy(1, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        imageLogo = new JLabel();
        imageLogo.setPreferredSize(new Dimension(150, 150));
        imageLogo.setText("Label");
        panel1.add(imageLogo, cc.xy(1, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16751002));
        label2.setPreferredSize(new Dimension(61, 17));
        label2.setText("Name");
        UpdateUserPanel.add(label2, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        nameField = new JTextField();
        nameField.setBackground(new Color(-855310));
        nameField.setForeground(new Color(-13947600));
        UpdateUserPanel.add(nameField, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-16751002));
        label3.setText("New Username");
        UpdateUserPanel.add(label3, cc.xy(3, 9));
        newNameField = new JTextField();
        newNameField.setBackground(new Color(-855310));
        newNameField.setForeground(new Color(-13947600));
        UpdateUserPanel.add(newNameField, cc.xy(3, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-16751002));
        Font label4Font = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-16751002));
        label4.setText("New Password");
        UpdateUserPanel.add(label4, cc.xy(3, 13));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-16751002));
        label5.setText("New Role");
        UpdateUserPanel.add(label5, cc.xy(3, 17));
        updateButton = new JButton();
        updateButton.setBackground(new Color(-16751002));
        Font updateButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, updateButton.getFont());
        if (updateButtonFont != null) updateButton.setFont(updateButtonFont);
        updateButton.setForeground(new Color(-855310));
        updateButton.setPreferredSize(new Dimension(100, 50));
        updateButton.setText("Update");
        UpdateUserPanel.add(updateButton, cc.xy(3, 23, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        UpdateUserPanel.add(spacer3, cc.xy(1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
        newRoleField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Director");
        defaultComboBoxModel1.addElement("Engineer");
        defaultComboBoxModel1.addElement("Specialist");
        newRoleField.setModel(defaultComboBoxModel1);
        UpdateUserPanel.add(newRoleField, cc.xy(3, 19));
        newPasswordField = new JPasswordField();
        newPasswordField.setBackground(new Color(-855310));
        newPasswordField.setForeground(new Color(-13947600));
        newPasswordField.setText("");
        UpdateUserPanel.add(newPasswordField, cc.xy(3, 15, CellConstraints.FILL, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        UpdateUserPanel.add(spacer4, cc.xy(3, 25, CellConstraints.DEFAULT, CellConstraints.FILL));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        UpdateUserPanel.add(spacer5, cc.xy(3, 21, CellConstraints.DEFAULT, CellConstraints.FILL));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return UpdateUserPanel;
    }
}
