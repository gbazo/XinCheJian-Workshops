void setup() {
   Serial.begin(9600);
}

void loop() {
  int input = Serial.read();
  if(input == 'h') {
    Serial.println("Hello!");
  }
}
