package org.example;

public class CeilingFan {
  Direction direction;
  int speedSetting;

  CeilingFan() {
    this.speedSetting = 0;
    this.direction = Direction.NORMAL;
  }

  public void pullSpeedCord() {
    if (this.speedSetting == 3) {
      this.speedSetting = 0;
    } else {
      this.speedSetting++;
    }
  }

  public int getSpeedSetting() {
    return this.speedSetting;
  }

  public void pullDirectionCord() {
    if (this.direction == Direction.NORMAL) {
      this.direction = Direction.REVERSED;
    } else {
      this.direction = Direction.NORMAL;
    }
  }

  public Direction getDirection() {
    return this.direction;
  }

  enum Direction {
    NORMAL,
    REVERSED
  }
}
