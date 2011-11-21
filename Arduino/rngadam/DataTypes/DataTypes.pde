void setup() {
}

void loop() {
  //boolean	1 byte
  boolean b1 = true; 
  boolean b2 = false;
  //char	1 byte
  char c1 = 0; 
  char c2 = 'A';
  // byte    1 byte
  byte byte1 = 10;  
  byte byte2 = B10010;  // "B" is the binary formatter (B10010 = 18 decimal) 
  //int	2 byte
  int i1 = -32768;
  int i2 = 32767;
  //unsigned int	2 byte
  //word	2 byte
  unsigned int ui1 = 0;
  unsigned int ui2 = 65535;
  //long	4 byte
  long l1 =  -2147483648; //  -2,147,483,648
  long l2 = 2147483647; // 2,147,483,647
   //unsigned long	4 byte
  unsigned long ul1 = 0;
  unsigned long ul2 =  4294967295; //  4,294,967,295
  //float or double	4 byte
  float f1 = -3.4028235E+38;
  float f2 = 3.4028235E+38;  
  //string	1 byte + x
  char string1[15];
  char string2[8] = {'a', 'r', 'd', 'u', 'i', 'n', 'o'};
  char string3[8] = {'a', 'r', 'd', 'u', 'i', 'n', 'o', '\0'};
  char string4[ ] = "arduino";
  char string5[8] = "arduino";
  char string6[15] = "arduino";  
  //array	1 byte + x
  int a[6] = {0, 1, 2, 3, 4};
}
