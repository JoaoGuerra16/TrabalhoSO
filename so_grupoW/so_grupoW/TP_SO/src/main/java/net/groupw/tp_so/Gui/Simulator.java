package net.groupw.tp_so.Gui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import net.groupw.tp_so.Enumerations.LogType;
import net.groupw.tp_so.Files.*;
import net.groupw.tp_so.Interface.ChatCallback;
import net.groupw.tp_so.Interface.SimulationCallback;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

public class Simulator extends JFrame implements SimulationCallback {
    private JPanel simulatorPanel;
    private JButton StartButton;
    private JButton chatButton;
    private JScrollBar SimulatorPanelScrollBar;
    private JTextPane SimulatorTextPane;
    private JScrollPane SimulatorScrollPane;
    private JButton StopButton;
    private Timer timer;
    private Chat chat;


    public Simulator(User user) {
        setContentPane(simulatorPanel);
        setTitle("Simulator");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);

        Satellite.getInstance().setSimulationCallback(this);

        ImageIcon icon = new ImageIcon("src/main/java/net/groupw/tp_so/assets/logo.png");
        setIconImage(icon.getImage());

        StartButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer == null || !timer.isRunning()) {
                    timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userSendRandomMessage();
                        }
                    });
                    timer.setRepeats(true);
                    timer.start();
                    LogEntry.getInstance().addLog(new Log(LogType.INFO, "Simulação Iniciada...", LocalDateTime.now()));
                    System.out.println("Simulação iniciada");
                    try {
                        if (chat != null) {
                            chat.dispose();
                        }
                        chatButton.setEnabled(false);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    LogEntry.getInstance().addLog(new Log(LogType.WARNING, "Simulação já em andamento...", LocalDateTime.now()));
                    System.out.println("Simulação já em andamento");
                }
            }
        });

        StopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                    try {
                        chatButton.setEnabled(true);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    LogEntry.getInstance().addLog(new Log(LogType.INFO, "Simulação Interrompida...", LocalDateTime.now()));
                } else {
                    LogEntry.getInstance().addLog(new Log(LogType.INFO, "Nenhuma simulação em andamento...", LocalDateTime.now()));
                    System.out.println("Nenhuma simulação em andamento");
                }
            }
        });


        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    chat = new Chat(user);
                });
            }
        });

    }

    public void addNewMessage(String message, String sender) {
        SwingUtilities.invokeLater(() -> {
            try {
                StyledDocument styledDocument = SimulatorTextPane.getStyledDocument();
                Style style = styledDocument.getStyle(StyleContext.DEFAULT_STYLE);
                styledDocument.insertString(styledDocument.getLength(), sender + " sent: " + message + "\n", style);
                SimulatorTextPane.setCaretPosition(styledDocument.getLength());

            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void onSimulatedMessage(String message, String sender) {
        SwingUtilities.invokeLater(() -> {
            addNewMessage(message, sender);
        });
    }

    public void userSendRandomMessage() {
        String[] sent = {
                "status",
                "request",
                "updates",
                "systemhealth",
                "weather",

        };


        Random random = new Random();
        int randomIndex = random.nextInt(sent.length);

        String randomMessage = sent[randomIndex];
        User.userSendMessage(randomMessage);
        addNewMessage(randomMessage, User.getUsername());
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
        simulatorPanel = new JPanel();
        simulatorPanel.setLayout(new FormLayout("fill:20px:noGrow,left:6dlu:noGrow,fill:100px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:150px:noGrow,left:15px:noGrow,fill:20px:noGrow", "top:4dlu:noGrow,center:40px:noGrow,top:4dlu:noGrow,center:d:grow,top:5dlu:noGrow,top:5dlu:noGrow,center:20px:noGrow,top:4dlu:noGrow,center:20px:noGrow"));
        simulatorPanel.setPreferredSize(new Dimension(500, 550));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        CellConstraints cc = new CellConstraints();
        simulatorPanel.add(spacer1, cc.xy(11, 4, CellConstraints.FILL, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        simulatorPanel.add(spacer2, cc.xy(1, 4, CellConstraints.FILL, CellConstraints.DEFAULT));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        simulatorPanel.add(spacer3, cc.xy(7, 9, CellConstraints.DEFAULT, CellConstraints.FILL));
        StartButton = new JButton();
        StartButton.setBackground(new Color(-15703020));
        Font StartButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, StartButton.getFont());
        if (StartButtonFont != null) StartButton.setFont(StartButtonFont);
        StartButton.setForeground(new Color(-855310));
        StartButton.setPreferredSize(new Dimension(75, 50));
        StartButton.setText("START");
        simulatorPanel.add(StartButton, cc.xywh(3, 6, 1, 3));
        chatButton = new JButton();
        chatButton.setBackground(new Color(-16751002));
        Font chatButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, chatButton.getFont());
        if (chatButtonFont != null) chatButton.setFont(chatButtonFont);
        chatButton.setForeground(new Color(-855310));
        chatButton.setPreferredSize(new Dimension(100, 50));
        chatButton.setText("Commands");
        simulatorPanel.add(chatButton, cc.xywh(9, 6, 1, 3));
        SimulatorScrollPane = new JScrollPane();
        SimulatorScrollPane.setVerticalScrollBarPolicy(22);
        simulatorPanel.add(SimulatorScrollPane, cc.xyw(3, 4, 7, CellConstraints.FILL, CellConstraints.FILL));
        SimulatorTextPane = new JTextPane();
        SimulatorTextPane.setEditable(false);
        SimulatorTextPane.setEnabled(true);
        SimulatorTextPane.setText("");
        SimulatorScrollPane.setViewportView(SimulatorTextPane);
        SimulatorPanelScrollBar = new JScrollBar();
        simulatorPanel.add(SimulatorPanelScrollBar, cc.xy(10, 4, CellConstraints.DEFAULT, CellConstraints.FILL));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Roboto Light", Font.BOLD, 24, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16751002));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setPreferredSize(new Dimension(200, 33));
        label1.setText("Simulator");
        simulatorPanel.add(label1, cc.xyw(1, 2, 11, CellConstraints.CENTER, CellConstraints.DEFAULT));
        StopButton = new JButton();
        StopButton.setBackground(new Color(-10223347));
        Font StopButtonFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 14, StopButton.getFont());
        if (StopButtonFont != null) StopButton.setFont(StopButtonFont);
        StopButton.setForeground(new Color(-855310));
        StopButton.setPreferredSize(new Dimension(100, 50));
        StopButton.setText("STOP");
        simulatorPanel.add(StopButton, cc.xywh(5, 6, 1, 3));
        SimulatorScrollPane.setVerticalScrollBar(SimulatorPanelScrollBar);
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
        return simulatorPanel;
    }
}
