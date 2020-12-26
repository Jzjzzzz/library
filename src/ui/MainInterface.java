package ui;

import component.BackGroundPanel;
import domain.User;
import service.impl.UserServiceImpl;
import util.PathUtils;
import util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class MainInterface {
    private UserServiceImpl userService=new UserServiceImpl();

    //窗口名称
    JFrame jf = new JFrame("软件工程二班专升本图书库");

    //设置窗口宽高
    final int WIDTH = 500;
    final int HEIGHT = 300;

    /*
     * 视图组装
     * */
    public void init() throws Exception {
        //定位窗口
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        //关闭窗口自由拉伸
        jf.setResizable(false);
        //窗口图标
        jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("logo.png"))));
        //设置背景图片
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("library.jpg"))));

        //组装登录相关的元素
        Box vBox=Box.createVerticalBox();

        //组装用户名
        Box uBox=Box.createHorizontalBox();
        JLabel uLable=new JLabel("用户名:");
        JTextField uField=new JTextField(15);
        uBox.add(uLable);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box  pBox=Box.createHorizontalBox();
        JLabel pLable=new JLabel("密    码:");
        JPasswordField pField=new JPasswordField(15);
        pBox.add(pLable);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装按钮
        Box btnBox=Box.createHorizontalBox();
        JButton loginBtn=new JButton("登录");
        JButton regisBtn=new JButton("清空");
        regisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uField.setText("");
                pField.setText("");
            }
        });
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String username = uField.getText().trim(); //账号
                String password = pField.getText().trim(); //密码
                if(username==null||"".equals(username)){
                    JOptionPane.showMessageDialog(jf,"账号不能为空");
                }
                else if(password==null||"".equals(password)){
                    JOptionPane.showMessageDialog(jf,"密码不能为空");
                }
                else {
                    User user = userService.login(username, password); //查询数据库对应用户
                    if(user!=null){
                        try {
                            new ManagerInterface().init(user);
                            jf.dispose();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(jf,"账号密码错误，登录失败");

                    }
                }

            }
        });


        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(regisBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        //窗口可视
        jf.setVisible(true);

    }


    /*
     * 客户端入口
     * */
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
