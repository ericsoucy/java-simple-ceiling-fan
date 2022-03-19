package org.example;

import java.util.Scanner;

import static java.lang.System.exit;

public class CeilingFan {
  Direction direction;
  int speedSetting;

  CeilingFan() {
    this.speedSetting = 0;
    this.direction = Direction.NORMAL;
  }

  public static void printMenu(String[] options) {
    for (String option : options) {
      System.out.println(option);
    }
    System.out.print("Choose your option : ");
  }

  public static void main(String[] args) {
    //
    CeilingFan fan = new CeilingFan();
    String[] options = {"D- Pull Direction Cord", "S- Pull Speed Cord", "Q- Quit Program"};
    Scanner scanner = new Scanner(System.in);
    String option = "";
    while (!option.equalsIgnoreCase("q")) {
      printMenu(options);
      option = scanner.nextLine();
      switch (option.toLowerCase()) {
        case "d" -> {
          fan.pullDirectionCord();
          System.out.println(
                  "Fan Direction: " + fan.getDirection() + " Fan Speed: " + fan.getSpeedSetting());
          System.out.println();
        }
        case "s" -> {
          fan.pullSpeedCord();
          System.out.println(
                  "Fan Direction: " + fan.getDirection() + " Fan Speed: " + fan.getSpeedSetting());
          System.out.println();
        }
        case "q" -> exit(0);
        default -> {
          System.out.println("Unrecognized option");
          System.out.println();

        }
      }
    }
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
