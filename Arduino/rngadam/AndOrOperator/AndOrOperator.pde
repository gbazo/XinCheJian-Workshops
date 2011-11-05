void setup() {
  Serial.begin(9600);
  boolean v1 = true;
  boolean v2 = true;
  if(v1 && v2) {
    Serial.println("v1 && v2 = true");
  } else {
    Serial.println("v1 && v2 = false");
  }
  if(v1 || v2) {
    Serial.println("v1 || v2 = true");
  } else {
    Serial.println("v1 || v2 = false");
  }
}

void loop() {
}
