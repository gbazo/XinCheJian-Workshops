
BareMinimum.cpp.elf:     file format elf32-avr


Disassembly of section .text:

00000000 <__vectors>:
	timer0_millis = m;
	timer0_overflow_count++;
}

unsigned long millis()
{
   0:	0c 94 34 00 	jmp	0x68	; 0x68 <__ctors_end>
   4:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
   8:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
   c:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  10:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  14:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  18:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  1c:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  20:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  24:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  28:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  2c:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  30:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  34:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  38:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  3c:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  40:	0c 94 5c 00 	jmp	0xb8	; 0xb8 <__vector_16>
  44:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  48:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  4c:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  50:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  54:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  58:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  5c:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  60:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>
  64:	0c 94 51 00 	jmp	0xa2	; 0xa2 <__bad_interrupt>

00000068 <__ctors_end>:
  68:	11 24       	eor	r1, r1
  6a:	1f be       	out	0x3f, r1	; 63
  6c:	cf ef       	ldi	r28, 0xFF	; 255
  6e:	d4 e0       	ldi	r29, 0x04	; 4
  70:	de bf       	out	0x3e, r29	; 62
  72:	cd bf       	out	0x3d, r28	; 61

00000074 <__do_copy_data>:
  74:	11 e0       	ldi	r17, 0x01	; 1
  76:	a0 e0       	ldi	r26, 0x00	; 0
  78:	b1 e0       	ldi	r27, 0x01	; 1
  7a:	e2 ec       	ldi	r30, 0xC2	; 194
  7c:	f1 e0       	ldi	r31, 0x01	; 1
  7e:	02 c0       	rjmp	.+4      	; 0x84 <.do_copy_data_start>

00000080 <.do_copy_data_loop>:
  80:	05 90       	lpm	r0, Z+
  82:	0d 92       	st	X+, r0

00000084 <.do_copy_data_start>:
  84:	a0 30       	cpi	r26, 0x00	; 0
  86:	b1 07       	cpc	r27, r17
  88:	d9 f7       	brne	.-10     	; 0x80 <.do_copy_data_loop>

0000008a <__do_clear_bss>:
  8a:	11 e0       	ldi	r17, 0x01	; 1
  8c:	a0 e0       	ldi	r26, 0x00	; 0
  8e:	b1 e0       	ldi	r27, 0x01	; 1
  90:	01 c0       	rjmp	.+2      	; 0x94 <.do_clear_bss_start>

00000092 <.do_clear_bss_loop>:
  92:	1d 92       	st	X+, r1

00000094 <.do_clear_bss_start>:
  94:	a9 30       	cpi	r26, 0x09	; 9
  96:	b1 07       	cpc	r27, r17
  98:	e1 f7       	brne	.-8      	; 0x92 <.do_clear_bss_loop>
  9a:	0e 94 55 00 	call	0xaa	; 0xaa <main>
  9e:	0c 94 df 00 	jmp	0x1be	; 0x1be <_exit>

000000a2 <__bad_interrupt>:
  a2:	0c 94 00 00 	jmp	0	; 0x0 <__vectors>

000000a6 <setup>:
void setup();
void loop();
void setup() 
  {
  // put your setup code here, to run once
  }
  a6:	08 95       	ret

000000a8 <loop>:

void loop() 
  {
  // put your main code here, to run repeatedly
  }
  a8:	08 95       	ret

000000aa <main>:
#include <WProgram.h>

int main(void)
{
	init();
  aa:	0e 94 a4 00 	call	0x148	; 0x148 <init>

	setup();
  ae:	0e 94 53 00 	call	0xa6	; 0xa6 <setup>
    
	for (;;)
		loop();
  b2:	0e 94 54 00 	call	0xa8	; 0xa8 <loop>
  b6:	fd cf       	rjmp	.-6      	; 0xb2 <main+0x8>

000000b8 <__vector_16>:
volatile unsigned long timer0_overflow_count = 0;
volatile unsigned long timer0_millis = 0;
static unsigned char timer0_fract = 0;

SIGNAL(TIMER0_OVF_vect)
{
  b8:	1f 92       	push	r1
  ba:	0f 92       	push	r0
  bc:	0f b6       	in	r0, 0x3f	; 63
  be:	0f 92       	push	r0
  c0:	11 24       	eor	r1, r1
  c2:	2f 93       	push	r18
  c4:	3f 93       	push	r19
  c6:	8f 93       	push	r24
  c8:	9f 93       	push	r25
  ca:	af 93       	push	r26
  cc:	bf 93       	push	r27
	// copy these to local variables so they can be stored in registers
	// (volatile variables must be read from memory on every access)
	unsigned long m = timer0_millis;
  ce:	80 91 04 01 	lds	r24, 0x0104
  d2:	90 91 05 01 	lds	r25, 0x0105
  d6:	a0 91 06 01 	lds	r26, 0x0106
  da:	b0 91 07 01 	lds	r27, 0x0107
	unsigned char f = timer0_fract;
  de:	30 91 08 01 	lds	r19, 0x0108

	m += MILLIS_INC;
  e2:	01 96       	adiw	r24, 0x01	; 1
  e4:	a1 1d       	adc	r26, r1
  e6:	b1 1d       	adc	r27, r1
	f += FRACT_INC;
  e8:	23 2f       	mov	r18, r19
  ea:	2d 5f       	subi	r18, 0xFD	; 253
	if (f >= FRACT_MAX) {
  ec:	2d 37       	cpi	r18, 0x7D	; 125
  ee:	20 f0       	brcs	.+8      	; 0xf8 <__vector_16+0x40>
		f -= FRACT_MAX;
  f0:	2d 57       	subi	r18, 0x7D	; 125
		m += 1;
  f2:	01 96       	adiw	r24, 0x01	; 1
  f4:	a1 1d       	adc	r26, r1
  f6:	b1 1d       	adc	r27, r1
	}

	timer0_fract = f;
  f8:	20 93 08 01 	sts	0x0108, r18
	timer0_millis = m;
  fc:	80 93 04 01 	sts	0x0104, r24
 100:	90 93 05 01 	sts	0x0105, r25
 104:	a0 93 06 01 	sts	0x0106, r26
 108:	b0 93 07 01 	sts	0x0107, r27
	timer0_overflow_count++;
 10c:	80 91 00 01 	lds	r24, 0x0100
 110:	90 91 01 01 	lds	r25, 0x0101
 114:	a0 91 02 01 	lds	r26, 0x0102
 118:	b0 91 03 01 	lds	r27, 0x0103
 11c:	01 96       	adiw	r24, 0x01	; 1
 11e:	a1 1d       	adc	r26, r1
 120:	b1 1d       	adc	r27, r1
 122:	80 93 00 01 	sts	0x0100, r24
 126:	90 93 01 01 	sts	0x0101, r25
 12a:	a0 93 02 01 	sts	0x0102, r26
 12e:	b0 93 03 01 	sts	0x0103, r27
}
 132:	bf 91       	pop	r27
 134:	af 91       	pop	r26
 136:	9f 91       	pop	r25
 138:	8f 91       	pop	r24
 13a:	3f 91       	pop	r19
 13c:	2f 91       	pop	r18
 13e:	0f 90       	pop	r0
 140:	0f be       	out	0x3f, r0	; 63
 142:	0f 90       	pop	r0
 144:	1f 90       	pop	r1
 146:	18 95       	reti

00000148 <init>:

void init()
{
	// this needs to be called before setup() or some functions won't
	// work there
	sei();
 148:	78 94       	sei
	
	// on the ATmega168, timer 0 is also used for fast hardware pwm
	// (using phase-correct PWM would mean that timer 0 overflowed half as often
	// resulting in different millis() behavior on the ATmega8 and ATmega168)
#if defined(TCCR0A) && defined(WGM01)
	sbi(TCCR0A, WGM01);
 14a:	84 b5       	in	r24, 0x24	; 36
 14c:	82 60       	ori	r24, 0x02	; 2
 14e:	84 bd       	out	0x24, r24	; 36
	sbi(TCCR0A, WGM00);
 150:	84 b5       	in	r24, 0x24	; 36
 152:	81 60       	ori	r24, 0x01	; 1
 154:	84 bd       	out	0x24, r24	; 36
	// this combination is for the standard atmega8
	sbi(TCCR0, CS01);
	sbi(TCCR0, CS00);
#elif defined(TCCR0B) && defined(CS01) && defined(CS00)
	// this combination is for the standard 168/328/1280/2560
	sbi(TCCR0B, CS01);
 156:	85 b5       	in	r24, 0x25	; 37
 158:	82 60       	ori	r24, 0x02	; 2
 15a:	85 bd       	out	0x25, r24	; 37
	sbi(TCCR0B, CS00);
 15c:	85 b5       	in	r24, 0x25	; 37
 15e:	81 60       	ori	r24, 0x01	; 1
 160:	85 bd       	out	0x25, r24	; 37

	// enable timer 0 overflow interrupt
#if defined(TIMSK) && defined(TOIE0)
	sbi(TIMSK, TOIE0);
#elif defined(TIMSK0) && defined(TOIE0)
	sbi(TIMSK0, TOIE0);
 162:	ee e6       	ldi	r30, 0x6E	; 110
 164:	f0 e0       	ldi	r31, 0x00	; 0
 166:	80 81       	ld	r24, Z
 168:	81 60       	ori	r24, 0x01	; 1
 16a:	80 83       	st	Z, r24
	// timers 1 and 2 are used for phase-correct hardware pwm
	// this is better for motors as it ensures an even waveform
	// note, however, that fast pwm mode can achieve a frequency of up
	// 8 MHz (with a 16 MHz clock) at 50% duty cycle

	TCCR1B = 0;
 16c:	e1 e8       	ldi	r30, 0x81	; 129
 16e:	f0 e0       	ldi	r31, 0x00	; 0
 170:	10 82       	st	Z, r1

	// set timer 1 prescale factor to 64
#if defined(TCCR1B) && defined(CS11) && defined(CS10)
	sbi(TCCR1B, CS11);
 172:	80 81       	ld	r24, Z
 174:	82 60       	ori	r24, 0x02	; 2
 176:	80 83       	st	Z, r24
	sbi(TCCR1B, CS10);
 178:	80 81       	ld	r24, Z
 17a:	81 60       	ori	r24, 0x01	; 1
 17c:	80 83       	st	Z, r24
	sbi(TCCR1, CS11);
	sbi(TCCR1, CS10);
#endif
	// put timer 1 in 8-bit phase correct pwm mode
#if defined(TCCR1A) && defined(WGM10)
	sbi(TCCR1A, WGM10);
 17e:	e0 e8       	ldi	r30, 0x80	; 128
 180:	f0 e0       	ldi	r31, 0x00	; 0
 182:	80 81       	ld	r24, Z
 184:	81 60       	ori	r24, 0x01	; 1
 186:	80 83       	st	Z, r24

	// set timer 2 prescale factor to 64
#if defined(TCCR2) && defined(CS22)
	sbi(TCCR2, CS22);
#elif defined(TCCR2B) && defined(CS22)
	sbi(TCCR2B, CS22);
 188:	e1 eb       	ldi	r30, 0xB1	; 177
 18a:	f0 e0       	ldi	r31, 0x00	; 0
 18c:	80 81       	ld	r24, Z
 18e:	84 60       	ori	r24, 0x04	; 4
 190:	80 83       	st	Z, r24

	// configure timer 2 for phase correct pwm (8-bit)
#if defined(TCCR2) && defined(WGM20)
	sbi(TCCR2, WGM20);
#elif defined(TCCR2A) && defined(WGM20)
	sbi(TCCR2A, WGM20);
 192:	e0 eb       	ldi	r30, 0xB0	; 176
 194:	f0 e0       	ldi	r31, 0x00	; 0
 196:	80 81       	ld	r24, Z
 198:	81 60       	ori	r24, 0x01	; 1
 19a:	80 83       	st	Z, r24
#if defined(ADCSRA)
	// set a2d prescale factor to 128
	// 16 MHz / 128 = 125 KHz, inside the desired 50-200 KHz range.
	// XXX: this will not work properly for other clock speeds, and
	// this code should use F_CPU to determine the prescale factor.
	sbi(ADCSRA, ADPS2);
 19c:	ea e7       	ldi	r30, 0x7A	; 122
 19e:	f0 e0       	ldi	r31, 0x00	; 0
 1a0:	80 81       	ld	r24, Z
 1a2:	84 60       	ori	r24, 0x04	; 4
 1a4:	80 83       	st	Z, r24
	sbi(ADCSRA, ADPS1);
 1a6:	80 81       	ld	r24, Z
 1a8:	82 60       	ori	r24, 0x02	; 2
 1aa:	80 83       	st	Z, r24
	sbi(ADCSRA, ADPS0);
 1ac:	80 81       	ld	r24, Z
 1ae:	81 60       	ori	r24, 0x01	; 1
 1b0:	80 83       	st	Z, r24

	// enable a2d conversions
	sbi(ADCSRA, ADEN);
 1b2:	80 81       	ld	r24, Z
 1b4:	80 68       	ori	r24, 0x80	; 128
 1b6:	80 83       	st	Z, r24
	// here so they can be used as normal digital i/o; they will be
	// reconnected in Serial.begin()
#if defined(UCSRB)
	UCSRB = 0;
#elif defined(UCSR0B)
	UCSR0B = 0;
 1b8:	10 92 c1 00 	sts	0x00C1, r1
#endif
}
 1bc:	08 95       	ret

000001be <_exit>:
 1be:	f8 94       	cli

000001c0 <__stop_program>:
 1c0:	ff cf       	rjmp	.-2      	; 0x1c0 <__stop_program>
