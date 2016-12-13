package com.fj.hiwetoptools.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by linyu on 2016/8/8.
 * window弹出图片
 */
public class QRCodeFrame extends JFrame{

    private JPanel contentPane;
    private static final Logger logger = LoggerFactory.getLogger(QRCodeFrame.class);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    new QRCodeFrame("D:\\develop\\running\\workspace_idea\\smartqq\\qrcode.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 关闭弹出窗口
     * @param qrCodeFrame
     */
    private void closeQrCodeFrame(QRCodeFrame qrCodeFrame){
        if(null!=qrCodeFrame){
            try {
                qrCodeFrame.dispose();
            }catch (Exception e){
                logger.error("close qrcode error",e);
            }
        }
    }

    /**
     * Create the frame.
     */
    @SuppressWarnings("serial")
    public QRCodeFrame(final String filePath) {
        setBackground(Color.WHITE);
        this.setResizable(false);
        this.setTitle("\u8BF7\u7528\u624B\u673A\u626B\u63CF\u5FAE\u4FE1\u4E8C\u7EF4\u7801");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 297, 362);
        this.contentPane = new JPanel() ;
        contentPane.setBackground(new Color(102, 153, 255));
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel qrcodePanel = new JPanel(){
            public void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(filePath);
                // 图片随窗体大小而变化
                g.drawImage(icon.getImage(), 0, 0, 301, 301, this);
            }
        };
        qrcodePanel.setBounds(0, 0, 295, 295);
        JLabel tipLable = new JLabel("扫描二维码登录");
        tipLable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        tipLable.setHorizontalAlignment(SwingConstants.CENTER);
        tipLable.setBounds(0, 297, 291, 37);

        contentPane.add(qrcodePanel);
        contentPane.add(tipLable);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
