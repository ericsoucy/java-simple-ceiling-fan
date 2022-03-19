package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
  The unit has 2 pull cords:
  One increases the speed each time it is pulled.
  There are 3 speed settings, and an “off” (speed 0) setting.
  If the cord is pulled on speed 3, the fan returns to the “off” setting.
  One reverses the direction of the fan at the current speed setting.
  Once the direction has been reversed,
  it remains reversed as we cycle through the speed settings, until reversed again.
   You can assume the unit is always powered (no wall switch)

*/

class CeilingFanUnitTests {

  @ParameterizedTest(
      name =
          "Given fan speed is at x and fan direction is d"
              + " When Direction cord is pulled Then fan direction is reversed and remains reversed through speed change cycle")
  @CsvSource({"NORMAL, REVERSED", "REVERSED, NORMAL"})
  void
      given_FanSpeedAtXAndFanDirectionIsNormal_When_DirectionCordIsPulled_Then_FanDirectionRemainsReversedThroughSpeedCycle(
          String initialDirection, String expectedDirection) {
    CeilingFan fan = new CeilingFan();
    if (!fan.getDirection().name().equals(initialDirection)) {
      fan.pullDirectionCord();
    }
    fan.pullDirectionCord();
    for (int i = 0; i < 3; i++) {
      fan.pullSpeedCord();
      assertEquals(expectedDirection, fan.getDirection().name());
    }
    fan.pullDirectionCord();
    assertEquals(initialDirection, fan.getDirection().name());
  }

  @ParameterizedTest(
      name =
          "Given fan speed {0} and fan direction is normal  when direction cord is pulled then expected direction is {1}")
  @CsvSource({"0 , REVERSED", "1, REVERSED", "2, REVERSED", "3, REVERSED"})
  void
      given_FanSpeedAtXAndFanDirectionIsNormal_When_DirectionCordIsPulled_Then_FanDirectionIsReversedAndSpeedIsAtX(
          int speed, String expectedDirection) {
    CeilingFan fan = new CeilingFan();
    for (int i = 0; i < speed; i++) {
      fan.pullSpeedCord();
    }
    fan.pullDirectionCord();
    assertEquals(speed, fan.getSpeedSetting());
    assertEquals(expectedDirection, CeilingFan.Direction.REVERSED.name());
  }

  @ParameterizedTest(
      name = "Given fan speed is at {0} when speed cord is pulled then fan speed is at {1}")
  @CsvSource({"0, 1", "1, 2", "2, 3"})
  void given_FanIsAtSpeedX_When_SpeedCordIsPulled_Then_SpeedSettingIsAtSpeedXPlusOne(
      int initialSpeed, int expectedSpeed) {
    CeilingFan fan = new CeilingFan();
    for (int i = 0; i < initialSpeed; i++) {
      fan.pullSpeedCord();
    }
    fan.pullSpeedCord();
    assertEquals(expectedSpeed, fan.getSpeedSetting());
  }

  @Test
  @DisplayName("given Fan Is At Speed 3 When Speed Cord Is Pulled Then SpeedSetting Is At Speed 0")
  void given_FanIsAtSpeed3_When_SpeedCordIsPulled_Then_SpeedSettingIsAtSpeed0() {
    CeilingFan fan = new CeilingFan();
    fan.pullSpeedCord();
    fan.pullSpeedCord();
    fan.pullSpeedCord();
    fan.pullSpeedCord();
    assertEquals(0, fan.getSpeedSetting());
  }
}
