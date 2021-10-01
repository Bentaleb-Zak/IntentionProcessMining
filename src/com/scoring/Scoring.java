package com.scoring;

import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardCalculator;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;

public class Scoring {

	private Hmm<ObservationInteger> hmm; // Hidden Markov Model
	
	public Scoring(Hmm<ObservationInteger> hmm){
		this.hmm = hmm;
	}
	
	public Scoring(){

	}
	
	
	/** Computes the probability of occurrence of an observation sequence given a Hidden Markov Model
	  * This computation use the Forward algorithm **/
	public double probability(List<ObservationInteger> oseq) {
		
		ForwardBackwardCalculator forward = new ForwardBackwardCalculator(oseq, this.hmm);
		return forward.probability();
		
	}

	/** Generate getters and setters **/
	
	public Hmm<ObservationInteger> getHmm() {
		return hmm;
	}

	public void setHmm(Hmm<ObservationInteger> hmm) {
		this.hmm = hmm;
	}

	
}
