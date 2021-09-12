package com.scoring;

import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardCalculator;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;

public class Scoring {

	private Hmm<ObservationInteger> hmm; // Hidden Markov Model
	private List<ObservationInteger> oseq; // Observation sequence
	
	public Scoring(Hmm<ObservationInteger> hmm, List<ObservationInteger> oseq){
		this.hmm = hmm;
		this.oseq = oseq;
	}
	
	public Scoring(){

	}
	
	
	/** Computes the probability of occurrence of an observation sequence given a Hidden Markov Model
	  * This computation use the Forward algorithm **/
	public double probability() {
		
		ForwardBackwardCalculator forward = new ForwardBackwardCalculator(this.oseq, this.hmm);
		return forward.probability();
		
	}

	/** Generate getters and setters **/
	
	public Hmm<ObservationInteger> getHmm() {
		return hmm;
	}

	public void setHmm(Hmm<ObservationInteger> hmm) {
		this.hmm = hmm;
	}

	public List<ObservationInteger> getOseq() {
		return oseq;
	}

	public void setOseq(List<ObservationInteger> oseq) {
		this.oseq = oseq;
	}
	
}
