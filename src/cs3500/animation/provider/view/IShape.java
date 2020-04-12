package cs3500.animation.provider.view;

import cs3500.animation.model.Posn;

import java.util.List;

public interface IShape {

  IColor getColor();

  List<Keyframe> getKeyframes();

  Posn getPosn();

  int getWidth();

  int getHeight();
}
