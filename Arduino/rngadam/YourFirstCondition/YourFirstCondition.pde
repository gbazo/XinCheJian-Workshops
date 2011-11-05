void setup() {
  Serial.begin(9600);
  int age = 21;
  Serial.print("My age is ");
  Serial.println(age);
  if(age < 22) {
    Serial.println("I'm young!");
  }
  if(age > 22) {
    Serial.println("I'm old!");
  }
}

void loop() {
}
