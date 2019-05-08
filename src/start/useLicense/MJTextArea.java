package start.useLicense;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class MJTextArea extends JTextArea implements MouseListener {

    private JPopupMenu pop = null; // 弹出菜单
    private JMenuItem copy = null, paste = null, cut = null; // 三个功能菜单

    public MJTextArea() {
        super();
        init();
    }
    public MJTextArea(Document doc) {
        super(doc);
        init();
    }
    public MJTextArea(int rows, int columns){
        super(rows, columns);
        init();
    }
    public MJTextArea(String text){
        super(text);
        init();
    }
    public MJTextArea(String text, int rows, int columns){
        super(text, rows, columns);
        init();
    }
    public MJTextArea(Document doc, String text, int rows, int columns){
        super(doc, text, rows, columns);
        init();
    }

    private void init() {
        this.addMouseListener(this);
        pop = new JPopupMenu();
        pop.add(copy = new JMenuItem("复制"));
        pop.add(paste = new JMenuItem("粘贴"));
        pop.add(cut = new JMenuItem("剪切"));
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        this.add(pop);
    }

    public void action(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals(copy.getText())) { // 复制
            this.copy();
        } else if (str.equals(paste.getText())) { // 粘贴
            this.paste();
        } else if (str.equals(cut.getText())) { // 剪切
            this.cut();
        }
    }

    public JPopupMenu getPop() {
        return pop;
    }

    public void setPop(JPopupMenu pop) {
        this.pop = pop;
    }

    /**
     * 剪切板中是否有文本数据可供粘贴
     *
     * @return true为有文本数据
     */
    public boolean pastable() {
        boolean b = false;
        Clipboard clipboard = this.getToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(this);
        try {
            if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
                b = true;
            }
        } catch (Exception e) {
        }
        return b;
    }

    /**
     * 文本组件中是否具备复制的条件
     *
     * @return true为具备
     */
    public boolean copyable() {
        boolean b = false;
        int start = this.getSelectionStart();
        int end = this.getSelectionEnd();
        if (start != end)
            b = true;
        return b;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            copy.setEnabled(copyable());
            paste.setEnabled(pastable());
            cut.setEnabled(copyable());
            pop.show(this, e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

}