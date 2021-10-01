package com.training;

import java.util.ArrayList;
import java.util.List;

import com.models.Activity;
import com.models.Intention;
import com.models.Row;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.Observation;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.toolbox.KullbackLeiblerDistanceCalculator;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;

public class Training {

	private int n; // Number of enumerated possible intentions
	private int m; // Number of observable activities
	private int l; // length of observation sequence
	
	private double[][] t; // Transition probabilities
	private double[][] e; // Emission probabilities
	private double[] pi; // Initial probability distribution
	
	private List<Intention> intentions;
	private List<Row> trainRows;
	
	double[] distancehist; // stock distance historical

	public Training(int n, int m, int l){
		
		this.n = n;
		this.m = m;
		this.l = l;
		this.t = new double[n][n];
		this.e = new double[n][l];
		this.pi = new double[n];
		intentions = new ArrayList<Intention>(n);
	}
	

	/** Build init Hidden Markov Model **/
	
	public Hmm<ObservationInteger> buildInitHmm() {
		Hmm<ObservationInteger> hmm = new Hmm<ObservationInteger>(this.n, new OpdfIntegerFactory(this.l));
		return hmm;
	}
	
	
	/** Build HMM with the parameters **/
	
	public Hmm<ObservationInteger> buildHmm(){
		
		Hmm<ObservationInteger> hmm = new Hmm<ObservationInteger>(this.n, new OpdfIntegerFactory(this.l));
		
		// Set The initial probabilities
		for(int i = 0; i < pi.length; i++) {
			hmm.setPi(i, pi[i]);
		}
		
		// Set the emission probabilities
		for(int i = 0; i < e.length; i++) {
			hmm.setOpdf(i, new OpdfInteger(e[i]));
		}
		
		// Set the transition probabilities
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t.length; j++) {
				hmm.setAij(i, j, t[i][j]);
			}
		}
		
		return hmm;
	}
	

	/** Generate several observation sequences using an HMM **/
	
	public <O extends Observation> List<List<O>> generateSequences(Hmm<O> hmm, int a){
		
		MarkovGenerator<O> mg = new MarkovGenerator<O>(hmm);
		
		List<List<O>> sequences = new ArrayList<List<O>>();
		for (int i = 0; i < a; i++)
			sequences.add(mg.observationSequence(this.l));

		return sequences;
	}
	
	/** Generate several observation sequences from training rows using an HMM **/
	
	public List<List<ObservationInteger>> generateSequencesfromDB(List<Row> trainRows){
		
		List<List<ObservationInteger>> sequences = new ArrayList<List<ObservationInteger>>();
		for (Row row : trainRows) {
			List<ObservationInteger> oseq1 = new ArrayList<ObservationInteger>();
			List<ObservationInteger> oseq2 = new ArrayList<ObservationInteger>();
			List<ObservationInteger> oseq3 = new ArrayList<ObservationInteger>();
			
			for(Activity a : row.getIntention1().getActions()) {
				oseq1.add(new ObservationInteger(a.getIndex()));
			}
			for(Activity a : row.getIntention2().getActions()) {
				oseq2.add(new ObservationInteger(a.getIndex()));
			}
			for(Activity a : row.getIntention3().getActions()) {
				oseq3.add(new ObservationInteger(a.getIndex()));
			}
			
			sequences.add(oseq1);
			sequences.add(oseq2);
			sequences.add(oseq3);
		}

		return sequences;
	}
	
	/** Update Transition matrix using the context probabilities **/
	
	public Hmm<ObservationInteger> updateTransition(Hmm<ObservationInteger> hmm) {
		
		return hmm;
	}
	
	/** Train the HMM model **/
	
	public Hmm<ObservationInteger> train(int epochs, double distance, boolean fromDB){
		
		// Build a HMM and generate observation sequences using this HMM
		Hmm<ObservationInteger> learnHmm = buildHmm();
		
		Hmm<ObservationInteger> tempHmm;
		
		KullbackLeiblerDistanceCalculator distanceCalculator = new KullbackLeiblerDistanceCalculator();
		
		
		List<List<ObservationInteger>> sequences;
		if(fromDB) {
			sequences = generateSequencesfromDB(this.trainRows);
		}else sequences = generateSequences(learnHmm, 200);
		
		
		// Baum-Welch learning
		BaumWelchLearner bwl = new BaumWelchLearner();
		
		double[] distancetemp = new double[epochs];
		int k = 0;
		
		// Incrementally improve the solution
		for (int i = 0; i < epochs; i++) {
			
			tempHmm = learnHmm;
			learnHmm = bwl.iterate(learnHmm, sequences);
			learnHmm = updateTransition(learnHmm);
			double d = Math.abs(distanceCalculator.distance(tempHmm, learnHmm));
			
			distancetemp[i] = d;
			k++;
			
			//System.out.println("Iteration : " + (i+1) + " || Distance : " + d);
			
			if(d < distance) break;
			
		}
		distancehist = new double[k];
		for(int i=0; i<k; i++) {
			distancehist[i] = distancetemp[i];
		}
		return learnHmm;
	}
	
	
	/** Getters and Setters **/
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public double[][] getT() {
		return t;
	}

	public void setT(double[][] t) {
		this.t = t;
	}

	public double[][] getE() {
		return e;
	}

	public void setE(double[][] e) {
		this.e = e;
	}

	public double[] getPi() {
		return pi;
	}

	public void setPi(double[] pi) {
		this.pi = pi;
	}

	public List<Intention> getIntentions() {
		return intentions;
	}

	public void setIntentions(List<Intention> intentions) {
		this.intentions = intentions;
	}


	public double[] getDistancehist() {
		return distancehist;
	}


	public void setDistancehist(double[] distancehist) {
		this.distancehist = distancehist;
	}


	public List<Row> getTrainRows() {
		return trainRows;
	}


	public void setTrainRows(List<Row> trainRows) {
		this.trainRows = trainRows;
	}

	
}
