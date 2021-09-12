package com.testing;

import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.ViterbiCalculator;

public class Testing {

	
	private Hmm<ObservationInteger> hmm;
	private List<ObservationInteger> oseq;
	
	public Testing(Hmm<ObservationInteger> hmm, List<ObservationInteger> oseq){
		this.hmm = hmm;
		this.oseq = oseq;
	}
	
	public Testing() {
		
	}
	
	/** Return the most likely state sequence of an observation sequence given a Hidden Markov Model
	  * This computation use the Viterbi Decoder **/
	public int[] stateSequence() {
		
		ViterbiCalculator vc = new ViterbiCalculator(this.oseq, this.hmm);
		return vc.stateSequence();
		
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
