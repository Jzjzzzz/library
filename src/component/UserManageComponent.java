package component;

import domain.Book;
import domain.User;
import listener.ActionDoneListener;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManageComponent extends Box {
    private UserServiceImpl userService=new UserServiceImpl();
    final int WIDTH=850;
    final int HEIGHT=600;
    JFrame jf=null;
    JSplitPane sp=null;
    private JTable table;

    public UserManageComponent(JFrame jf, JSplitPane sp) {
        //垂直布局
        super(BoxLayout.Y_AXIS);
        String [] ts={"序号","用户名"};

        this.jf=jf;
        this.sp=sp;
        //组装视图
        JPanel btnPanel=new JPanel();
        Color color=new Color(203,220,217);
        btnPanel.setBackground(color);
        btnPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));


        //组装表格
        table = new JTable(UserAll(),ts);
        table.setPreferredSize(new Dimension(740, 460));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.setRowHeight(30);
        //设置智能选中一行

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane =new JScrollPane(table);
        this.add(scrollPane);

    }

    public Object[][] UserAll() {
        List<User> list = userService.findAll();
        Object[][] obj = new Object[list.size()][7];
        int i = 0;
        for (User user: list) {
            obj[i][0] = user.getId();
            obj[i][1] = user.getUsername();
            i++;
        }

        return obj;
    }
}
