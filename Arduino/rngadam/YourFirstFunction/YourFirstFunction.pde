int calculate_age(int year_born, int current_year) {
  return current_year - year_born;
}

void setup() {
  Serial.begin(9600);
  Serial.print(calculate_age(1980, 2011));
}

void loop() {  
}
