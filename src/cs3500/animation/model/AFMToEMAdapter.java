package cs3500.animation.model;

import cs3500.animation.provider.view.ExCELlenceOperations;
import cs3500.animation.provider.view.IColor;
import cs3500.animation.provider.view.IMotion;
import cs3500.animation.provider.view.Keyframe;

import java.util.ArrayList;
import java.util.HashMap;

public class AFMToEMAdapter extends AnimationFrameModel implements ExCELlenceOperations {
  @Override
  public void create(String name, String shape, Posn p, double w, double h, IColor c) {

  }

  @Override
  public void addMotion(String name, IMotion m) {

  }

  @Override
  public void removeMotion(String name, IMotion m) {

  }

  @Override
  public ArrayList<IMotion> getMotions(String name) {
    return null;
  }

  @Override
  public void addKeyframe(String name, Keyframe k) {

  }

  @Override
  public void removeKeyframe(String name, Keyframe k) {

  }

  @Override
  public ArrayList<Keyframe> getKeyframes(String name) {
    return null;
  }

  @Override
  public ArrayList<cs3500.animation.provider.view.IShape> getShapesAt(int tick) {
    return null;
  }

  @Override
  public ArrayList<cs3500.animation.provider.view.IShape> getShapesAtKeyframe(int tick) {
    return null;
  }

  @Override
  public HashMap<String, cs3500.animation.provider.view.IShape> getShapes() {
    return null;
  }

  @Override
  public String getState() {
    return null;
  }

  @Override
  public Posn getTopLeft() {
    return null;
  }

  @Override
  public int getWindowWidth() {
    return 0;
  }

  @Override
  public int getWindowHeight() {
    return 0;
  }

  @Override
  public String getKey(cs3500.animation.provider.view.IShape s) {
    return null;
  }

  @Override
  public void sortMotions(String name) {

  }

  @Override
  public void sortKeyframes(String name) {

  }

  @Override
  public void validateMotions(String name) {

  }

  @Override
  public void setTopLeft(Posn p) {

  }

  @Override
  public void setWindowWidth(int w) {

  }

  @Override
  public void setWindowHeight(int h) {

  }
}
