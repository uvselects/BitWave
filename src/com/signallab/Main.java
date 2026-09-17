package com.signallab;

import com.signallab.analysis.BERAnalyzer;
import com.signallab.channel.NoisyChannel;
import com.signallab.encoding.HammingCode;
import com.signallab.encoding.LineEncoder;
import com.signallab.exceptions.InvalidSignalException;
import com.signallab.exceptions.TransmissionException;
import com.signallab.receiver.Receiver;
import com.signallab.signal.SignalGenerator;
import com.signallab.signal.Waveform;
import com.signallab.util.FileHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SignalGenerator generator = new SignalGenerator();
        LineEncoder encoder = new LineEncoder();
        HammingCode hamming = new HammingCode();
        NoisyChannel channel = new NoisyChannel();
        Receiver receiver = new Receiver();
        BERAnalyzer analyzer = new BERAnalyzer();
        FileHandler fileHandler = new FileHandler();

        
        System.out.println("   SignalLab - Digital Communication Simulator   ");
        

        try {

            System.out.println("\n[MODULE 1] Select Waveform Type:");
            System.out.println("1. SINE");
            System.out.println("2. SQUARE");
            System.out.println("3. SAWTOOTH");
            System.out.print("Enter choice (1-3): ");
            int waveChoice = scanner.nextInt();

            Waveform selectedWaveform;
            switch (waveChoice) {
                case 1: selectedWaveform = Waveform.SINE; break;
                case 2: selectedWaveform = Waveform.SQUARE; break;
                case 3: selectedWaveform = Waveform.SAWTOOTH; break;
                default: throw new InvalidSignalException("Waveform option selected is out of bounds.");
            }

            System.out.print("Enter Signal Frequency (Hz): ");
            double frequency = scanner.nextDouble();

            System.out.print("Enter Sampling Rate (Hz) [Must be >= 2x Freq]: ");
            double samplingRate = scanner.nextDouble();

            System.out.print("Enter Signal Duration (seconds): ");
            int duration = scanner.nextInt();

            double[] analogSignal = generator.generateAnalogSignal(selectedWaveform, frequency, samplingRate, duration);
            int[] rawBits = generator.toBitstream(analogSignal);
            System.out.println(">>> Waveform sampled into a digital bitstream of " + rawBits.length + " bits.");

            System.out.print("\n[MODULE 3] Enter Channel Error Probability (0.0 to 1.0): ");
            double errorProb = scanner.nextDouble();
            if (errorProb < 0.0 || errorProb > 1.0) {
                throw new InvalidSignalException("Probability value must lie between 0.0 and 1.0.");
            }

            System.out.println("\n[PROCESSING] Driving bitstream through pipeline infrastructure...");

            int[] encodedBits = hamming.encodeStream(rawBits);

            int[] corruptedBits = channel.introduceNoise(encodedBits, errorProb);

            int[] recoveredBits = receiver.processIncomingStream(corruptedBits);

            int rawChannelErrors = analyzer.countErrors(encodedBits, corruptedBits);
            double systemBER = analyzer.calculateBER(rawBits, recoveredBits);

            StringBuilder report = new StringBuilder();
           
            report.append("             SIGNALLAB EXECUTION METRICS          \n");
           
            report.append("Waveform Type: ").append(selectedWaveform).append("\n");
            report.append("frequency: ").append(frequency).append(" Hz\n");
            report.append("Sampling Rate: ").append(samplingRate).append(" Hz\n");
            report.append("original data Size: ").append(rawBits.length).append(" bits\n");
            report.append("Channel Noise Level: ").append(errorProb * 100).append("%\n");
            report.append("Total Raw Channel Bit Flips: ").append(rawChannelErrors).append(" bits\n");
            report.append("Uncorrected Bit Error Rate (BER): ").append(systemBER).append("\n");
            report.append("System Recovery Status: ");
            if (systemBER == 0.0) {
                report.append("SUCCESS - All single-bit corruptions repaired fully via Hamming parity.\n");
            } else {
                report.append("PARTIAL - Multi-bit block corruptions exceeded Hamming single-error bounds.\n");
            }
            

            System.out.print("\n" + report.toString());
            fileHandler.saveSimulationReport(report.toString());

        } catch (InvalidSignalException | TransmissionException e) {
            System.err.println("\n[PIPELINE ABORTED] Error Detected: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n[CRITICAL FAILURE] Unexpected system state exception: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\nfinished ");
        }
    }
}
