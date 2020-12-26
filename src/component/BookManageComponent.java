package component;

import domain.Book;
import domain.User;
import listener.ActionDoneListener;
import service.BookService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class BookManageComponent extends Box {
    private BookServiceImpl bookService=new BookServiceImpl();
    private UserServiceImpl userService=new UserServiceImpl();
    final int WIDTH=850;
    final int HEIGHT=600;
    JFrame jf=null;
    JSplitPane sp=null;
    private JTable table;

    public BookManageComponent(JFrame jf, JSplitPane sp, User user) {
        //垂直布局
        super(BoxLayout.Y_AXIS);
        String [] ts={"编号","书名","简介","作者","价格","库存"};

        this.jf=jf;
        this.sp=sp;
        //组装视图
        JPanel btnPanel=new JPanel();
        Color color=new Color(203,220,217);
        btnPanel.setBackground(color);
        btnPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton borrowBtn=new JButton("借阅");
        JButton addBtn=new JButton("添加");
        JButton updateBtn=new JButton("修改");
        JButton deleteBtn=new JButton("删除");
        borrowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前表格选中的id
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请先选择要借阅的条目!");
                    return;
                }
                Object id = table.getValueAt(selectedRow, 0);
                Book book = bookService.finById((Integer) id);
                User user1 = userService.finById(user.getId());  //获取最新的user
                if(user1.getBookNumber()==1){
                    JOptionPane.showMessageDialog(jf,"当前已存在借书，请归还后再借用!");
                    return;
                }
                if(book!=null){ //判断书籍是否存在
                    if(book.getSurplusAmount()<=0){ //当书籍数量小于等于0的时候，不执行借书操作
                        JOptionPane.showMessageDialog(jf,"该书籍库存已不足");
                        return;
                    }
                    Boolean borrow = bookService.borrowBook(book, user);
                    if(borrow==true){
                        JOptionPane.showMessageDialog(jf,"借阅成功!");
                        sp.setRightComponent(new BookManageComponent(jf,sp,user));

                    }else {
                        JOptionPane.showMessageDialog(jf,"借阅失败!");
                    }
                }else {
                    JOptionPane.showMessageDialog(jf,"该书籍不存在!");
                    return;
                }

            }
        });


        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出一个对话框，让用户输入添加图书的信息
                new AddBookDialog(jf, "添加图书", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        sp.setRightComponent(new BookManageComponent(jf,sp,user));
                    }
                }).setVisible(true);

            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前表格选中的id
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请先选择要修改的条目!");
                    return;
                }
                Object id = table.getValueAt(selectedRow, 0);
                //弹出一个对话框,让用户修改
                new UpdateBookDialog(jf, "修改图书", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        sp.setRightComponent(new BookManageComponent(jf,sp,user));
                    }
                },String.valueOf(id)).setVisible(true);
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取选中的条目
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请先选择要删除的条目!");
                    return;
                }
                //防止用户误操作
                int result = JOptionPane.showConfirmDialog(jf, "确认要删除选中的条目吗?", "确认删除", JOptionPane.YES_NO_OPTION);
                if(result!=JOptionPane.YES_OPTION){
                    return;
                }

                Object id = table.getValueAt(selectedRow, 0);
                Boolean delete = bookService.delete((Integer) id);
                if(delete==true){
                    JOptionPane.showMessageDialog(jf,"删除成功!");
                    sp.setRightComponent(new BookManageComponent(jf,sp,user));

                }else {
                    JOptionPane.showMessageDialog(jf,"删除失败!");
                }
            }
        });

        //判断是否是管理员
        if(user.getUsername().equals("admin")){
            btnPanel.add(addBtn);
            btnPanel.add(updateBtn);
            btnPanel.add(deleteBtn);
        }else {
            btnPanel.add(borrowBtn);
        }


        this.add(btnPanel);

        //组装表格
        table = new JTable(BookAll(),ts);
        table.setPreferredSize(new Dimension(740, 460));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.setRowHeight(30);

        //设置智能选中一行

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane =new JScrollPane(table);
        this.add(scrollPane);

    }

    public Object[][] BookAll  () {
        List<Book> list = bookService.findAll();
        Object[][] obj = new Object[list.size()][7];
        int i = 0;
        for (Book book: list) {
            obj[i][0] = book.getId();
            obj[i][1] = book.getBookName();
            obj[i][2] = book.getDescription();
            obj[i][3] = book.getAuthor();
            obj[i][4] = book.getPrice();
            obj[i][5] = book.getSurplusAmount();
            i++;
        }

        return obj;
    }
}
