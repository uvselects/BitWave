# Project Statement: BitWave

## Problem Statement

When data moves from one place to another the signal can be disturbed by noise. This disturbance may cause some bits to change, which means the original data might become corrupted.

In communication systems error-correction techniques are used to find and fix these mistakes.. Testing these methods on actual hardware usually takes a lot of time and can also cost a lot of money.

To solve this I built BitWave as a software simulation. It lets me create a signal send it through a channel and see what happens to the data. It also shows how Hamming code helps in detecting and fixing errors.

## Scope

BitWave is a console-based Java application that follows the basic flow of a communication system:

**Sender → Noisy Channel → Receiver**

The project focuses on the following:

* Creating waveforms such as Sine, Square and Sawtooth.

* Sampling the waveform. Turning the samples into bits.

* Checking if the sampling rate meets the Nyquist-Shannon theorem.

* Using (7,4) Hamming code to add error correction.

* Adding noise by flipping bits during transmission.

* Receiving the data. Decoding it.

*. Correcting single-bit errors.

* Displaying the number of errors. Calculating the final Bit Error Rate (BER).

* Saving the results of each run in a log file.

## Who Can Use It

This project can help:

* **Students** who are studying communication systems and want to understand noise, sampling and error correction with an example.

* **. Evaluators** who want to see how Java concepts like object-oriented programming, exceptions and file handling work in an engineering project.

## Main Modules

### 1. Signal Generator and Encoder

This module creates the chosen waveform. Takes samples from it. It also checks whether the sampling rate follows the Nyquist rule.

Then the data is turned into bits. Encoded using Hamming (7,4).

### 2. Noisy Channel

This module acts like a communication channel where errors can occur.

The user gives a probability value. Based on that value some bits are randomly flipped while the data is being sent.

### 3. Receiver and Analytics

At the side the program checks the received bits and uses Hamming code to detect and correct single-bit errors.

It also calculates the Bit Error Rate. Shows how many errors were found and fixed. The details are then saved in a log file.

## What the Project Should Do Well

* The simulation should run fast. Not take too much time.

* The calculations for sampling, noise and BER must be accurate.

* The program should be easy to use with a command-line menu.

* If the user enters something the program should show a clear error message instead of crashing suddenly.

## Key Features

* User can choose the type of signal and the sampling rate.

* Uses Hamming (7,4) for error detection and correction.

* Simulates noise, by changing bits.

* Shows the number of errors detected and corrected in the console.

* Calculates the Bit Error Rate (BER).

* Automatically saves a log file inside the `data/` folder after each run.
