package component;

import domain.Book;
import listener.ActionDoneListener;
import service.BookService;
import service.impl.BookServiceImpl;
import util.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AddBookDialog extends JDialog {
    private BookServiceImpl bookService=new BookServiceImpl();
    final int WIDTH=400;
    final int HEIGHT=300;
    private ActionDoneListener listener;
    public AddBookDialog(JFrame jf, String title, boolean isModel,ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener=listener;
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        Box vBox=Box.createVerticalBox();

        //组装图书名称
        Box nameBox=Box.createHorizontalBox();
        JLabel nameLable=new JLabel("图书名称:");
        JTextField nameField=new JTextField(15);
        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装图书数量
        Box stockBox=Box.createHorizontalBox();
        JLabel stockLable=new JLabel("图书数量:");
        JTextField stockField=new JTextField(15);
        stockBox.add(stockLable);
        stockBox.add(Box.createHorizontalStrut(20));
        stockBox.add(stockField);

        //组装图书作者
        Box authorBox=Box.createHorizontalBox();
        JLabel authorBoxLable=new JLabel("图书作者:");
        JTextField authorBoxField=new JTextField(15);
        authorBox.add(authorBoxLable);
        authorBox.add(Box.createHorizontalStrut(20));
        authorBox.add(authorBoxField);

        //组装图书价格
        Box priceBox=Box.createHorizontalBox();
        JLabel priceBoxLable=new JLabel("图书价格:");
        JTextField priceBoxField=new JTextField(15);
        priceBox.add(priceBoxLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceBoxField);

        //组装图书简介
        Box descBox=Box.createHorizontalBox();
        JLabel descBoxLable=new JLabel("图书简介:");
        JTextArea descArea=new JTextArea(3,15);
        descBox.add(descBoxLable);
        descBox.add(Box.createHorizontalStrut(20));
        descBox.add(descArea);

        //组装按钮
        Box btnBox=Box.createHorizontalBox();
        JButton addBtn=new JButton("添加");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim(); //书籍名称
                String stock = stockField.getText().trim(); //书籍数量
                String author = authorBoxField.getText().trim(); //书籍作者
                String price = priceBoxField.getText().trim(); //书籍价格
                String desc = descArea.getText().trim(); //详情
                //逻辑判断
                if(name.equals("")){
                    JOptionPane.showMessageDialog(jf,"书籍名称不能为空");
                    return;
                }
                if(price.equals("")){
                    JOptionPane.showMessageDialog(jf,"书籍价格不能为空");
                    return;
                }
                if(stock.equals("")){
                    JOptionPane.showMessageDialog(jf,"书籍数量不能为空");
                    return;
                }
                //这里从数据库获取有无同名书籍判断
                Book isBook = bookService.finByName(name);
                if(isBook!=null){
                    JOptionPane.showMessageDialog(jf,"该书籍已存在");
                    return;
                }

                Book book=new Book();
                //封装
                book.setBookName(name);
                book.setAmount(Integer.valueOf(stock));
                book.setAuthor(author);
                book.setPrice(new BigDecimal(price));
                book.setDescription(desc);
                Boolean result = bookService.add(book);
                if(result==true){
                    JOptionPane.showMessageDialog(jf,"添加成功");
                    dispose();
                    listener.done(null);
                }else{
                    JOptionPane.showMessageDialog(jf,"添加失败");
                }

            }
        });
        btnBox.add(addBtn);

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(stockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(authorBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(descBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(btnBox);
        //为了左右有间距，在vBox外层封装一个水平的Box，添加间隔
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));
        this.add(hBox);
    }
}
