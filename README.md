
# SignalLab: Digital Communication Simulator
 
A console-based Java application that simulates and analyzes the data integrity pipeline of a digital communication system under variable channel noise conditions. Built to bridge core Electronics and Communication Engineering (ECE) concepts — signal sampling, error correction, channel noise — with solid software engineering practice (OOP design, custom exceptions, file I/O).
 
## Overview
 
SignalLab walks a signal through the full lifecycle of a digital communication system:
 
1. Generate an analog waveform (Sine, Square, or Sawtooth).
2. Sample it into a binary bitstream, validated against the Nyquist-Shannon sampling theorem.
3. Encode the bitstream with (7,4) Hamming code for error protection.
4. Pass it through a simulated noisy channel that randomly flips bits.
5. Decode and correct errors on the receiving end.
6. Log the full run — parameters, errors introduced, errors corrected — to disk.
It's designed as a learning/demo tool for understanding how error correction and sampling constraints behave under real-world-like noise, without needing physical hardware.
 
## Features
 
- **Signal Generation & Quantization** — Simulates Sine, Square, and Sawtooth analog waveforms and samples them into binary bitstreams.
- **Nyquist-Shannon Validation** — Enforces the sampling rule (Fs ≥ 2 × Fsignal) and throws a custom exception on violation, preventing aliasing.
- **(7,4) Hamming Block Encoding** — Encodes 4-bit data blocks with 3 parity bits, enabling detection and correction of single-bit errors.
- **Noisy Channel Simulation** — A configurable, probability-based bit-flip engine that models stochastic transmission interference.
- **Error Detection & Correction** — Automatically detects and repairs single-bit errors during decoding using Hamming parity checks.
- **Automated Logging** — Writes a full execution report (parameters, bit error rate, correction stats) to a local log file after each run.
- **Custom Exception Handling** — Domain-specific exceptions (e.g. `InvalidSignalException`) for graceful failure instead of crashes.
## Tech Stack
 
| Component | Details |
|---|---|
| Language | Java SE (JDK 8+) |
| IDE | Visual Studio Code |
| Build/Compile | `javac` (CLI, no external build tool) |
| OS | Windows Command Prompt / Terminal (also runs on macOS/Linux shells) |
 
## Project Structure
 
```
SignalLab/
├── src/
│   └── com/
│       └── signallab/
│           ├── Main.java              # Entry point, console menu
│           ├── signal/
│           │   ├── WaveformGenerator.java
│           │   └── Sampler.java
│           ├── encoding/
│           │   └── HammingEncoder.java
│           ├── channel/
│           │   └── NoisyChannel.java
│           ├── exceptions/
│           │   └── InvalidSignalException.java
│           └── logging/
│               └── ReportLogger.java
├── bin/                                # Compiled .class output
├── data/                               # Generated log/report files
└── README.md
```
 
*(Adjust paths/class names above to match your actual package layout if different.)*
 
## Setup & Run
 
**1. Navigate to the project folder**
```bash
cd C:\SignalLab
```
 
**2. Compile**
```bash
javac -sourcepath src -d bin src/com/signallab/Main.java
```
 
**3. Run**
```bash
java -cp bin com.signallab.Main
```
 
## Usage
 
On launch, the console menu will prompt for:
 
1. **Waveform Type** — `1` Sine, `2` Square, `3` Sawtooth
2. **Signal Frequency** (Hz)
3. **Sampling Rate** (Hz) — must satisfy Fs ≥ 2 × Fsignal
4. **Signal Duration** (seconds)
5. **Channel Error Probability** — a decimal between `0.0` and `1.0` (e.g. `0.02` = 2% chance per bit)
The program then runs the full pipeline and prints a summary to the console, plus writes a log file to `data/`.
 
## Testing
 
**Test 1 — Normal Run (Error Correction)**
 
| Parameter | Value |
|---|---|
| Waveform | `1` (Sine) |
| Frequency | `5` |
| Sampling Rate | `20` |
| Duration | `2` |
| Error Probability | `0.02` |
 
Expected: Pipeline completes end-to-end, corrects single-bit errors via Hamming parity, prints a summary report, and writes a log file to `C:\SignalLab\data\`.
 
**Test 2 — Nyquist Violation**
 
| Parameter | Value |
|---|---|
| Waveform | `1` (Sine) |
| Frequency | `10` |
| Sampling Rate | `15` (invalid: 15 < 2 × 10) |
 
Expected: `InvalidSignalException` is thrown with the message `Nyquist Shannon violation!`. The program handles it gracefully and does not crash.
 
## Sample Output
 
```
     SignalLab: Digital Communication Simulator 
Waveform: SINE | Frequency: 5 Hz | Sampling Rate: 20 Hz | Duration: 2s
Nyquist check passed (20 >= 2 x 5)
Bitstream generated: 40 samples
Hamming (7,4) encoding complete
Channel error probability: 0.02
Bits flipped by channel: 3
Errors detected: 3 | Errors corrected: 3
Final bit error rate after correction: 0.00%
Report saved to: data/run_20260917_1.log
```
 
