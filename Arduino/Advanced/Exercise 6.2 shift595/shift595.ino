

const int serialData = 13;    // pin 14 in 595. Note Arduino PIN13 has LED - you can see the data input!
const int outputEnable = 12;  // pin 13 on 505
const int latchClk = 11;      // pin 12 on 505
const int shiftClk = 10;      // pin 11 on 505
const int reset = 9;          // pin 10 on 505

void setup() {
  // put your setup code here, to run once:

  pinMode(shiftClk, OUTPUT);          // Need a low to high signal to shift serial data into the shift register
  pinMode(outputEnable, OUTPUT);      // LOW = enable, HIGH = tristate
  pinMode(reset, OUTPUT);             // LOW = reset, HIGH = normal operation.
  pinMode(latchClk, OUTPUT);          // Need a low to high signal to latch serial data to output
  pinMode(serialData, OUTPUT);      

  digitalWrite(outputEnable, LOW);    // Set initial state of all control & data pinscs
  digitalWrite(shiftClk, LOW);
  digitalWrite(latchClk, LOW);
  digitalWrite(serialData, LOW);

  digitalWrite(reset, LOW);          // reset the shift register
  digitalWrite(reset, HIGH);         // enable normal operation

  // clear the output latch (using cleared shift reg outputs)
  digitalWrite(latchClk, LOW);
  digitalWrite(latchClk, HIGH);

}

void loop() {
  // put your main code here, to run repeatedly: 

  for (int i=0; i<8; i++){
    if (i<1) {
      digitalWrite(serialData, HIGH);
    }
    else {
      digitalWrite(serialData, LOW);
    }
    
    // clock the shift register
    digitalWrite(shiftClk, LOW);
    digitalWrite(shiftClk, HIGH);

    digitalWrite(outputEnable, HIGH);        // disable the outputs to minimise flashing

    // clock the output latch
    digitalWrite(latchClk, LOW);
    digitalWrite(latchClk, HIGH);

    digitalWrite(outputEnable, LOW);        // re-enable the outputs
    delay(60);    // set speed of output
  }
}






