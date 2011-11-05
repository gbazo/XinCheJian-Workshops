int calculate_age(int year_born) {
  return 2011 - year_born;
}

void setup() {
  Serial.begin(9600);
  Serial.print(calculate_age(1980));
}

void loop() {  
}
