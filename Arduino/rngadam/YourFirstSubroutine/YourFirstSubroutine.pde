void setup() {
  Serial.begin(9600);
  output_name();
}

void loop() {
}

void output_name() {
  Serial.println("My name is ...");
}
