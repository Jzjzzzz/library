package component;

import domain.Borrow;
import domain.User;
import listener.ActionDoneListener;
import service.impl.BorrowServiceImpl;
import service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowManageComponent extends Box {
    private BorrowServiceImpl borrowService=new BorrowServiceImpl();
    final int WIDTH=850;
    final int HEIGHT=600;
    JFrame jf=null;
    JSplitPane sp=null;
    private JTable table;

    public BorrowManageComponent(JFrame jf, JSplitPane sp,User user) {
        //垂直布局
        super(BoxLayout.Y_AXIS);
        String [] ts={"序号","用户名","书名","借书时间","是否归还","归还时间"};

        this.jf=jf;
        this.sp=sp;
        //组装视图
        JPanel btnPanel=new JPanel();
        Color color=new Color(203,220,217);
        btnPanel.setBackground(color);
        btnPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton repayBtn=new JButton("归还");
        repayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前表格选中的id
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请先选择要修改的条目!");
                    return;
                }
                Object id = table.getValueAt(selectedRow, 0);
                Boolean result = borrowService.giveBack((Integer) id,user);
                if(result==true){
                    JOptionPane.showMessageDialog(jf,"还书成功!");
                    sp.setRightComponent(new BookManageComponent(jf,sp,user));

                }else {
                    JOptionPane.showMessageDialog(jf,"还书失败!");
                }
            }
        });
        if(!user.getUsername().equals("admin")){
            btnPanel.add(repayBtn);
        }
        this.add(btnPanel);
        //组装表格
        table = new JTable(BorrowAll(user),ts);
        table.setPreferredSize(new Dimension(740, 460));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.setRowHeight(30);
        //设置智能选中一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane =new JScrollPane(table);
        this.add(scrollPane);

    }

    public Object[][] BorrowAll  (User user) {
        List<Borrow> list=null;
        if(user.getUsername().equals("admin")){
            list = borrowService.findAll(); //当时管理员时查询所有用户所有记录
        }else {
            list = borrowService.findAll(user); //当不是管理员时只查询当前用户信息
        }
        Object[][] obj = new Object[list.size()][7];
        int i = 0;
        for (Borrow borrow : list) {
            obj[i][0] = borrow.getId();
            obj[i][1] = borrow.getUsername();
            obj[i][2] = borrow.getBookName();
            obj[i][3] = borrow.getCreateDate();
            obj[i][4] = (borrow.getIsRepay()==1)?"已归还":"未归还";
            obj[i][5] = (borrow.getRepayDate()==null)?"":borrow.getRepayDate();
            i++;
        }

        return obj;
    }
}
