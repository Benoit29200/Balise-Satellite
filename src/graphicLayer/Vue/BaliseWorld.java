package graphicLayer.Vue;

import graphicLayer.ObjetVue.EntiteVue;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaliseWorld extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;

    private List<EntiteVue> drawables = Collections.synchronizedList(new LinkedList<EntiteVue>());
    private KeyListener keyListener;

    String name = "";

    public BaliseWorld(String name) {
        this.name = name;
        this.addKeyListener(this);
    }

    public List<EntiteVue> contents() {
        return drawables;
    }

    public void open() {
        JFrame frame = new JFrame(name);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        requestFocus();
    }

    public void add(EntiteVue d) {
        drawables.add(d);
        d.setWorld(this);
        repaint();
    }

    public void remove(EntiteVue d) {
        d.setWorld(null);
        drawables.remove(d);
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        synchronized (drawables) {
            for (Iterator<EntiteVue> iter = drawables.iterator(); iter.hasNext();) {
                iter.next().draw(g);
            }
        }
    }

    public void setKeyListener(KeyListener k) {
        keyListener = k;
    }

    public void clear() {
        for (Iterator<EntiteVue> iter = drawables.iterator(); iter.hasNext();) {
            iter.next().setWorld(null);
        }
        drawables.clear();
        repaint();
    }

    public List<EntiteVue> find(Point p) {
        List<EntiteVue> l = new ArrayList<EntiteVue>();
        for (Iterator<EntiteVue> iter = drawables.iterator(); iter.hasNext();) {
            EntiteVue element = iter.next();
            if (element.getBounds().contains(p)) {
                l.add(element);
            }
        }
        return l;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (keyListener != null) {
            keyListener.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyListener != null) {
            keyListener.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyListener != null) {
            keyListener.keyReleased(e);
        }

    }

}