package com.testing;

import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.ViterbiCalculator;

public class Testing {

	
	private Hmm<ObservationInteger> hmm;
	
	public Testing(Hmm<ObservationInteger> hmm){
		this.hmm = hmm;
	}
	
	public Testing() {
		
	}
	
	/** Return the most likely state sequence of an observation sequence given a Hidden Markov Model
	  * This computation use the Viterbi Decoder **/
	public int[] stateSequence(List<ObservationInteger> oseq) {
		
		ViterbiCalculator vc = new ViterbiCalculator(oseq, this.hmm);
		return vc.stateSequence();
		
	}
	
	
	/** Generate getters and setters **/
	
	public Hmm<ObservationInteger> getHmm() {
		return hmm;
	}

	public void setHmm(Hmm<ObservationInteger> hmm) {
		this.hmm = hmm;
	}

}
