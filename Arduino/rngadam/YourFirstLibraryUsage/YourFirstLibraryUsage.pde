#include <EEPROM.h>

void setup()
{
  Serial.begin(9600);
  Serial.println("Starting");
}

void loop()
{
  byte value = EEPROM.read(0);
  Serial.print(0);
  Serial.print("\t");
  Serial.println(value, DEC);
  EEPROM.write(0, value+1); // life of 100,000 write/erase cycles
  delay(1000);
}

