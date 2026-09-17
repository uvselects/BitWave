# Project Statement: SignalLab
 
## Problem Statement
 
Whenever data travels over a physical medium, it picks up noise along the way — this can flip bits and corrupt the message. Real communication systems deal with this using techniques like error-correcting codes, but testing these techniques on actual hardware is slow and expensive.
 
SignalLab solves this by simulating the whole process in software: generate a signal, send it through a "noisy" channel, and see how well error correction repairs the damage.
 
## Scope
 
SignalLab is a console-based Java app that models a basic transmitter → noisy channel → receiver pipeline. It covers:
 
- Generating simple waveforms (Sine, Square, Sawtooth) and sampling them into bits
- Encoding data with (7,4) Hamming code so single-bit errors can be fixed
- Simulating a noisy channel that randomly flips bits based on a probability you set
- Decoding the received data, detecting errors, and correcting them
- Reporting results like Bit Error Rate (BER) and saving a log of each run
## Who all can use it
 
- **Students** learning ECE concepts, who want to actually see how noise affects a signal and how error correction fixes it, instead of just reading about it
- **Evaluators/instructors** checking that core Java concepts (OOP, exceptions, file handling) are applied correctly to a real engineering problem

## Main Modules
 
1. **Signal Generator & Encoder** – creates the waveform, checks it against the Nyquist-Shannon sampling rule, and encodes it with Hamming (7,4).
2. **Noisy Channel Simulator** – randomly flips bits to mimic real-world interference
3. **Receiver & Analytics Engine** – decodes the signal, corrects errors, calculates BER, and logs the results to a file
## What all it needs to do well
 
- Run the simulation instantly with no noticeable delay.
- Keep Calculations precise (sampling checks, noise injection, BER)
- Stay easy to Use — a simple, guided command-line menu.
- Fail gracefully — bad input (like an invalid sampling rate) should raise a clear .error, not crash the program
## Key Features
 
- Configurable signal type and sampling rate
- Working (7,4) Hamming code encode/decode logic
- Randomized bit-flip noise based on a probability you choose
- Console summary of errors detected and corrected
- Automatic log file saved to the `data/` folder after each run
 