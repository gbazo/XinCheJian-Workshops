void setup() {
  Serial.begin(9600);
  while(!Serial.available());
  int input = Serial.read();
  Serial.println(input);
}

void loop() {
}
