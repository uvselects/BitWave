# SignalLab: Digital Communication Simulator

SignalLab is a console-based Java project that I made to understand how data is transmitted in a digital communication system.

The basic idea is to take a signal, sample it, convert it into bits, send those bits through a noisy channel, and then check whether the original data can be recovered at the receiving end. Through this project, I wanted to connect some ECE concepts like signal sampling, the Nyquist theorem, and error correction with Java concepts like OOP, custom exceptions, and file handling.

## What this project does

The project starts by generating a waveform. The user can choose between a Sine, Square, or Sawtooth wave. After that, the waveform is sampled at a given sampling rate, and the sampled values are converted into a binary bitstream.

Before moving ahead, the program checks whether the sampling rate is sufficient according to the Nyquist-Shannon theorem. If the rate is too low, it throws a custom exception instead of continuing with an invalid signal.

Once the sampling is done, the bitstream is encoded using (7,4) Hamming code. This adds parity bits that help the receiver detect and correct single-bit errors.

Next, the encoded bits are passed through a simulated noisy channel. The channel uses an error probability to randomly flip some bits, which represents the kind of errors that can happen during data transmission.

At the receiving end, the program decodes the bits and checks for errors using the Hamming code. If a single-bit error is found, it corrects it and recovers the original data.

Finally, the program displays a summary of the run and saves details such as the input parameters, errors introduced, and errors corrected in a log file.

So, in simple words, this project shows how data can get changed while travelling through a channel and how error-correction techniques help us recover the correct information.

## Features

- Generates Sine, Square and Sawtooth waveforms.

- Converts sampled signal values into binary bits.

- Checks the Nyquist sampling condition.

- Uses (7,4) Hamming code for error correction.

- Simulates random bit errors using error probability.

- Detects and corrects single-bit errors.

- Uses custom exceptions for invalid inputs.

- Saves the execution details in a log file.

## Tech Stack

| Component | Details |

| Language | Java |

| JDK | Java 8 or above |

| IDE | Visual Studio Code |

| Compile | `javac` |

| Run | Java Command Prompt / Terminal |

| External Libraries | None |

## Project Structure

```text

SignalLab/

├── src/

│ └── com/

│ └── signallab/

│ ├── Main.java

│ ├── signal/

│ │ ├── WaveformGenerator.java

│ │ └── Sampler.java

│ ├── encoding/

│ │ └── HammingEncoder.java

│ ├── channel/

│ │ └── NoisyChannel.java

│ ├── exceptions/

│ │ └── InvalidSignalException.java

│ └── logging/

│ └── ReportLogger.java

├── bin/

├── data/

└── README.md

```

## How to Run

### 1. Open the project folder

```bash

cd C:\SignalLab

```

### 2. Compile the project

```bash

javac -sourcepath src -d bin src/com/signallab/Main.java

```

### 3. Run the program

```bash

java -cp bin com.signallab.Main

```

## How to Use

When the program starts, it asks for some inputs:

1. **Waveform Type**

- `1` → Sine

- `2` → Square

- `3` → Sawtooth

2. **Signal Frequency** — Enter the frequency in Hz.

3. **Sampling Rate** — Enter the sampling rate. It should satisfy:

`Fs >= 2 × Fsignal`

4. **Duration** — Enter the signal duration in seconds.

5. **Error Probability** — Enter a value between `0.0` and `1.0`.

For example, `0.02` means there is a 2% chance of a bit getting flipped.

After taking the inputs, the program runs the complete pipeline and displays the result. It also saves a report in the `data` folder.

## Testing

### Test 1: Normal Run

| Parameter | Value |

|---|---|

| Waveform | `1` (Sine) |

| Frequency | `5 Hz` |

| Sampling Rate | `20 Hz` |

| Duration | `2 seconds` |

| Error Probability | `0.02` |

**Expected result:** The pipeline should complete successfully. If single-bit errors are introduced, Hamming code should detect and correct them. A report file should also be created.

### Test 2: Nyquist Violation

| Parameter | Value |

|---|---|

| Waveform | `1` (Sine) |

| Frequency | `10 Hz` |

| Sampling Rate | `15 Hz` |

Here, the sampling rate is less than `2 × 10`, so the program should throw `InvalidSignalException` with the message:

```text

Nyquist Shannon violation!

```

The exception should be handled properly instead of crashing the program.

## Sample Output

```text

SignalLab: Digital Communication Simulator

Waveform: SINE

Frequency: 5 Hz

Sampling Rate: 20 Hz

Duration: 2 seconds

Nyquist check passed (20 >= 2 x 5)

Bitstream generated: 40 samples

Hamming (7,4) encoding complete

Channel error probability: 0.02

Bits flipped by channel: 3

Errors detected: 3

Errors corrected: 3

Final bit error rate after correction: 0.00%

Report saved to: data/run_20260917_1.log

```

## What I Learned

While making this project, I got to understand how signal sampling and error correction work together in a communication system.

I also practiced Java concepts like classes, packages, exception handling, file I/O, and separating the project into different modules.

## Future Improvements

- Add a graphical interface to display the waveforms.

- Show the original and received bitstreams side by side.

- Add more error-correction techniques.

- Improve the signal quantization process.

- Add more detailed graphs and analysis.
