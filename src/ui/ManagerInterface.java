package ui;

import component.BookManageComponent;
import component.BorrowManageComponent;
import component.UserManageComponent;
import domain.User;
import util.PathUtils;
import util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ManagerInterface {
    JFrame jf = new JFrame("软件工程二班专升本图书库,欢迎您");
    final int WIDTH = 1000;
    final int HEIGHT = 600;

    public void init(User user) throws Exception {
        //定位窗口
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        //关闭窗口自由拉伸
        jf.setResizable(false);
        //窗口图标
        jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("logo.png"))));
        //设置菜单栏
        JMenuBar jmb = new JMenuBar();
        JMenu jMenu = new JMenu("设置");
        JMenuItem m1 = new JMenuItem("切换账号");
        JMenuItem m2 = new JMenuItem("退出程序");
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();
                    jf.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jMenu.add(m1);
        jMenu.add(m2);
        jmb.add(jMenu);
        jf.setJMenuBar(jmb);

        //设置分割面板
        JSplitPane sp = new JSplitPane();
        //支持连续布局
        sp.setContinuousLayout(true);
        sp.setDividerLocation(150);
        sp.setDividerSize(7);
        //设置左侧内容
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("用户管理");
        DefaultMutableTreeNode bookManage = new DefaultMutableTreeNode("图书管理");
        DefaultMutableTreeNode borrowManage = new DefaultMutableTreeNode("借阅管理");
        root.add(userManage);
        root.add(bookManage);
        root.add(borrowManage);
        JTree tree = new JTree(root);
        Color color = new Color(203, 220, 217);
        MyRenderer myRenderer = new MyRenderer();
        myRenderer.setBackgroundNonSelectionColor(color);
        myRenderer.setBackgroundSelectionColor(new Color(140, 140, 140));
        tree.setCellRenderer(myRenderer);
        tree.setBackground(color);

        //设置当前tree默认选中图书管理
        tree.setSelectionRow(2);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            //当条目选中变化后，这个方法会执行
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //得到当前选中的结点对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if (userManage.equals(lastPathComponent)) {
                    if(user.getUsername().equals("admin")){
                        sp.setRightComponent(new UserManageComponent(jf,sp));
                        sp.setLastDividerLocation(150);
                    }else {
                        JOptionPane.showMessageDialog(jf,"您不是管理员无法查看");
                        return;
                    }

                } else if (bookManage.equals(lastPathComponent)) {
                    sp.setRightComponent(new BookManageComponent(jf,sp,user));
                    sp.setLastDividerLocation(150);
                }
                if (borrowManage.equals(lastPathComponent)) {
                    sp.setRightComponent(new BorrowManageComponent(jf,sp,user));
                    sp.setLastDividerLocation(150);
                }

            }
        });
        sp.setRightComponent(new BookManageComponent(jf,sp,user));
        sp.setLeftComponent(tree);
        jf.add(sp);
        jf.setVisible(true);
        jf.setTitle("软件工程二班专升本图书库,欢迎您"+user.getUsername());
    }


    //自定义结点绘制器
    private class MyRenderer extends DefaultTreeCellRenderer {
        private ImageIcon rootIcon = null;
        private ImageIcon userManageIcon = null;
        private ImageIcon bookManageIcon = null;
        private ImageIcon borrowManageIcon = null;
        private ImageIcon statisticsManageIcon = null;

        public MyRenderer() {
            rootIcon = new ImageIcon(PathUtils.getRealPath("systemManage.png"));
            userManageIcon = new ImageIcon(PathUtils.getRealPath("userManage.png"));
            bookManageIcon = new ImageIcon(PathUtils.getRealPath("bookManage.png"));
            borrowManageIcon = new ImageIcon(PathUtils.getRealPath("borrowManage.png"));
            statisticsManageIcon = new ImageIcon(PathUtils.getRealPath("statisticsManage.png"));
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            ImageIcon image = null;
            switch (row) {
                case 0:
                    image = rootIcon;
                    break;
                case 1:
                    image = userManageIcon;
                    break;
                case 2:
                    image = bookManageIcon;
                    break;
                case 3:
                    image = borrowManageIcon;
                    break;
                case 4:
                    image = statisticsManageIcon;
                    break;

            }
            this.setIcon(image);
            return this;
        }
    }
}
