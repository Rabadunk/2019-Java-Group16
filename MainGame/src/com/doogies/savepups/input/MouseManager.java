package com.doogies.savepups.input;

import com.doogies.savepups.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private boolean moved = false;
    private UIManager uiManager;

    public MouseManager(){

    }

    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    // Getters

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    // Implemented methods

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }

        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(mouseY != e.getY() || mouseX != e.getX()) {
            moved = true;
        } else {
            moved = false;
        }

        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null){
            uiManager.onMouseMove(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }


    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}