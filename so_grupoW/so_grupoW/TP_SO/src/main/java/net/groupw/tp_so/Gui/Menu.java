package net.groupw.tp_so.Gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import net.groupw.tp_so.Files.Kernel;
import net.groupw.tp_so.Files.Log;
import net.groupw.tp_so.Files.User;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class Menu extends JFrame {
    private ArrayList<Log> logs;
    private JPanel Menu;
    private JButton perfilButton;
    private JButton simulacaoButton;
    private JButton logsButton;
    private JButton graficoButton;
    private JButton sairButton;
    private JLabel MenuLabel;

    public Menu(User user) {
        setContentPane(Menu);
        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon icon = new ImageIcon("src/main/java/net/groupw/tp_so/assets/logo.png");
        setIconImage(icon.getImage());


        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new Profile(user);
                });
            }
        });
        logsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Logs();
            }
        });
        graficoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginChart("Chart");
            }
        });

        simulacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Simulator(user);
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kernel kernel = new Kernel();
                kernel.desligarSistema();
            }
        });
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
        Menu = new JPanel();
        Menu.setLayout(new FormLayout("fill:10px:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:10px:noGrow", "center:10px:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:10px:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:10px:noGrow"));
        Menu.setBackground(new Color(-855310));
        Menu.setPreferredSize(new Dimension(400, 350));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
        panel1.setBackground(new Color(-855310));
        panel1.setOpaque(false);
        panel1.setPreferredSize(new Dimension(400, 50));
        CellConstraints cc = new CellConstraints();
        Menu.add(panel1, cc.xy(3, 3));
        MenuLabel = new JLabel();
        Font MenuLabelFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 24, MenuLabel.getFont());
        if (MenuLabelFont != null) MenuLabel.setFont(MenuLabelFont);
        MenuLabel.setForeground(new Color(-16751002));
        MenuLabel.setText("Menu");
        panel1.add(MenuLabel, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:4dlu:noGrow,center:10px:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        panel2.setOpaque(false);
        panel2.setPreferredSize(new Dimension(400, 350));
        Menu.add(panel2, cc.xy(3, 5));
        perfilButton = new JButton();
        perfilButton.setBackground(new Color(-16751002));
        Font perfilButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 18, perfilButton.getFont());
        if (perfilButtonFont != null) perfilButton.setFont(perfilButtonFont);
        perfilButton.setForeground(new Color(-855310));
        perfilButton.setPreferredSize(new Dimension(200, 100));
        perfilButton.setText("Profile");
        panel2.add(perfilButton, cc.xy(1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
        simulacaoButton = new JButton();
        simulacaoButton.setBackground(new Color(-16751002));
        Font simulacaoButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 18, simulacaoButton.getFont());
        if (simulacaoButtonFont != null) simulacaoButton.setFont(simulacaoButtonFont);
        simulacaoButton.setForeground(new Color(-855310));
        simulacaoButton.setPreferredSize(new Dimension(200, 100));
        simulacaoButton.setText("Simulator");
        panel2.add(simulacaoButton, cc.xy(3, 1));
        logsButton = new JButton();
        logsButton.setBackground(new Color(-16751002));
        Font logsButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 18, logsButton.getFont());
        if (logsButtonFont != null) logsButton.setFont(logsButtonFont);
        logsButton.setForeground(new Color(-855310));
        logsButton.setPreferredSize(new Dimension(200, 100));
        logsButton.setText("Logs");
        panel2.add(logsButton, cc.xy(1, 5));
        graficoButton = new JButton();
        graficoButton.setBackground(new Color(-16751002));
        Font graficoButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 18, graficoButton.getFont());
        if (graficoButtonFont != null) graficoButton.setFont(graficoButtonFont);
        graficoButton.setForeground(new Color(-855310));
        graficoButton.setPreferredSize(new Dimension(200, 100));
        graficoButton.setText("Graph");
        panel2.add(graficoButton, cc.xy(3, 5));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel3.setPreferredSize(new Dimension(400, 100));
        Menu.add(panel3, cc.xy(3, 9));
        sairButton = new JButton();
        sairButton.setBackground(new Color(-6877690));
        Font sairButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 18, sairButton.getFont());
        if (sairButtonFont != null) sairButton.setFont(sairButtonFont);
        sairButton.setForeground(new Color(-855310));
        sairButton.setPreferredSize(new Dimension(200, 100));
        sairButton.setText("Exit");
        panel3.add(sairButton, BorderLayout.CENTER);
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        Menu.add(spacer2, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        Menu.add(spacer3, cc.xy(5, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        Menu.add(spacer4, cc.xy(3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        Menu.add(spacer5, cc.xy(3, 11, CellConstraints.DEFAULT, CellConstraints.FILL));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        Menu.add(spacer6, cc.xy(3, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
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
        return Menu;
    }
}